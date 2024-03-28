package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.QuestResponse;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.repository.QuestionsRepository;
import com.javarush.khmelov.repository.ResponsesRepository;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.tools.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Quest implements Command{
    private final QuestService questService;
    private final QuestionsRepository questionsRepository;
    private final ResponsesRepository responsesRepository;
    public Quest(QuestService questService, QuestionsRepository questionsRepository, ResponsesRepository responsesRepository) {
        this.questService = questService;
        this.questionsRepository = questionsRepository;
        this.responsesRepository = responsesRepository;
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selection = req.getParameter("responselist");

        return getPage() + "?question=" + Long.parseLong(selection) ;
    }

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<QuestResponse> responses = new ArrayList<>() ;
        String stringId = req.getParameter("id");
        String stringQuestions = req.getParameter("question");
        String stringStart = req.getParameter("start");

        Long quiestionId = stringQuestions!=null?Long.parseLong(stringQuestions):0;
        if(stringId!=null){
            com.javarush.khmelov.entity.Quest questObject = getQuest(Long.parseLong(stringId));
            quiestionId = questObject.getNextQuestionID();
        }

        Question questionObject = getQuestion(quiestionId);
        ArrayList<QuestResponse > responseList = getResponseList(questionObject);

        req.setAttribute("question", questionObject);
        req.setAttribute("responses", responseList.isEmpty()?null:responseList);

        return getJspPage();
    }


    private com.javarush.khmelov.entity.Quest getQuest(long id){
        Optional<com.javarush.khmelov.entity.Quest> quest = questService.get(id);
        com.javarush.khmelov.entity.Quest questObject = quest.isPresent()?quest.get():
                com.javarush.khmelov.entity.Quest.builder()
                        .name("Пустой")
                        .description("Данный квест не заполнен!!!")
                        .build();
        return questObject;
    }
    private Question getQuestion(long id){
        Optional<Question> question = questionsRepository.get(id);
        Question questionObject = question.isPresent()? question.get():
                Question.builder()
                        .name(Keys.EMPTY)
                        .description("Такого вопроса не существует")
                        .build();
        return questionObject;
    }

    private ArrayList<QuestResponse> getResponseList(Question question) {

        ArrayList<QuestResponse> responseList = new ArrayList<>();

        if (question.getRelatedObjectIDs().length != 0) {
            long[] responsesIds = question.getRelatedObjectIDs();
            for (long id : responsesIds) {
                Optional<QuestResponse> responseObject = responsesRepository.get(id);
                responseList.add(responseObject.get());
            }
        }
        return responseList;
    }


}
