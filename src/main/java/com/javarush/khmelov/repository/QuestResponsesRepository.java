package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.FinishMessage;
import com.javarush.khmelov.entity.QuestResponse;
import com.javarush.khmelov.entity.Question;

import java.util.Random;
import java.util.stream.Stream;

public class QuestResponsesRepository extends QuestObjectRepository<QuestResponse> {
    public QuestResponsesRepository() {
    }

    @Override
    public Stream<QuestResponse> find(QuestResponse pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getDescription(), u.getDescription()));
    }
}
