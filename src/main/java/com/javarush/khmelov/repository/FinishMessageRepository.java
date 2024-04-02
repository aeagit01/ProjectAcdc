package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.FinishMessage;
import com.javarush.khmelov.entity.QuestResponse;

import java.util.stream.Stream;

public class FinishMessageRepository extends QuestObjectRepository<FinishMessage> {
    public FinishMessageRepository() {
        map.put(1L, FinishMessage.builder().id(1L).description("Finish message 1").build());
        map.put(2L, FinishMessage.builder().id(2L).description("Finish message  2").build());
        map.put(3L, FinishMessage.builder().id(3L).description("Finish message  3").build());
        map.put(4L, FinishMessage.builder().id(4L).description("Finish message  4").build());
    }
    @Override
    public Stream<FinishMessage> find(FinishMessage pattern) {
        return map.values()
                  .stream()
                  .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                  .filter(u -> nullOrEquals(pattern.getDescription(), u.getDescription()));
    }
}
