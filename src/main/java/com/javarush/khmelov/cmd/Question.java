package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.QuestObject;
import com.javarush.khmelov.service.QuestResponsesService;
import com.javarush.khmelov.service.QuestionService;
import com.javarush.khmelov.tools.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;

public class Question implements Command {
    private final QuestionService questionService;
    private final QuestResponsesService questResponsesService;

    public Question(QuestionService questionService, QuestResponsesService questResponsesService) {
        this.questionService = questionService;
        this.questResponsesService = questResponsesService;
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse res) {
        String selection = req.getParameter(Keys.JSP_VAL_RESPONSELIST); //"responselist"

        return getPage() + "?%s=%s".formatted(Keys.QUESTION, selection); //question
    }

    public String doGet(HttpServletRequest req, HttpServletResponse res) {
        return getJspPage();
    }
}
