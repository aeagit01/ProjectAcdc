package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
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

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question questObject;
        String routeLink = "select-quest";
        Long questID = 1L;

        String editKey = req.getParameter("edit");
        String startKey = req.getParameter("start");
        questID = editKey==null?Long.parseLong(startKey):questID;
        questID = startKey==null?Long.parseLong(editKey):questID;
        routeLink = editKey!=null?"edit-quest":"quest";

        String selectedPage = "%s?id=".formatted(routeLink) + questID;

        return  selectedPage;
    }

}
