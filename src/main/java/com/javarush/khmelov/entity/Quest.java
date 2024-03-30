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
public class Quest implements QuestObject{
    private final Collection<QuestObject> question = new ArrayList<>();
    private final Collection<Long> images = new ArrayList<>();
    private Long id;
    private String name;
    private String description;
}
