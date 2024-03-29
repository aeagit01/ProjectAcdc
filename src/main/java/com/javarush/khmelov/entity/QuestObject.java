package com.javarush.khmelov.entity;

import lombok.*;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class QuestObject {
    private long id;
    private String name;
    private String description;
    private int [] images;
    private long [] relatedObjectIDs;

    public Long getNextQuestionID(){
        long [] questionsIDs = this.getRelatedObjectIDs();
        return questionsIDs[(int) (new Random()).nextLong(questionsIDs.length)];
    }

}
