package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.FinishMessage;
import com.javarush.khmelov.entity.QuestResponse;
import com.javarush.khmelov.entity.Question;

import java.util.Random;
import java.util.stream.Stream;

public class QuestResponsesRepository extends QuestObjectRepository<QuestResponse>{
    public QuestResponsesRepository() {
        map.put(1L, QuestResponse.builder().id(1L).description("Response 1").build());
        map.put(2L, QuestResponse.builder().id(2L).description("Response 2").build());
        map.put(3L, QuestResponse.builder().id(3L).description("Response 3").build());
        map.put(4L, QuestResponse.builder().id(4L).description("Response 4").build());
        map.put(5L, QuestResponse.builder().id(5L).description("Response 5").build());
        map.put(6L, QuestResponse.builder().id(6L).description("Response 6").build());
        map.put(7L, QuestResponse.builder().id(7L).description("Response 7").build());
        map.put(8L, QuestResponse.builder().id(8L).description("Response 8").build());
//                .getNextQuestions().add(FinishMessage
//                        .builder()
//                        .id(0L)
//                        .description("Finish Good!!!")
//                        .image(0L)
//                        .build()));

        map.put(9L, QuestResponse.builder().id(9L).description("Response 9").build());

        map.put(10L, QuestResponse.builder().id(10L).description("Response 10").build());
//                .getNextQuestions().add(FinishMessage
//                        .builder()
//                        .id(0L)
//                        .description("Finish Very Good!!!")
//                        .image(0L)
//                        .build()));
        map.put(11L, QuestResponse.builder().id(11L).description("Response 11").build());
        map.put(12L, QuestResponse.builder().id(12L).description("Response 12").build());
        map.put(13L, QuestResponse.builder().id(13L).description("Response 13").build());
        map.put(14L, QuestResponse.builder().id(14L).description("Response 14").build());
    }

    @Override
    public Stream<QuestResponse> find(QuestResponse pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getDescription(), u.getDescription()))
                .filter(u -> nullOrEquals(pattern.getNextQuestions(), u.getNextQuestions()))
                .filter(u -> nullOrEquals(pattern.getImages(), u.getImages()));
    }
}
