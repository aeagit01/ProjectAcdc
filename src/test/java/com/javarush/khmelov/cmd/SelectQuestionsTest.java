package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import com.javarush.khmelov.BaseIT;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SelectQuestionsTest extends BaseIT {
    private final SelectQuestions selectQuestions = Winter.find(SelectQuestions.class);

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @ValueSource(strings = {Keys.COMMAND_EXIT})
    void whenExitKeyPressed_thenReturnEditQuestPage(String param) {
        String EMPTY_QUEST = "99";
        String EMPTY_QUESTION = "1";
        Mockito.when(request.getParameter(Keys.PARAMETR_ID)).thenReturn(EMPTY_QUEST);
        Mockito.when(request.getParameter(Keys.PARAMETR_QUESTION)).thenReturn(EMPTY_QUESTION);
        Mockito.when(request.getParameter(Keys.JSP_VAL_DIRECT)).thenReturn(param);
        String actualUri = selectQuestions.doPost(request, response);
        String expectedUri = "%s?%s=%s".formatted(Route.EDIT_QUEST, Keys.PARAMETR_ID, EMPTY_QUEST);
        Assertions.assertEquals(expectedUri, actualUri);
    }


}