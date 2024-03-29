package com.javarush.khmelov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Random;

@Data
@Builder

public class QuestResponse extends QuestObject{
    private long id;
    private String description;
    private int [] images;
    private long [] relatedObjectIDs;

//    @Override
//    public int[] getRelatedObjectIDs() {
//        return Arrays.stream(this.getRelatedObjectIDs()).toArray();
//    }
//    @Override
//    public void setRelatedObjectIDs(int[] relatedObjectIDs) {
//        this.setRelatedObjectIDs(relatedObjectIDs);
//    }

}
