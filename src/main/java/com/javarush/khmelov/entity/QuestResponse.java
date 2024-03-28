package com.javarush.khmelov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Random;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestResponse{
    private long id;
    private String description;
    private int [] images;
    private long [] relatedObjectIDs;

    public Long getNextQuestionID(){
        long [] questionsIDs = this.getRelatedObjectIDs();
        return questionsIDs[(int) (new Random()).nextLong(questionsIDs.length)];
    }

//    @Override
//    public int[] getRelatedObjectIDs() {
//        return Arrays.stream(this.getRelatedObjectIDs()).toArray();
//    }
//    @Override
//    public void setRelatedObjectIDs(int[] relatedObjectIDs) {
//        this.setRelatedObjectIDs(relatedObjectIDs);
//    }

}
