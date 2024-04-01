package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
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
    Integer step = 0;
    Quest selectedQuest;
    public SelectQuestions(QuestionService questionService, QuestService questService) {
        this.questionService = questionService;
        this.questService = questService;
    }
    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long questId = Long.parseLong(req.getParameter("q"));
        selectedQuest = questService.get(questId);

        List<Question> chkquestion = questionService.getQuestQuestion(questId);
        Collection<Question> allQuestions = questionService.getAll();
        for (com.javarush.khmelov.entity.Question question:chkquestion){
            allQuestions.remove(chkquestion);
        }
        req.setAttribute("chkquestions", chkquestion.toArray());
        req.setAttribute("questions", allQuestions.toArray());
        req.setAttribute("quest", selectedQuest);

        return getJspPage(); //getPage()+"?q="+questId;//
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long questId = Long.parseLong(req.getParameter("q"));
        Quest quest = questService.get(questId);
        updateRelatadObj(req, quest);
        String direction = req.getParameter("direct");

        if(direction.equals("next")){
            step = step + 1;
        }
        if(direction.equals("prev")){
            step = step>1?step - 1:step;
        }
//        Quest quest = questService.get(questId);
//        ArrayList<Question> chkquestion = questionService.getQuestQuestion(questId);
//        ArrayList<Question> allQuestions = (ArrayList<Question>) questionService.getAll();
//        for (com.javarush.khmelov.entity.Question question:chkquestion){
//            allQuestions.remove(chkquestion);
//        }
//        req.setAttribute("chkquestions", chkquestion);
//        req.setAttribute("questions", allQuestions);
//        req.setAttribute("quest", quest);

        return getPage()+"?q="+questId;
    }

    private void updateRelatadObj(HttpServletRequest req, Quest quest) {
        String[] selectedQuestion = req.getParameterValues("questionchk");
        quest.getQuestion().clear();
        for (String ResponseID : selectedQuestion){
            quest.getQuestion().add(questionService.get(Long.parseLong(ResponseID)));
        }
        questService.update(quest);
    }
}
