package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.*;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SelectResponse implements Command{

    QuestionService questionService;
    QuestService questService;
    GeneralService generalService;
    QuestResponsesService questResponsesService;
    FinishMessageService finishMessageService;
    Integer questionId = 1;
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
        ArrayList<QuestResponse> chkresponses = new ArrayList<>();
        Long questId = Long.parseLong(req.getParameter("id"));
        Long questionId = Long.parseLong(req.getParameter("q"));

        selectedQuestion = questionService.get(questionId);
        req.setAttribute("question", selectedQuestion);

        QuestElement pattern = QuestElement.builder().questionID(questionId).questID(questId).build();
        ArrayList<QuestElement> questElementList = (ArrayList<QuestElement>) generalService.find(pattern).collect(Collectors.toList());
        Collection<QuestResponse> allResponses = new ArrayList<>(questResponsesService.getAll());
        for (QuestElement questElement:questElementList){
            QuestResponse foundQuestion = questResponsesService.get(questElement.getResponseID());
            allResponses.remove(foundQuestion);
            chkresponses.add(foundQuestion);
        }

        req.setAttribute("responsechk", chkresponses);
        req.setAttribute("responses", allResponses);

        return getJspPage(); //getPage()+"?q="+questId;//
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long questId = Long.parseLong(req.getParameter("id"));

        String direction = req.getParameter("direct");

        if(direction.equals("next")){
            questionId = questionId + 1;
        }
        if(direction.equals("prev")){
            questionId = questionId>1?questionId - 1:questionId;
        }
        updateResponsesElenments(req);

        return getPage()+"?id="+questId + "&q=" + questionId;
    }
    private void updateResponsesElenments(HttpServletRequest req){
        Long questId = Long.parseLong(req.getParameter("id"));
        Long questionId = Long.parseLong(req.getParameter("q"));
        QuestElement pattern = QuestElement.builder().questionID(questionId).questID(questId).build();
        ArrayList<QuestElement> questElementList = (ArrayList<QuestElement>) generalService.find(pattern).collect(Collectors.toList());

        String[] checkedResponses = req.getParameterValues("responsechk");
        if (checkedResponses!=null) {
            List<String> selectedResponses = Arrays.asList(checkedResponses);
            List<String> tmpCheck = new ArrayList<String>(selectedResponses);

            for (QuestElement questElement : questElementList) {
                String checked = questElement.getResponseID().toString();
                if (selectedResponses.indexOf(checked) == -1) {
                    generalService.delete(questElement);
                } else {
                    tmpCheck.remove(checked);
                }
            }
            for (String newResponse : tmpCheck) {
                generalService.create(QuestElement.builder()
                        .questID(questId)
                        .questionID(questionId)
                        .responseID(Long.parseLong(newResponse)).build());
            }
        }
    }

}
