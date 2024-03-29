package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Question;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class QuestionsRepository implements Repository{
    private final Map<Long, Question> map = new HashMap<>();

    public QuestionsRepository() {
        map.put(1L, Question.builder().id(1L).relatedObjectIDs(new long[]{2L, 3L}).description("Question 1").build());
        map.put(2L, Question.builder().id(2L).relatedObjectIDs(new long[]{1L,3L}).description("Question 2").images(new int[]{}).build());
        map.put(3L, Question.builder().id(3L).relatedObjectIDs(new long[]{4L,2L,7L}).description("Question 3").images(new int[]{}).build());
        map.put(4L, Question.builder().id(4L).relatedObjectIDs(new long[]{4L}).description("Question 4").images(new int[]{}).build());
        map.put(5L, Question.builder().id(5L).relatedObjectIDs(new long[]{4L,2L}).description("Question 5").images(new int[]{}).build());
        map.put(6L, Question.builder().id(6L).relatedObjectIDs(new long[]{4L,2L}).description("Question 6").images(new int[]{}).build());
        map.put(7L, Question.builder().id(7L).relatedObjectIDs(new long[]{3L,1L,4L}).description("Question 7").images(new int[]{}).build());

    }

    @Override
    public Collection<Question> getAll() {
        return map.values();
    }

    @Override
    public Optional<Question> get(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void create(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }
}
