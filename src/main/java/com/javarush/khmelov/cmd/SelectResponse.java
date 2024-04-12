
package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.*;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.service.*;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;
import java.util.stream.Collectors;

//todo add selection from list question of quest
public class SelectResponse implements Command {

    QuestionService questionService;
    QuestService questService;
    GeneralService generalService;
    QuestResponsesService questResponsesService;
    FinishMessageService finishMessageService;
    Integer questionId;
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
        Long questId = Long.parseLong(req.getParameter(Keys.PARAMETR_ID));
        Long questionId = Long.parseLong(req.getParameter(Keys.PARAMETR_QUESTION));

        selectedQuestion = questionService.get(questionId);
        req.setAttribute(Keys.QUESTION, selectedQuestion);

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
        req.setAttribute(Keys.JSP_VAL_RESPONSECHK, chkresponses);
        req.setAttribute(Keys.JSP_VAL_RESPONSES, allResponses);

        return getJspPage();
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse res) {
        String suffix = Keys.EMPTYSTR;
        QuestElement nextQuestElement = null;
        String nextPage = getPage();
        String questId = req.getParameter(Keys.PARAMETR_ID);
        String questionId = req.getParameter(Keys.PARAMETR_QUESTION);
        String direction = req.getParameter(Keys.JSP_VAL_DIRECT);

        if (direction.equals(Keys.COMMAND_NEXT)) {
            nextQuestElement = generalService.getNextQuestElement(QuestElement.builder().questionID(Long.parseLong(questionId)).build(),
                    generalService.findQuestQuestions(Long.parseLong(questId)));
        }
        if (direction.equals(Keys.COMMAND_PREV)) {
            nextQuestElement = generalService.getPrevQuestElement(Long.parseLong(questionId),
                    generalService.findQuestQuestions(Long.parseLong(questId)));
        }
        updateResponsesElenments(req);


        if (nextQuestElement == null) {
            nextPage = Route.EDIT_QUEST;
            suffix = "";
        } else {
            questionId = nextQuestElement != null ? nextQuestElement.getQuestionID().toString() : questionId;
            suffix = "&%s=%s".formatted(Keys.PARAMETR_QUESTION, questionId);
        }

        return nextPage + "?%s=%s".formatted(Keys.PARAMETR_ID, questId) + suffix;
    }

    private void updateResponsesElenments(HttpServletRequest req) {

        String questId = req.getParameter(Keys.PARAMETR_ID);
        String questionId = req.getParameter(Keys.PARAMETR_QUESTION);
        QuestElement pattern = QuestElement.builder().questionID(Long.parseLong(questionId)).questID(Long.parseLong(questId)).build();
        ArrayList<QuestElement> questElementList = (ArrayList<QuestElement>) generalService.find(pattern).collect(Collectors.toList());

        String[] checkedResponses = req.getParameterValues(Keys.JSP_VAL_RESPONSECHK);
        if (checkedResponses != null) {
            List<String> selectedResponses = Arrays.asList(checkedResponses);
            List<String> tmpCheck = new ArrayList<String>(selectedResponses);

            for (QuestElement questElement : questElementList) {
                String checked = questElement.getResponseID() != null ? questElement.getResponseID().toString() : Keys.EMPTYSTR;
                if (selectedResponses.indexOf(checked) == Keys.OUT_OF_INDEX) {
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
