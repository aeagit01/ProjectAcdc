package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.entity.QuestElement;
import com.javarush.khmelov.repository.GeneralRepository;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import com.javarush.khmelov.BaseIT;


import static org.junit.jupiter.api.Assertions.*;

class EditQuestTest extends BaseIT{
    public final EditQuest editQuest = Winter.find(EditQuest.class);
    public final GeneralRepository generalRepository = Winter.find(GeneralRepository.class);
    @BeforeEach
    void setUp() {

    }

//    @ParameterizedTest
//    @ValueSource(strings = {Keys.COMMAND_NEXT, Keys.COMMAND_PREV})
    @Test
    void whenSelectQuestionButtonPressed_thenReturnSelectQuestionPage() {
        Mockito.when(request.getParameter(Keys.PARAMETR_ID)).thenReturn("1");
        Mockito.when(request.getParameter(Keys.JSP_VAL_DIRECT)).thenReturn(Keys.COMMAND_SELECT_QUESTION);
        Mockito.when(request.getParameter(Route.EDIT_QUEST)).thenReturn(Route.EDIT_QUEST);
        String actualUri = editQuest.doPost(request, response);
        String expectedUri = "%s?%s=%d".formatted(Route.SELECT_QUESTIONS, Keys.PARAMETR_ID, 1);
        Assertions.assertEquals(expectedUri, actualUri);
    }
    @Test
    void whenEditResponseButtonPressed_thenReturnSelectResponsePage() {
        Mockito.when(request.getParameter(Keys.PARAMETR_ID)).thenReturn("1");
        Mockito.when(request.getParameter(Keys.JSP_VAL_DIRECT)).thenReturn(Keys.COMMAND_SELECT_RESPONSE);
        Mockito.when(request.getParameter(Route.EDIT_QUEST)).thenReturn(Route.EDIT_QUEST);
        String actualUri = editQuest.doPost(request, response);
        String expectedUri = "%s?%s=%d&%s=%s".formatted(Route.SELECT_RESPONSE,
                                                                Keys.PARAMETR_ID, 1,
                                                                Keys.PARAMETR_QUESTION,
                                                                "2");
        Assertions.assertEquals(expectedUri, actualUri);
    }
    @Test
    void whenEditNextQuestionButtonPressed_thenReturnSelectNextQuestionPage() {
        Mockito.when(request.getParameter(Keys.PARAMETR_ID)).thenReturn("1");
        Mockito.when(request.getParameter(Keys.JSP_VAL_DIRECT)).thenReturn(Keys.COMMAND_SELECT_NEXT);
        Mockito.when(request.getParameter(Route.EDIT_QUEST)).thenReturn(Route.EDIT_QUEST);
        String actualUri = editQuest.doPost(request, response);
        String expectedUri = "%s?%s=%d&%s=%s".formatted(Route.SELECT_NEXT_QUESTIONS,
                Keys.PARAMETR_ID, 1,
                Keys.PARAMETR_RESPONSE,
                "3");
        Assertions.assertEquals(expectedUri, actualUri);
    }
    @Test
    void whenExitButtonPressed_thenReturnSelectQuestPage() {
        Mockito.when(request.getParameter(Keys.PARAMETR_ID)).thenReturn("1");
        Mockito.when(request.getParameter(Keys.JSP_VAL_DIRECT)).thenReturn(Keys.COMMAND_EXIT);
        Mockito.when(request.getParameter(Route.EDIT_QUEST)).thenReturn(Route.EDIT_QUEST);
        String actualUri = editQuest.doPost(request, response);
        String expectedUri = "%s?%s=%d".formatted(Route.SELECT_QUEST,
                Keys.PARAMETR_ID, 1);
        Assertions.assertEquals(expectedUri, actualUri);
    }



}