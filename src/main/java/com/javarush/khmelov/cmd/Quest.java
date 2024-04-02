package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.FinishMessage;
import com.javarush.khmelov.entity.QuestObject;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.service.QuestResponsesService;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.service.QuestionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Quest implements Command{
    private final QuestService questService;
    private final QuestionService questionService;
    private final QuestResponsesService questResponsesService;

    public Quest(QuestService questService, QuestionService questionService, QuestResponsesService questResponsesService) {
        this.questService = questService;
        this.questionService = questionService;
        this.questResponsesService = questResponsesService;
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selection = req.getParameter("responselist");
        String questID = req.getParameter("questID");
        return getPage() + "?q="+ Long.parseLong(questID) +",id=" + Long.parseLong(selection) ;
    }

    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<QuestObject> responses = new ArrayList<>() ;

        String stringID = req.getParameter("id");
        String questID = req.getParameter("questID");
        String returnPage = getJspPage();

        Question questObject = questionService.get(Long.parseLong(stringID));
        req.setAttribute("question", questObject);
//        req.setAttribute("responses", questObject.getResponses().isEmpty() ? null : questObject.getResponses());

        return returnPage;
    }

}
