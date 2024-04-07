package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.FinishMessage;
import com.javarush.khmelov.entity.QuestObject;
import com.javarush.khmelov.service.QuestResponsesService;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.service.QuestionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Question implements Command{
    private final QuestService questService;
    private final QuestionService questionService;
    private final QuestResponsesService questResponsesService;

    public Question(QuestService questService, QuestionService questionService, QuestResponsesService questResponsesService) {
        this.questService = questService;
        this.questionService = questionService;
        this.questResponsesService = questResponsesService;
    }


    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse res) {
        String selection = req.getParameter("responselist");
        String returnString = "";

        return getPage() + "?question=" + Long.parseLong(selection) ;
    }

    public String doGet(HttpServletRequest req, HttpServletResponse res) {
        ArrayList<QuestObject> responses = new ArrayList<>() ;

        String stringId = req.getParameter("id");
        String returnPage = getJspPage();


        return returnPage;
    }
}
