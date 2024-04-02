package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.*;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class SelectResponse implements Command{

    QuestionService questionService;
    QuestService questService;
    GeneralService generalService;
    QuestResponsesService questResponsesService;
    FinishMessageService finishMessageService;
    Integer step = 0;
    Question selectedQuestion;

    public SelectResponse(QuestionService questionService, QuestService questService, GeneralService generalService,
                          QuestResponsesService questResponsesService, FinishMessageService finishMessageService) {
        this.questionService = questionService;
        this.questService = questService;
        this.generalService = generalService;
        this.questResponsesService = questResponsesService;
        this.finishMessageService = finishMessageService;

    }

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long questId = Long.parseLong(req.getParameter("q"));
        selectedQuestion = questionService.get(questId);
        req.setAttribute("question", selectedQuestion);


        Collection<QuestResponse> responseslist = questResponsesService.getAll();
        req.setAttribute("responses", responseslist.toArray());

        Collection<com.javarush.khmelov.entity.Question> questionlist = questionService.getAll();
        req.setAttribute("questions", questionlist.toArray());

        Collection<FinishMessage> finmessagelist = finishMessageService.getAll();
        req.setAttribute("finmessages", finmessagelist.toArray());

        return getJspPage(); //getPage()+"?q="+questId;//
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long questId = Long.parseLong(req.getParameter("q"));

        String direction = req.getParameter("direct");

        if(direction.equals("next")){
        }
        if(direction.equals("prev")){
            step = step>1?step - 1:step;
        }

        return getPage()+"?q="+questId;
    }
}
