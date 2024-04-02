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

}
