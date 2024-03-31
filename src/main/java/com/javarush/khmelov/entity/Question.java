package com.javarush.khmelov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question implements QuestObject, Cloneable{
    private final Collection<QuestObject> responses = new ArrayList<>();
    private final Collection<Long> images = new ArrayList<>();
    private Long questID;
    private Long id;
    private String description;

    @Override
    public Object clone() throws CloneNotSupportedException {

        Question question = Question.builder().description(this.description).build();
        for(QuestObject responses:this.responses){
            question.getResponses().add(responses);
        }
        for (Long image:this.images){
            question.getImages().add(image);
        }

        return question;

    }
}
