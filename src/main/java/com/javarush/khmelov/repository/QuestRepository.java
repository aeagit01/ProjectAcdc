package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Quest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class QuestRepository implements Repository{
    private final Map<Long, Quest> map = new HashMap<>();
    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());
    public QuestRepository() {
        map.put(1L, Quest.builder().id(1L).name("Quest 1").relatedObjectIDs(new long[]{2L, 3L}).description("Description quiest 1").build());
        map.put(2L, Quest.builder().id(2L).name("Quest 2").relatedObjectIDs(new long[]{1L, 2L, 3L}).description("Description quiest 2").build());
        map.put(3L, Quest.builder().id(3L).name("Quest 3").relatedObjectIDs(new long[]{4L, 1L}).description("Description quiest 3").build());
        map.put(4L, Quest.builder().id(4L).name("Quest 4").relatedObjectIDs(new long[]{2L, 4L, 1L}).description("Description quiest 4").build());
    }
    @Override
    public Collection<Quest> getAll() {
        return map.values();
    }
    @Override
    public Optional<Quest> get(long id) {
        return Optional.of(map.get(id));
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
