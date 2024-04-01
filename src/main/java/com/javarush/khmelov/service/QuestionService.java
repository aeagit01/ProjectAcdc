package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.repository.QuestionRepository;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Stream;

public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void create(Question question) {
        questionRepository.create(question);
    }

    public void update(Question question) {
        questionRepository.update(question);
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }

    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    public Question get(long id) {
        return (Question) questionRepository.get(id);
    }

    public List<Question> getQuestQuestion(long questID){
        Question pattern = Question.builder().questID(questID).build();
         List<Question> currentQuestions =  questionRepository.find(pattern).toList();
//                                             .findFirst(); //max(Comparator.comparingLong(Question::getId)
//        if (!currentQuestion.isPresent()){
//            pattern = Question.builder().questID(null).id(id).build();
//            Optional<Question> templateQuestion = questionRepository.find(pattern).findFirst();
//            currentQuestion = templateQuestion.isPresent()?
//                              Optional.ofNullable((Question) templateQuestion.get().clone()):
//                              currentQuestion;
//            currentQuestion.get().setQuestID(questID);
//            currentQuestion.get().setId(id);
//            create(currentQuestion.get());
//            pattern = Question.builder().questID(questID).id(id).build();
//            currentQuestion = questionRepository.find(pattern).findFirst();
//        }
        return currentQuestions;
    }

}
