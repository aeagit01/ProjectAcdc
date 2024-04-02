package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.*;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.service.GeneralService;
import com.javarush.khmelov.service.QuestResponsesService;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.service.QuestionService;
import com.javarush.khmelov.tools.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class SelectQuestions implements Command{
    QuestionService questionService;
    QuestService questService;
    GeneralService generalService;
    QuestResponsesService questResponsesService;
    Integer step = 0;
    Quest selectedQuest;

    public SelectQuestions(QuestionService questionService, QuestService questService, GeneralService generalService) {
        this.questionService = questionService;
        this.questService = questService;
        this.generalService = generalService;
    }
    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long questId = Long.parseLong(req.getParameter("q"));
        selectedQuest = questService.get(questId);

//        List<Question> chkquestion = questionService.getQuestQuestion(questId);
//        Collection<Question> allQuestions = questionService.getAll();
//        for (com.javarush.khmelov.entity.Question question:chkquestion){
//            allQuestions.remove(chkquestion);
//        }
//        req.setAttribute("chkquestions", chkquestion.toArray());
//        req.setAttribute("questions", allQuestions.toArray());
        req.setAttribute("quest", selectedQuest);

        return getJspPage(); //getPage()+"?q="+questId;//
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long questId = Long.parseLong(req.getParameter("q"));
        String urlPath = getPage();
        String editKey = req.getParameter("edit");

        String direction = req.getParameter("direct");

        if(direction!=null && direction.equals("next")){
            step = step + 1;
//            QuestElement questElement = getQuestElement(req,questId);
//            if (questElement == null){
//                generalService.create(QuestElement.builder().questID(questId).build());
//                questElement = getQuestElement(req,questId);
//            }
//            UpdateQuestElement(req, questElement);
        }
        if(direction!=null && direction.equals("prev")){
            step = step>1?step - 1:step;
        }

        return urlPath +"?q="+questId;
    }

    public void UpdateQuestElement(HttpServletRequest req, QuestElement questElement){
//        String description = req.getParameter("description");
//        String[] selectedQuestion = req.getParameterValues("questionchk");
//        updateQuestionList(questElement, selectedQuestion);
//        String[] selectedResponse = req.getParameterValues("responsechk");
//        Long questionID = Long.parseLong(req.getParameter("questionID"));
//        updateResponseListElement(questElement, selectedResponse, questionID);
//        String[] selectedNextQuestion = req.getParameterValues("nextQuestioncnk");
//        String[] selectedFinishMessage = req.getParameterValues("finishMessagecnk");
//        Long resposeID = Long.parseLong(req.getParameter("resonseID"));
//        updateNextQuestionListElement(questElement, selectedResponse, resposeID);
    }


}
