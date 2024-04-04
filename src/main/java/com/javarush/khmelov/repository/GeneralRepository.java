package com.javarush.khmelov.repository;

import java.util.*;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;


import com.javarush.khmelov.entity.QuestElement;

public class GeneralRepository implements Repository<QuestElement>{
    public final AtomicLong id = new AtomicLong(0L);
    protected final Map<Long, QuestElement> map = new ConcurrentHashMap<>();


    @Override
    public Collection<QuestElement> getAll() {
        return null;
    }

    @Override
    public Stream<QuestElement> find(QuestElement pattern) {
        return map.values()
                .stream()
                .filter(u->nullOrEquals(pattern.getId(),u.getId()))
                .filter(u->nullOrEquals(pattern.getEnd(), u.getEnd()))
                .filter(u->nullOrEquals(pattern.getQuestID(),u.getQuestID()))
                .filter(u->nullOrEquals(pattern.getQuestionID(),u.getQuestionID()))
                .filter(u->nullOrEquals(pattern.getNextQuestionID(),u.getNextQuestionID()))
                ;
    }
    @Override
    public QuestElement get(long id) {
        return map.get(id);
    }
    @Override
    public void create(QuestElement entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }
    @Override
    public void update(QuestElement entity) {
        map.put(entity.getId(), entity);
    }
    @Override
    public void delete(QuestElement entity) {
        map.remove(entity.getId());
    }
    public List<QuestElement> getByQuest(Long questID){
        return map.values().stream().filter(q->questID.equals(q.getQuestID())).findFirst().stream().toList();
    }
    protected boolean nullOrEquals(Object patternField, Object repoField) {
        return patternField == null || patternField.equals(repoField);
    }
}
