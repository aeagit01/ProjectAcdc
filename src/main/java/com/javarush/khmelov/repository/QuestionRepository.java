package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.FinishMessage;
import java.util.stream.Stream;
import com.javarush.khmelov.entity.Question;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Random;

public class QuestionRepository extends QuestObjectRepository<Question>{
        public QuestionRepository() {
                map.put(1L, Question.builder().id(1L).description("Question 1").build());
                map.put(2L, Question.builder().id(2L).description("Question 2").build());
                map.put(3L, Question.builder().id(3L).description("Question 3").build());
                map.put(4L, Question.builder().id(4L).description("Question 4").build());
                map.put(5L, Question.builder().id(5L).description("Question 5").build());
                map.put(6L, Question.builder().id(6L).description("Question 6").build());
                map.put(7L, Question.builder().id(7L).description("Question 7").build());
                map.put(8L, Question.builder().id(8L).description("Question 8").build());
                map.put(9L, Question.builder().id(9L).description("Question 9").build());
        }
        @Override
        public Stream<Question> find(Question pattern) {
                return map.values()
                        .stream()
                        .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                        .filter(u -> nullOrEquals(pattern.getDescription(), u.getDescription()));
        }
}
