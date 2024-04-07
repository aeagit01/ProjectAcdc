package com.javarush.khmelov.entity;

import com.javarush.khmelov.tools.Keys;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestElement  {
    private Long id;
    private Long position; //0-not checked, 1-first, 2-last
    private Long questID;
    private Long questionID;
    private Long responseID;
    private Long nextQuestionID;

    @Override
    public int hashCode() {
        return Objects.hash(questionID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestElement that = (QuestElement) o;
        return Objects.equals(questionID, that.questionID);
    }
}
