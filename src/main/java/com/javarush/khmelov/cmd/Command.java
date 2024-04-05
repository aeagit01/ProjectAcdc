package com.javarush.khmelov.cmd;

import com.javarush.khmelov.tools.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Command {
    default String doGet(HttpServletRequest req) {
        return getJspPage();
    }
    default String doPost(HttpServletRequest req) throws ServletException, IOException {
        return getPage();
    }
//    default String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        return getJspPage();
//    }
//    default String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        return getPage();
//    }

    default String getJspPage() {
        return "WEB-INF/%s.jsp".formatted(getPage());
    }
    default String getPage() {
        String simpleName = this.getClass().getSimpleName();
        return convertCamelCaseToSnakeStyle(simpleName);
    }
    private static String convertCamelCaseToSnakeStyle(String string) {
        String snakeName = string.chars()
                .mapToObj(s -> String.valueOf((char) s))
                .flatMap(s -> s.matches("[A-Z]")
                        ? Stream.of("-", s)
                        : Stream.of(s))
                .collect(Collectors.joining())
                .toLowerCase();
        return snakeName.startsWith("-")
                ? snakeName.substring(1)
                : snakeName;
    }
}
