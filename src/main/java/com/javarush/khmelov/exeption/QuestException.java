package com.javarush.khmelov.exeption;
public class QuestException extends RuntimeException {
    public QuestException() {
        super();
    }

    public QuestException(String message) {
        super(message);
    }

    public QuestException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestException(Throwable cause) {
        super(cause);
    }
}

