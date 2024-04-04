package com.javarush.khmelov.tools;

import java.util.HashMap;
import java.util.Map;

public class Tools {
    private static final Map<String, String> commandKeys = new HashMap<>();
    public Tools() {
        commandKeys.put(Keys.COMMAND_EDIT, Route.EDIT_QUEST);
        commandKeys.put(Keys.COMMAND_EXIT, Route.SELECT_QUEST);
        commandKeys.put(Keys.COMMAND_SELECT_RESPONSE, Route.SELECT_RESPONSE);
        commandKeys.put(Keys.COMMAND_SELECT_NEXT, Route.SELECT_NEXT_QUESTIONS);
        commandKeys.put(Keys.COMMAND_SELECT_QUESTION, Route.SELECT_QUESTIONS);
    }

    public static String getCommandKeys(String mapKey) {
        return commandKeys.get(mapKey);
    }

}
