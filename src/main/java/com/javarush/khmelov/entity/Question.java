package com.javarush.khmelov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question{
    private long id;
    private String name;
    private String description;
    private int [] images;
    private long [] relatedObjectIDs;
//    @Override
//    public int[] getRelatedObjectIDs() {
//        return Arrays.stream(this.getRelatedObjectIDs()).toArray();
//    }

}
