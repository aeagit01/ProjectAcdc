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
    private Long position = 0L; //0-not checked, 1-first, 2-last
    private Long questID;
    private Long questionID;
    private Long responseID;
    private Long nextQuestionID;
}
