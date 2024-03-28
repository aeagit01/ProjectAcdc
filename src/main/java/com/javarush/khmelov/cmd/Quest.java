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
        return Command.super.doPost(req, resp);
    }

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<QuestResponse> responses = new ArrayList<>() ;
        String stringId = req.getParameter("id");
        String stringQuestions = req.getParameter("questions");
        String stringStart = req.getParameter("start");

        Optional<com.javarush.khmelov.entity.Quest> quest = questService.get(Integer.parseInt(stringId));
        com.javarush.khmelov.entity.Quest questObject = quest.isPresent()?quest.get():
                                                        com.javarush.khmelov.entity.Quest.builder()
                                                                                         .name("Пустой")
                                                                                         .description("Данный квест не заполнен!!!")
                                                                                         .build();

        Optional<Question> question = questionsRepository.get(questObject.getNextQuestionID());
        Question questionObject = question.isPresent()? question.get():
                                                        Question.builder()
                                                                .name(Keys.EMPTY)
                                                                .description("Такого вопроса не существует")
                                                                .build();
    if (questionObject.getRelatedObjectIDs().length!=0){
        long[] responsesIds = questionObject.getRelatedObjectIDs();
        for(long id:responsesIds){
            Optional<QuestResponse> responseObject = responsesRepository.get(id);
            responses.add(responseObject.get());
        }
    }

        req.setAttribute("question", questionObject);
        req.setAttribute("responses", responses.isEmpty()?null:responses);

        return getJspPage();
    }

}
