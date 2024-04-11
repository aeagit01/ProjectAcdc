package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.service.GeneralService;
import com.javarush.khmelov.tools.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.javarush.khmelov.service.QuestService;

import java.util.Collection;

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
        String suffix = Keys.EMPTYSTR;
        String commandLink = Keys.COMMAND_SELECTQUEST;
        String questID = "1";

        String editKey = req.getParameter(Keys.COMMAND_EDIT);
        String startKey = req.getParameter(Keys.COMMAND_START);

        if (startKey!=null){
            questID = startKey;
            commandLink = Keys.JSP_VAL_QUEST;
            suffix = "&q=%s".formatted(generalService.getFirstQuestElement(Long.parseLong(questID)).toString());
        }
        if (editKey!=null){
            suffix = "";
            questID = editKey;
            commandLink = Keys.COMMAND_EDIT + "-" + Keys.JSP_VAL_QUEST; //"edit-quest"
        }

//        questID = editKey==null?startKey:questID;
//        questID = startKey==null?editKey:questID;
//        routeLink = editKey!=null?"edit-quest":"quest";
//        String suffix = routeLink==Keys.JSP_VAL_QUEST
//                        ?"&q=%s".formatted(generalService.getFirstQuestElement(Long.parseLong(questID)).toString()):"";

        String selectedPage = "%s?id=%s".formatted(commandLink,questID) + suffix;

        return  selectedPage;
    }


}
