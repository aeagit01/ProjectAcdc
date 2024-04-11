package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.QuestElement;
import com.javarush.khmelov.repository.GeneralRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

class GeneralServiceTest {
    @Mock
    private GeneralRepository mokGeneralRepository;
    @Mock
    private GeneralService mokGeneralService;
    @Mock
    private List<QuestElement> sortedList;
    @Mock
    private List<QuestElement> unsortedList;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mokGeneralRepository = Mockito.mock(GeneralRepository.class);
        mokGeneralService = new GeneralService(mokGeneralRepository);
        sortedList = new ArrayList<>();
        sortedList.add(QuestElement.builder().position(1L).questID(5L).questionID(9L).responseID(17L).nextQuestionID(2L).build());
        sortedList.add(QuestElement.builder().position(2L).questID(5L).questionID(10L).responseID(8L).nextQuestionID(10L).build());
        sortedList.add(QuestElement.builder().position(2L).questID(5L).questionID(11L).responseID(16L).nextQuestionID(2L).build());

        unsortedList = new ArrayList<>();
        unsortedList.add(QuestElement.builder().position(1L).questID(5L).questionID(9L).responseID(17L).nextQuestionID(2L).build());
        unsortedList.add(QuestElement.builder().position(2L).questID(5L).questionID(10L).responseID(8L).nextQuestionID(10L).build());
        unsortedList.add(QuestElement.builder().position(0L).questID(5L).questionID(10L).responseID(8L).nextQuestionID(10L).build());
        unsortedList.add(QuestElement.builder().position(2L).questID(5L).questionID(11L).responseID(16L).nextQuestionID(2L).build());
        unsortedList.add(QuestElement.builder().position(0L).questID(4L).questionID(9L).responseID(17L).nextQuestionID(2L).build());
        unsortedList.add(QuestElement.builder().position(0L).questID(4L).questionID(10L).responseID(17L).nextQuestionID(2L).build());
        unsortedList.add(QuestElement.builder().position(1L).questID(5L).questionID(8L).responseID(8L).nextQuestionID(10L).build());
        unsortedList.add(QuestElement.builder().position(0L).questID(4L).questionID(11L).responseID(16L).nextQuestionID(2L).build());
    }

    @Test
    void checkReturnNextQuestElementFromGeneralRepository() {
        Long currentQuestionId = 9L;
        QuestElement nextQueslElement = mokGeneralService.getNextQuestElement(currentQuestionId, sortedList);

        Integer currentIndex = sortedList.indexOf(sortedList.stream()
                .filter(qe->qe.getQuestionID().equals(currentQuestionId))
                .findFirst().get());
        Assertions.assertNotEquals(sortedList.get(currentIndex), nextQueslElement);

    }
    @Test
    void checkReturnPrevQuestElementFromGeneralRepository() {
        Long currentQuestionId = 10L;
        GeneralService generalService = new GeneralService(mokGeneralRepository);

        QuestElement nextQueslElement = mokGeneralService.getPrevQuestElement(currentQuestionId, sortedList);

        Integer currentIndex = sortedList.indexOf(sortedList.stream()
                .filter(qe->qe.getQuestionID().equals(currentQuestionId))
                .findFirst().get());
        Assertions.assertNotEquals(sortedList.get(currentIndex), nextQueslElement);
    }
    @Test
    void create() {
        Long ID = 100L;
        Long position = 1L;
        Long questId = 5L;
        Long questionId = 10L;
        Long responseId = 9L;
        Long nextQuestionId = 9L;
        QuestElement questElement = QuestElement.builder()
                .id(ID)
                .position(position)
                .questID(questId)
                .questionID(questionId)
                .responseID(responseId)
                .nextQuestionID(nextQuestionId).build();
        mokGeneralService.create(questElement);
        Mockito.verify(mokGeneralRepository, times(3)).create(questElement);
    }
    @Test
    void getAll() {
        mokGeneralService.getAll();
        Mockito.verify(mokGeneralRepository).getAll();
    }
}