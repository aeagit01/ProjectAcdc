package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.QuestResponse;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ResponsesRepository implements Repository{
    private final Map<Long, QuestResponse> map = new HashMap<>();

    public ResponsesRepository() {
        map.put(1L, QuestResponse.builder()
                 .id(1L)
                 .description("Response1")
                 .relatedObjectIDs(new long[]{2L,3L})
                 .images(new int[]{}).build());

        map.put(2L, QuestResponse.builder()
                .id(2L)
                .description("Response2")
                .relatedObjectIDs(new long[]{2L,3L})
                .images(new int[]{}).build());
        map.put(3L, QuestResponse.builder()
                .id(3L)
                .description("Response3")
                .relatedObjectIDs(new long[]{})
                .images(new int[]{}).build());
        map.put(4L, QuestResponse.builder()
                .id(4L)
                .description("Response4")
                .relatedObjectIDs(new long[]{1L,3L})
                .images(new int[]{}).build());
        map.put(5L, QuestResponse.builder()
                .id(5L)
                .description("Response5")
                .relatedObjectIDs(new long[]{4L,2L})
                .images(new int[]{}).build());
    }

    @Override
    public Collection<QuestResponse> getAll() {
        return map.values();
    }

    @Override
    public Optional get(long id) {
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
