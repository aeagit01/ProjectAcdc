package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.repository.QuestionRepository;
import com.javarush.khmelov.service.FinishMessageService;
import com.javarush.khmelov.service.QuestResponsesService;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.service.QuestionService;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import com.javarush.khmelov.tools.Tools;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class EditQuest implements Command {
    private final QuestService questService;
    private final QuestionService questionService;
    private final QuestResponsesService questResponsesService;
    private final QuestionRepository questionRepository;
    private final FinishMessageService finishMessageService;

    public EditQuest(QuestService questService, QuestionService questionService,
                     QuestResponsesService questResponsesService,
                     QuestionRepository questionRepository, FinishMessageService finishMessageService) {
        this.questService = questService;
        this.questionService = questionService;
        this.questResponsesService = questResponsesService;
        this.questionRepository = questionRepository;
        this.finishMessageService = finishMessageService;
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandKey;
        Long questID = Long.parseLong(req.getParameter("id"));

        commandKey = getCommandName(req, Route.EDIT_QUEST);

        String suffix = commandKey==Route.SELECT_RESPONSE?"&q=1":"";
        suffix = commandKey==Route.SELECT_NEXT_QUESTIONS?"&r=1":suffix;

        return commandKey + "?id=" + questID + suffix;
    }

    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question questObject;
        Long questionID = Long.parseLong(req.getParameter("id"));
        String returnPage = getJspPage();

        return returnPage;
    }
    private String getCommandName(HttpServletRequest req, String currentCommand) {
        Tools tools = new Tools();
        String commandName = currentCommand;
        Optional<String> cmd = Optional.ofNullable(req.getParameter("direct"));
        if (cmd.isPresent()) {
            commandName = tools.getCommandKeys(cmd.get());
        }
        return commandName;
    }

    private String getFirstElement(ArrayList arrayList){


        return null;
    }
}