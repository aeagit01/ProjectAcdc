package com.javarush.khmelov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestElement  {
    private Long id;
    private Long questID;
    private final Map<Question, ArrayList<QuestResponse>> responseLink = new HashMap<>();
    private final Map<QuestResponse, ArrayList<QuestObject>> nextQuestionLink = new HashMap<>();
}
