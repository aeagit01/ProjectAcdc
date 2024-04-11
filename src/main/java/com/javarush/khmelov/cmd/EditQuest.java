package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.QuestElement;
import com.javarush.khmelov.repository.QuestionRepository;
import com.javarush.khmelov.service.*;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import com.javarush.khmelov.tools.Tools;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;

public class EditQuest implements Command {
    private final QuestService questService;
    private final QuestionService questionService;
    private final QuestResponsesService questResponsesService;
    private final QuestionRepository questionRepository;
    private final FinishMessageService finishMessageService;
    private final GeneralService generalService;

    public EditQuest(QuestService questService, QuestionService questionService,
                     QuestResponsesService questResponsesService,
                     QuestionRepository questionRepository, FinishMessageService finishMessageService, GeneralService generalService) {
        this.questService = questService;
        this.questionService = questionService;
        this.questResponsesService = questResponsesService;
        this.questionRepository = questionRepository;
        this.finishMessageService = finishMessageService;
        this.generalService = generalService;
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse res) {

        String commandKey;
        String suffix = "";
        String parametrId = null;
        String commandParametr = null;

        Long questID = Long.parseLong(req.getParameter(Keys.PARAMETR_ID));

        commandKey = getCommandName(req, Route.EDIT_QUEST);
        List<QuestElement> questElementList = generalService.findQuestQuestions(questID);

        if (commandKey == Route.SELECT_RESPONSE) {
            commandParametr = Keys.PARAMETR_QUESTION;
            questElementList = generalService.findQuestQuestions(questID);
            parametrId = questElementList.isEmpty() ? parametrId : questElementList.getFirst().getQuestionID().toString();
        }
        if (commandKey == Route.SELECT_NEXT_QUESTIONS) {
            commandParametr = Keys.PARAMETR_RESPONSE;
            QuestElement pattern = QuestElement.builder().questID(questID).build();
            questElementList = generalService.sortedFind(pattern, Comparator.comparingLong(QuestElement::getResponseID));
            QuestElement questElement = questElementList.getFirst();
            parametrId = questElementList.isEmpty() ? parametrId : "%d&%s=%d".formatted(questElement.getResponseID(),Keys.PARAMETR_QUESTION,questElement.getQuestionID());
        }

        if (parametrId!=null && !commandParametr.isEmpty()) {
            suffix = "&%s=%s".formatted(commandParametr, parametrId);
        }
        return commandKey + "?%s=%d".formatted(Keys.PARAMETR_ID,questID) + suffix;
    }

    public String doGet(HttpServletRequest req, HttpServletResponse res) {

        return getJspPage();
    }

    private String getCommandName(HttpServletRequest req, String currentCommand) {
        Tools tools = new Tools();
        String commandName = currentCommand;
        Optional<String> cmd = Optional.ofNullable(req.getParameter(Keys.JSP_VAL_DIRECT));
        if (cmd.isPresent()) {
            commandName = Tools.getCommandKeys(cmd.get());
        }
        return commandName;
    }

}