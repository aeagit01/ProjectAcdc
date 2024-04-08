package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.javarush.khmelov.BaseIT;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SelectResponseTest extends BaseIT {
    private final SelectResponse selectResponse = Winter.find(SelectResponse.class);

    @BeforeEach
    void setUp() {

    }

    @ParameterizedTest
    @ValueSource(strings = {Keys.COMMAND_NEXT, Keys.COMMAND_PREV})
    void whenNextButtonPressedAndLastOrFistQuestion_thenReturnEditQuestPage(String param) {
        Mockito.when(request.getParameter(Keys.PARAMETR_ID)).thenReturn("5");
        Mockito.when(request.getParameter(Keys.PARAMETR_QUESTION)).thenReturn("1");
        Mockito.when(request.getParameter(Keys.JSP_VAL_DIRECT)).thenReturn(param);
        String actualUri = selectResponse.doPost(request, response);
        String expectedUri = "%s?%s=%d".formatted(Route.EDIT_QUEST, Keys.PARAMETR_ID, 5);
        Assertions.assertEquals(expectedUri, actualUri);
    }


    @Test
    void doGet() {
    }

    @Test
    void doPost() {
    }


}