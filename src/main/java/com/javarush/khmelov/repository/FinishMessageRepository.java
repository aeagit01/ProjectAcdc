package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.FinishMessage;
import com.javarush.khmelov.entity.QuestResponse;

import java.util.stream.Stream;

public class FinishMessageRepository extends QuestObjectRepository<FinishMessage> {
    public FinishMessageRepository() {
    }
    @Override
    public Stream<FinishMessage> find(FinishMessage pattern) {
        return map.values()
                  .stream()
                  .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                  .filter(u -> nullOrEquals(pattern.getDescription(), u.getDescription()));
    }
}
