package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.*;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.service.GeneralService;
import com.javarush.khmelov.service.QuestResponsesService;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.service.QuestionService;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import com.javarush.khmelov.tools.Tools;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;
import java.util.stream.Collectors;

public class SelectQuestions implements Command {
    QuestionService questionService;
    QuestService questService;
    GeneralService generalService;
    Quest selectedQuest;

    public SelectQuestions(QuestionService questionService, QuestService questService, GeneralService generalService) {
        this.questionService = questionService;
        this.questService = questService;
        this.generalService = generalService;
    }

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse res) {
        Long questId = Long.parseLong(req.getParameter(Keys.PARAMETR_ID)); //"id"
        ArrayList<Question> chkquest = new ArrayList<>();
        Question firstchk = null;
        selectedQuest = questService.get(questId);

        List<QuestElement> questElementList = generalService.findQuestQuestions(questId);
        Collection<Question> allQuestions = new ArrayList<>(questionService.getAll());
        for (QuestElement questElement : questElementList) {
            Question foundQuestion = questionService.get(questElement.getQuestionID());
            allQuestions.remove(foundQuestion);
            chkquest.add(foundQuestion);
            if (questElement.getPosition() != null && questElement.getPosition() == Keys.ELEMENT_FIRST) {
                firstchk = foundQuestion;
                chkquest.remove(foundQuestion);
            }
        }
        req.setAttribute(Keys.JSP_VAL_FIRSTELEMENT, firstchk);
        req.setAttribute(Keys.JSP_VAL_CHECKEDQUESTION, chkquest);
        req.setAttribute(Keys.JSP_VAL_QUESTION, allQuestions.toArray());
        req.setAttribute(Keys.JSP_VAL_QUEST, selectedQuest);

        return getJspPage();
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse res) {
        Long questId = Long.parseLong(req.getParameter(Keys.PARAMETR_ID));
        String commandName;

        String cmd = getCommandName(req, Route.SELECT_QUESTIONS);
        if (cmd != Route.SELECT_QUESTIONS) {
            updateQuestElements(req);
            commandName = Route.EDIT_QUEST;
        } else {
            commandName = req.getParameter(Keys.COMMAND_EDIT);
        }
        return commandName + "?%s=%s".formatted(Keys.PARAMETR_ID, questId);
    }

    private void updateQuestElements(HttpServletRequest req) {
        Long questId = Long.parseLong(req.getParameter(Keys.PARAMETR_ID));
        QuestElement pattern = QuestElement.builder().questID(questId).build();
        ArrayList<QuestElement> questElementList = (ArrayList<QuestElement>) generalService.find(pattern).collect(Collectors.toList());
        String[] checkedQuestions = req.getParameterValues(Keys.JSP_VAL_CHECKFLAG);
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
        Optional<String> cmd = Optional.ofNullable(req.getParameter(Keys.JSP_VAL_DIRECT));
        if (cmd.isPresent()) {
            commandName = tools.getCommandKeys(cmd.get());
        }
        return commandName;
    }

    private void updateFirstElement(HttpServletRequest req) {
        String firstElementString = req.getParameter(Keys.JSP_VAL_FIRSTELEMENT);
        String questIdString = req.getParameter(Keys.PARAMETR_ID);

        if (firstElementString != null) {
            Long firstElementLong = Long.parseLong(firstElementString);
            Long questIdLong = Long.parseLong(questIdString);
            QuestElement pattern = QuestElement.builder().questID(questIdLong).position(1L).build();
            ArrayList<QuestElement> firstElementList = (ArrayList<QuestElement>) generalService.find(pattern).collect(Collectors.toList());
            for (QuestElement firstElenemt : firstElementList) {
                Long cheked = firstElenemt.getQuestionID();
                if (firstElementLong != cheked) {
                    firstElenemt.setPosition(Keys.ELEMENT_ORDINARY);
                    generalService.update(firstElenemt);
                }
            }
            pattern = QuestElement.builder().questID(questIdLong).questionID(firstElementLong).build();
            QuestElement newFirstElement = generalService.find(pattern).findFirst().get();
            newFirstElement.setPosition(Keys.ELEMENT_FIRST);
            generalService.update(newFirstElement);
        }
    }
}
