package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.FinishMessage;
import com.javarush.khmelov.entity.Quest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FinishMessageReporitory implements Repository{
    private final Map<Long, FinishMessage> map = new HashMap<>();
    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());
    public FinishMessageReporitory() {
        map.put(1L, FinishMessage.builder().id(1L).name("Finish 1").images(new int[]{2}).description("Description Finish 1").build());
        map.put(2L, FinishMessage.builder().id(2L).name("Finish 2").images(new int[]{1}).description("Description Finish 2").build());
        map.put(3L, FinishMessage.builder().id(3L).name("Finish 3").images(new int[]{4}).description("Description Finish 3").build());
        map.put(4L, FinishMessage.builder().id(4L).name("Finish 4").images(new int[]{2}).description("Description Finish 4").build());
    }

    @Override
    public Collection getAll() {
        return map.values();
    }

    @Override
    public Optional get(long id) {
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
