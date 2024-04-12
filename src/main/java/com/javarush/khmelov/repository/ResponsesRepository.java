package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.QuestResponse;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ResponsesRepository implements Repository {
    private final Map<Long, QuestResponse> map = new HashMap<>();

    public ResponsesRepository() {

    }

    @Override
    public Collection<QuestResponse> getAll() {
        return map.values();
    }

    @Override
    public Stream find(Object pattern) {
        return null;
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
