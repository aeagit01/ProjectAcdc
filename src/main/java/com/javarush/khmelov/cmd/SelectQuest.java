package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.QuestElement;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.service.GeneralService;
import com.javarush.khmelov.tools.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.javarush.khmelov.service.QuestService;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public class SelectQuest implements Command{
    private final QuestService questService;
    private final GeneralService generalService;
    public SelectQuest(QuestService questService, GeneralService generalService) {
        this.questService = questService;
        this.generalService = generalService;
    }
    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse res) {
        Collection<Quest> quests = questService.getAll();
        req.setAttribute("quests", quests);
        return getJspPage();
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse res){
        Question questObject;
        String routeLink = "select-quest";
        String questID = "1";

        String editKey = req.getParameter("edit");
        String startKey = req.getParameter("start");
        questID = editKey==null?startKey:questID;
        questID = startKey==null?editKey:questID;
        routeLink = editKey!=null?"edit-quest":"quest";
        String suffix = routeLink==Keys.JSP_VAL_QUEST
                        ?"&q=%s".formatted(getFirstQuestion(questID).toString()):"";

        String selectedPage = "%s?id=%s".formatted(routeLink,questID) + suffix;

        return  selectedPage;
    }

    private Long getFirstQuestion(String questID){

        Long firstQuestionId = 0L;
        QuestElement pattern = QuestElement.builder()
                                .questID(Long.parseLong(questID))
                                .position(Keys.ELEMENT_FIRST).build();
        Optional<QuestElement> questElement = generalService.find(pattern).findFirst();
        if (questElement.isPresent()){
            firstQuestionId = questElement.get().getQuestionID();
        }
        return firstQuestionId;
    }
}
