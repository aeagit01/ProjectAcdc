package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Quest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.javarush.khmelov.service.QuestService;

import java.io.IOException;
import java.util.Collection;

public class SelectQuest implements Command{
    private final QuestService questService;
    public SelectQuest(QuestService questService) {
        this.questService = questService;
    }
    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Quest> quests = questService.getAll();
        req.setAttribute("quests", quests);
        return getJspPage();
    }
}
