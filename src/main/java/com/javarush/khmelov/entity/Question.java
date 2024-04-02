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
    private Long id;
    private String description;
    private final Collection<Long> images = new ArrayList<>();

    @Override
    public Object clone() throws CloneNotSupportedException {

        Question question = Question.builder().description(this.description).build();
        for (Long image:this.images){
            question.getImages().add(image);
        }
        return question;
    }
}
