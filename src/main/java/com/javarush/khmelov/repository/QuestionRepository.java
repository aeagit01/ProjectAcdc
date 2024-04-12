package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.FinishMessage;

import java.util.stream.Stream;

import com.javarush.khmelov.entity.Question;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Random;

public class QuestionRepository extends QuestObjectRepository<Question> {
    public QuestionRepository() {
    }

    @Override
    public Stream<Question> find(Question pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getDescription(), u.getDescription()));
    }
}
