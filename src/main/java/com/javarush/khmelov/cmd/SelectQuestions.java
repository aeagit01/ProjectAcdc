package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.*;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.service.GeneralService;
import com.javarush.khmelov.service.QuestResponsesService;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.service.QuestionService;
import com.javarush.khmelov.tools.Route;
import com.javarush.khmelov.tools.Tools;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelectQuestions implements Command {
    QuestionService questionService;
    QuestService questService;
    GeneralService generalService;
    QuestResponsesService questResponsesService;
    Integer step = 0;
    Quest selectedQuest;

    public SelectQuestions(QuestionService questionService, QuestService questService, GeneralService generalService) {
        this.questionService = questionService;
        this.questService = questService;
        this.generalService = generalService;
    }

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long questId = Long.parseLong(req.getParameter("id"));
        ArrayList<Question> chkquest = new ArrayList<>();
        Question firstchk = null;
        selectedQuest = questService.get(questId);

        QuestElement pattern = QuestElement.builder().questID(questId).build();
        ArrayList<QuestElement> questElementList = (ArrayList<QuestElement>) generalService.find(pattern).collect(Collectors.toList());
        Collection<Question> allQuestions = new ArrayList<>(questionService.getAll());
        for (QuestElement questElement : questElementList) {
            Question foundQuestion = questionService.get(questElement.getQuestionID());
            allQuestions.remove(foundQuestion);
            chkquest.add(foundQuestion);
            if (questElement.getPosition()!=null && questElement.getPosition()==1L){
                firstchk = foundQuestion;
                chkquest.remove(foundQuestion);
            }
        }

        req.setAttribute("firstchk", firstchk);
        req.setAttribute("chkquestions", chkquest);
        req.setAttribute("questions", allQuestions.toArray());
        req.setAttribute("quest", selectedQuest);

        return getJspPage(); //getPage()+"?q="+questId;//
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long questId = Long.parseLong(req.getParameter("id"));
        String commandName;
//        String urlPath = getPage();

        String cmd = getCommandName(req, Route.SELECT_QUESTIONS);
        if (cmd != Route.SELECT_QUESTIONS) {
            updateQuestElements(req);
            commandName = Route.EDIT_QUEST;
        } else {
            commandName = req.getParameter("edit");
        }

        return commandName + "?id=" + questId;
    }

    private void updateQuestElements(HttpServletRequest req) {
        Long questId = Long.parseLong(req.getParameter("id"));
        QuestElement pattern = QuestElement.builder().questID(questId).build();
        ArrayList<QuestElement> questElementList = (ArrayList<QuestElement>) generalService.find(pattern).collect(Collectors.toList());
        String[] checkedQuestions = req.getParameterValues("questionchk");
        if (checkedQuestions != null) {
            List<String> selectedQuestions = Arrays.asList(checkedQuestions);
            List<String> tmpCheck = new ArrayList<String>(selectedQuestions);

            for (QuestElement questElement : questElementList) {
                String checked = questElement.getQuestionID().toString();
                if (selectedQuestions.indexOf(checked) == -1) {
                    generalService.delete(questElement);
                } else {
                    tmpCheck.remove(checked);
                }
            }
            for (String newQuestion : tmpCheck) {
                generalService.create(QuestElement.builder()
                        .questID(questId)
                        .questionID(Long.parseLong(newQuestion)).build());
            }
            updateFirstElement(req);
        }
    }

    private String getCommandName(HttpServletRequest req, String currentCommand) {
        Tools tools = new Tools();
        String commandName = currentCommand;
        Optional<String> cmd = Optional.ofNullable(req.getParameter("direct"));
        if (cmd.isPresent()) {
            commandName = tools.getCommandKeys(cmd.get());
        }
        return commandName;
    }

    private void updateFirstElement(HttpServletRequest req) {
        Long firstElements = Long.parseLong(req.getParameter("firstchk"));
        Long questId = Long.parseLong(req.getParameter("id"));

        if (firstElements != null) {
            QuestElement pattern = QuestElement.builder().questID(questId).position(1L).build();
            ArrayList<QuestElement> firstElementList = (ArrayList<QuestElement>) generalService.find(pattern).collect(Collectors.toList());
            for (QuestElement firstElenemt: firstElementList){
                Long cheked = firstElenemt.getQuestionID();
                if (firstElements != cheked){
                    firstElenemt.setPosition(0L);
                    generalService.update(firstElenemt);
                }
            }
            pattern = QuestElement.builder().questID(questId).questionID(firstElements).build();
            QuestElement newFirstElement = generalService.find(pattern).findFirst().get();
            newFirstElement.setPosition(1L);
            generalService.update(newFirstElement);
        }
    }
}
