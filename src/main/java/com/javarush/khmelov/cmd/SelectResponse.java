
package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.*;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.service.*;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

//todo add selection from list question of quest
public class SelectResponse implements Command {

    QuestionService questionService;
    QuestService questService;
    GeneralService generalService;
    QuestResponsesService questResponsesService;
    FinishMessageService finishMessageService;
    Integer questionId = 1;
    Question selectedQuestion;

    public SelectResponse(QuestionService questionService, QuestService questService, GeneralService generalService,
                          QuestResponsesService questResponsesService, FinishMessageService finishMessageService) {
        this.questionService = questionService;
        this.questService = questService;
        this.generalService = generalService;
        this.questResponsesService = questResponsesService;
        this.finishMessageService = finishMessageService;

    }

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse res) {
        ArrayList<QuestResponse> chkresponses = new ArrayList<>();
        Long questId = Long.parseLong(req.getParameter("id"));
        Long questionId = Long.parseLong(req.getParameter("q"));

        selectedQuestion = questionService.get(questionId);
        req.setAttribute("question", selectedQuestion);

        QuestElement pattern = QuestElement.builder().questionID(questionId).questID(questId).build();
        ArrayList<QuestElement> questElementList = (ArrayList<QuestElement>) generalService.find(pattern).collect(Collectors.toList());
        Collection<QuestResponse> allResponses = new ArrayList<>(questResponsesService.getAll());
        for (QuestElement questElement : questElementList) {
            if (questElement.getResponseID() != null) {
                QuestResponse foundResponse = questResponsesService.get(questElement.getResponseID());
                allResponses.remove(foundResponse);
                chkresponses.add(foundResponse);
            }
        }
        req.setAttribute("responsechk", chkresponses);
        req.setAttribute("responses", allResponses);

        return getJspPage(); //getPage()+"?q="+questId;//
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse res) {
        QuestElement nextQuestElement = null;
        String nextPage = getPage();
        String questId = req.getParameter("id");
        String questionId = req.getParameter("q");
        String direction = req.getParameter("direct");

        if (direction.equals("next")) {
            nextQuestElement = generalService.getNextQuestElement(Long.parseLong(questionId), Long.parseLong(questId));
            questionId = nextQuestElement != null ? nextQuestElement.getQuestionID().toString() : questionId;
            nextPage = nextQuestElement == null ? Route.EDIT_QUEST : nextPage;
        }
        if (direction.equals("prev")) {
            nextQuestElement = generalService.getPrevQuestElement(Long.parseLong(questionId), Long.parseLong(questId));
            questionId = nextQuestElement != null ? nextQuestElement.getQuestionID().toString() : questionId;
            nextPage = nextQuestElement == null ? Route.EDIT_QUEST : nextPage;
        }
        updateResponsesElenments(req);
        return nextPage + "?id=" + questId + "&q=" + questionId;
    }

    private void updateResponsesElenments(HttpServletRequest req) {
        String questId = req.getParameter("id");
        String questionId = req.getParameter("q");
        QuestElement pattern = QuestElement.builder().questionID(Long.parseLong(questionId)).questID(Long.parseLong(questId)).build();
        ArrayList<QuestElement> questElementList = (ArrayList<QuestElement>) generalService.find(pattern).collect(Collectors.toList());

        String[] checkedResponses = req.getParameterValues("responsechk");
        if (checkedResponses != null) {
            List<String> selectedResponses = Arrays.asList(checkedResponses);
            List<String> tmpCheck = new ArrayList<String>(selectedResponses);

            for (QuestElement questElement : questElementList) {
                String checked = questElement.getResponseID() != null ? questElement.getResponseID().toString() : Keys.EMPTYSTR;
                if (selectedResponses.indexOf(checked) == -1) {
                    generalService.delete(questElement);
                } else {
                    tmpCheck.remove(checked);
                }
            }
            for (String newResponse : tmpCheck) {
                generalService.create(QuestElement.builder()
                        .questID(Long.parseLong(questId))
                        .questionID(Long.parseLong(questionId))
                        .responseID(Long.parseLong(newResponse)).build());
            }
        }
    }

}
