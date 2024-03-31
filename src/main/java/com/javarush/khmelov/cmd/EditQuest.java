package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.FinishMessage;
import com.javarush.khmelov.entity.QuestObject;
import com.javarush.khmelov.entity.QuestResponse;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.repository.QuestionRepository;
import com.javarush.khmelov.service.FinishMessageService;
import com.javarush.khmelov.service.QuestResponsesService;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.service.QuestionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class EditQuest implements Command{
    private final QuestService questService;
    private final QuestionService questionService;
    private final QuestResponsesService questResponsesService;
    private final QuestionRepository questionRepository;
    private final FinishMessageService finishMessageService;

    public EditQuest(QuestService questService, QuestionService questionService, QuestResponsesService questResponsesService, QuestionRepository questionRepository, FinishMessageService finishMessageService) {
        this.questService = questService;
        this.questionService = questionService;
        this.questResponsesService = questResponsesService;
        this.questionRepository = questionRepository;
        this.finishMessageService = finishMessageService;
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question questObject;
        Long questID = Long.parseLong(req.getParameter("q"));
        Long questionID = Long.parseLong(req.getParameter("id"));

        if (checkQuestion(req)) {
            try{
                questObject = questionService.getQuestQuestion(questID, questionID);
                updateQuestion(req, questObject);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
        Boolean newQuestion = Boolean.parseBoolean(req.getParameter("newQuestion"));
        String direct = req.getParameter("direct");

        if (direct.equals("prev")) {
            questionID = questionID > 1 ? questionID - 1 : questionID;
        } else if (direct.equals("next")) {
            questionID = questionID + 1;
        }


        return getPage() + "?q=" + questID + "&id=" + questionID;
    }

    private void updateQuestion(HttpServletRequest req,Question question){
        String description = req.getParameter("description");
        question.setDescription(description);
//        updateImages(req, question);
        updateRelatadObj(req, question);
        questionService.update(question);
    }
    private void updateRelatadObj(HttpServletRequest req, Question question) {
        String[] selectedResponse = req.getParameterValues("responsechk");
        question.getResponses().clear();
        for (String ResponseID : selectedResponse){
            question.getResponses().add(questResponsesService.get(Long.parseLong(ResponseID)));
        }
    }
//    private void updateImages(HttpServletRequest req, Question question) {
//        String[] selectedResponse = req.getParameterValues("images");
//        for (String ResponseID : selectedResponse){
//            question.getImages().add(imageService.get(Long.parseLong(ResponseID)));
//        }
//    }

    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question questObject;
        Long questionID = Long.parseLong(req.getParameter("id"));
        Long questID = Long.parseLong(req.getParameter("q"));
        String returnPage = getJspPage();
        try{
            questObject = questionService.getQuestQuestion(questID,questionID);
            req.setAttribute("question", questObject);
            ArrayList[] responses = getResponses(questObject);
            ArrayList[] fnmessage = getFinishMessages();
            req.setAttribute("fnmessages", fnmessage[0]);
            req.setAttribute("chkedresponses", responses[0]);
            req.setAttribute("responses", responses[1]);

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        ;
//        Question questObject = checkQuestion(req);


        return returnPage;
    }

    private Boolean checkQuestion(HttpServletRequest req){
        return !(req.getParameterValues("responsechk") == null || req.getParameterValues("description").toString().isBlank());
    }

    private ArrayList[] getFinishMessages(){
        ArrayList<QuestObject> allFinishMessages = new ArrayList<QuestObject>(finishMessageService.getAll());
        return new ArrayList[] {allFinishMessages};
    }
    private ArrayList[] getResponses(Question question){

        ArrayList<QuestObject> checkedResponse = (ArrayList<QuestObject>) question.getResponses();
        ArrayList<QuestObject> allResponses = new ArrayList<QuestObject>(questResponsesService.getAll());
        for(QuestObject response: checkedResponse){
            allResponses.remove(response);
        }

        return new ArrayList[]{checkedResponse, allResponses};
    }

    private Question setQuestion(HttpServletRequest req, Long questionID){
        Question question = Question.builder()
                .questID(Long.parseLong(req.getParameter("q")))
                .description(req.getParameter("description"))
                .id(Long.parseLong(req.getParameter("id"))).build();
        Collection<QuestObject> responses = question.getResponses();
        responses.forEach(response ->{
        } );
        questionRepository.create(question);
        String description = req.getParameter("question");
        question.setDescription(req.getParameter("question"));
        return question;
    }
}