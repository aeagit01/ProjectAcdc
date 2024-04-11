package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.*;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.service.*;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Messages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Quest implements Command {
    private final QuestService questService;
    private final QuestionService questionService;
    private final QuestResponsesService questResponsesService;
    private final GeneralService generalService;
    private final FinishMessageService finishMessageService;

    public Quest(QuestService questService, QuestionService questionService, QuestResponsesService questResponsesService, GeneralService generalService, FinishMessageService finishMessageService) {
        this.questService = questService;
        this.questionService = questionService;
        this.questResponsesService = questResponsesService;
        this.generalService = generalService;
        this.finishMessageService = finishMessageService;
    }

    public String doGet(HttpServletRequest req, HttpServletResponse res) {
        Question question;
        List<QuestElement> responseElementList = new ArrayList<>();
        Optional<QuestElement> questElement = null;
        QuestElement pattern;
        ArrayList<QuestObject> responses = new ArrayList<>();

        String stringID = req.getParameter(Keys.PARAMETR_ID);
        String questionID = req.getParameter(Keys.PARAMETR_QUESTION);
        String finishParametr = req.getParameter(Keys.PARAMETR_FINISH);
        String returnPage = getJspPage();

        if (questionID != null) {
            pattern = QuestElement.builder()
                    .questID(Long.parseLong(stringID))
                    .questionID(Long.parseLong(questionID)).build();
            questElement = generalService.find(pattern).findFirst();
        }

        if (questElement != null && questElement.isPresent()) {
            question = questionService.get(questElement.get().getQuestionID());

            pattern = QuestElement.builder()
                    .questID(Long.parseLong(stringID))
                    .questionID(question.getId()).build();
            responseElementList = generalService.find(pattern).collect(Collectors.toList());
            for (QuestElement seletedResponse : responseElementList) {
                responses.add(questResponsesService.get(seletedResponse.getResponseID()));
            }
        } else {
            question = Question.builder().description(Messages.NO_FIRST_QUESTION).build();
        }

        req.setAttribute(Keys.QUESTION, //"question"
                finishParametr != null ? finishMessageService.get(Long.parseLong(finishParametr)) :
                        question);
        req.setAttribute(Keys.JSP_VAL_RESPONSES, responses); //"responses"
        req.setAttribute(Keys.JSP_VAL_BUTTONNAME, finishParametr != null ? Keys.BN_FINISH : Keys.BN_RESPONSE);

        return returnPage + "?id=%s&%s=%s".formatted(stringID,
                finishParametr != null ? Keys.PARAMETR_FINISH : Keys.PARAMETR_QUESTION,
                questionID == null ? "1" : questionID);
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse res) {
        String selection = req.getParameter(Keys.JSP_VAL_RESPONSELIST); //"responselist"
        String stringId = req.getParameter(Keys.PARAMETR_ID);
        String questionId = req.getParameter(Keys.PARAMETR_QUESTION);
        String nextParametr = Keys.PARAMETR_QUESTION;
        String suffix = Keys.EMPTYSTR;
        String commandKey = getPage();
        Long nextQuestion = 0L;

        if (selection != null) {
            QuestElement pattern = QuestElement.builder()
                                                .questID(Long.parseLong(stringId))
                                                .questionID(Long.parseLong(questionId))
                                                .responseID(Long.parseLong(selection)).build();
            Optional<QuestElement> questElement = generalService.find(pattern).findFirst();
            if (questElement.isPresent()) {
                nextParametr = getNextObjectParametr(questElement.get());
                nextQuestion = questElement.get().getNextQuestionID();
                suffix = "&%s=%d".formatted(nextParametr, nextQuestion);
            }
        }else{
            commandKey = Keys.COMMAND_SELECTQUEST;
        }

        String questID = req.getParameter(Keys.PARAMETR_ID);
        return  commandKey + "?%s=%s".formatted(Keys.PARAMETR_ID,questID) + suffix;
    }

    private String getNextObjectParametr(QuestElement questElement) {
        return questElement.getPosition() != Keys.ELEMENT_LAST ? Keys.PARAMETR_QUESTION : Keys.PARAMETR_FINISH;
    }
}
