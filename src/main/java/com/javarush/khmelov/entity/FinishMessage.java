package com.javarush.khmelov.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinishMessage extends QuestObject{
    private long id;
    private String name;
    private String description;
    private int [] images;


}
