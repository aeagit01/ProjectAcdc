package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.*;
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
import java.util.stream.Stream;

public class SelectNextquestions implements Command {

    QuestionService questionService;
    QuestService questService;
    GeneralService generalService;
    QuestResponsesService questResponsesService;
    FinishMessageService finishMessageService;
    Integer step = 0;
    QuestResponse selectedResponse;

    public SelectNextquestions(QuestionService questionService, QuestService questService, GeneralService generalService,
                               QuestResponsesService questResponsesService, FinishMessageService finishMessageService) {
        this.questionService = questionService;
        this.questService = questService;
        this.generalService = generalService;
        this.questResponsesService = questResponsesService;
        this.finishMessageService = finishMessageService;

    }

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse res) {

        Long questId = Long.parseLong(req.getParameter(Keys.PARAMETR_ID));
        Long responseId = Long.parseLong(req.getParameter(Keys.PARAMETR_RESPONSE));
        Long questionId = Long.parseLong(req.getParameter(Keys.PARAMETR_QUESTION));

        Question selectedQuestion = questionService.get(questionId);
        req.setAttribute(Keys.QUESTION, selectedQuestion);

        Question chkq = getCheckedQuestion(questId, questionId, responseId);
        req.setAttribute("chkq", chkq);
        selectedResponse = questResponsesService.get(responseId);

        req.setAttribute(Keys.RESPONSE, selectedResponse);
        Collection<com.javarush.khmelov.entity.Question> questionlist = questionService.getAll();
        Collection<Question> tmpList = new ArrayList<>(questionlist);
        if (chkq != null) {
            tmpList.remove(chkq);
        }

        req.setAttribute(Keys.JSP_VAL_QUESTION, tmpList.toArray());
        FinishMessage chkfin = getCheckedFinishMessage(questId, questionId, responseId);
        req.setAttribute("chkfin", chkfin);
        Collection<FinishMessage> finmessagelist = finishMessageService.getAll();
        Collection<FinishMessage> tmpFinMessageList = new ArrayList<>(finmessagelist);
        if (chkfin != null) {
            tmpFinMessageList.remove(chkfin);
        }

        req.setAttribute("finmessages", tmpFinMessageList.toArray());

        return getJspPage(); //getPage()+"?q="+questId;//
    }


    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        QuestElement nextQuestElement = null;
        String commandKey = getPage();
        String parametrId = Keys.EMPTYSTR;
        String questId = req.getParameter(Keys.PARAMETR_ID);
        String responseId = req.getParameter(Keys.PARAMETR_RESPONSE);
        String questionId = req.getParameter(Keys.PARAMETR_QUESTION);
        String direction = req.getParameter(Keys.JSP_VAL_DIRECT);

        QuestElement pattern = QuestElement.builder().questID(Long.parseLong(questId)).build();
        List<QuestElement> questElementList = generalService.sortedFind(pattern, Comparator.comparingLong(QuestElement::getResponseID));

        if (direction.equals(Keys.COMMAND_NEXT)) {
            nextQuestElement = generalService.getNextQuestElement(QuestElement.builder()
                    .questID(Long.parseLong(questId))
                    .questionID(Long.parseLong(questionId)).responseID(Long.parseLong(responseId)).build(), questElementList);
        }

        if (direction.equals(Keys.COMMAND_PREV)) {
            nextQuestElement = generalService.getPrevQuestElement(Long.parseLong(questionId), questElementList);
        }

        if (nextQuestElement == null) {
            commandKey = Route.EDIT_QUEST;
            parametrId = Keys.EMPTYSTR;
        } else {
            parametrId = nextQuestElement == null ? parametrId : "&%s=%d&%s=%d".formatted(Keys.PARAMETR_RESPONSE, nextQuestElement.getResponseID(),
                    Keys.PARAMETR_QUESTION, nextQuestElement.getQuestionID());
        }
        updateQuestElenments(req);

        return commandKey + "?%s=%s".formatted(Keys.PARAMETR_ID, questId) + parametrId;
    }

    private void updateQuestElenments(HttpServletRequest req) {

        String nextQuestObject = null;
        Long position = Keys.ELEMENT_ORDINARY;
        String questId = req.getParameter(Keys.PARAMETR_ID);
        String responseId = req.getParameter(Keys.PARAMETR_RESPONSE);
        String questionId = req.getParameter(Keys.PARAMETR_QUESTION);
        String chkedFinMessage = req.getParameter(Keys.JSP_VAL_CHECKEDFINISHMESSAGE);
        String checkedResponses = req.getParameter(Keys.JSP_VAL_CHECKFLAG);

        QuestElement pattern = QuestElement.builder()
                .questionID(Long.parseLong(questionId))
                .responseID(Long.parseLong(responseId))
                .questID(Long.parseLong(questId)).build();

        if (checkedResponses != null) {
            nextQuestObject = checkedResponses;
        }

        if (chkedFinMessage != null) {
            nextQuestObject = chkedFinMessage;
            position = Keys.ELEMENT_LAST;
        }


        if (nextQuestObject != null) {
            updateQuestElement(Long.parseLong(questId),
                    Long.parseLong(questionId),
                    Long.parseLong(responseId),
                    position,
                    Long.parseLong(nextQuestObject));
        }
    }

    private void updateQuestElement(Long questId, Long questionID,
                                    Long responseId, Long position, Long nextQuestion) {
        QuestElement pattern = QuestElement.builder()
                .questID(questId)
                .questionID(questionID)
                .responseID(responseId).build();
        Optional<QuestElement> questElement = generalService.find(pattern).findFirst();
        if (questElement.isPresent()) {
            QuestElement newQuestElement = questElement.get();
            newQuestElement.setPosition(position);
            newQuestElement.setNextQuestionID(nextQuestion);
            generalService.update(newQuestElement);
        } else {
            generalService.create(QuestElement.builder()
                    .questID(questId)
                    .questionID(questionID)
                    .responseID(responseId)
                    .position(position)
                    .nextQuestionID(nextQuestion).build());
        }
    }

    private Question getCheckedQuestion(Long questId, Long questionID, Long responseId) {
        Question question = null;
        QuestElement pattern = QuestElement.builder()
                .questID(questId)
                .questionID(questionID)
                .responseID(responseId).build();

        Optional<QuestElement> questElement = generalService.find(pattern).findFirst();
        if (questElement.isPresent()) {
            question = questionService.get(questElement.get().getNextQuestionID());
        }
        return question;
    }

    private FinishMessage getCheckedFinishMessage(Long questId, Long questionID, Long responseId) {
        FinishMessage finishMessage = null;
        QuestElement pattern = QuestElement.builder()
                .questID(questId)
                .questionID(questionID)
                .responseID(responseId).build();

        Optional<QuestElement> questElement = generalService.find(pattern).findFirst();
        if (questElement.isPresent()) {
            finishMessage = finishMessageService.get(questElement.get().getNextQuestionID());
        }
        return finishMessage;
    }
}
