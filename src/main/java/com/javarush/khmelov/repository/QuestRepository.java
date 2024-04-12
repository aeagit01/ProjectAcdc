package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Quest;

import java.util.stream.Stream;

public class QuestRepository extends QuestObjectRepository<Quest> {

    public QuestRepository() {
    }

    @Override
    public Stream<Quest> find(Quest pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getDescription(), u.getDescription()))
                .filter(u -> nullOrEquals(pattern.getName(), u.getName()));
    }

}



