package com.javarush.khmelov.cmd;

import com.javarush.khmelov.exeption.QuestException;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static com.javarush.khmelov.tools.Keys.WEB_INF;

@Log4j2
public class Help implements Command {

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse res) {

        Path dataPath = WEB_INF.resolve(Route.DATA_PATH + Keys.HELP_FILE);
        try (Stream<String> questData = Files.lines(Paths.get(dataPath.toUri()))) {
            List<String> list = questData.toList();
            req.setAttribute("helplines", list);
        } catch (IOException e) {
            log.error("Error load help data");
            throw new QuestException(e);
        }
        return getJspPage();
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String commandKey = Route.SELECT_QUEST;

        return commandKey;
    }

}
