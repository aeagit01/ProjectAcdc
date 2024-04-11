package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.QuestElement;
import com.javarush.khmelov.repository.GeneralRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Comparator;
import java.util.List;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GeneralServiceTest {
    @Mock
    private GeneralRepository mokGeneralRepository;
    @Mock
    private GeneralService mokGeneralService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mokGeneralRepository = Mockito.mock(GeneralRepository.class);
        mokGeneralService = new GeneralService(mokGeneralRepository);
        mokGeneralService.create(QuestElement.builder().position(1L).questID(5L).questionID(10L).responseID(9L).nextQuestionID(9L).build());
        mokGeneralService.create(QuestElement.builder().position(2L).questID(5L).questionID(10L).responseID(8L).nextQuestionID(10L).build());
        mokGeneralService.create(QuestElement.builder().position(2L).questID(5L).questionID(9L).responseID(16L).nextQuestionID(2L).build());
        mokGeneralService.create(QuestElement.builder().position(1L).questID(5L).questionID(9L).responseID(17L).nextQuestionID(2L).build());
    }

    @Test
    void checkReturnNextQuestElementFromGeneralRepository() {
        String currentQuestId = "5";
        String currentQuestionId = "9";
        String currentResponseId = "16";
//        Mockito.doReturn()
        List<QuestElement> mokListQuestElement = mokGeneralRepository.getAll()
                .stream().toList()
                .stream().sorted(Comparator.comparingLong(QuestElement::getResponseID))
                .collect(Collectors.toList());
        QuestElement nextQueslElement = mokGeneralService.getNextQuestElement(Long.parseLong(currentQuestionId), mokListQuestElement);
        QuestElement expecterQuestElement = mokGeneralService.get(1);
        Assertions.assertNotEquals(null, nextQueslElement);
        Assertions.assertEquals(expecterQuestElement, nextQueslElement);
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
        Mockito.verify(mokGeneralRepository, times(5)).create(questElement);
    }
    @Test
    void getAll() {
        mokGeneralService.getAll();
        Mockito.verify(mokGeneralRepository).getAll();
    }
    @Test
    void find() {
        Long position = 1L;
        Long questId = 5L;
        Long questionId = 10L;
        Long responseId = 9L;
        Long nextQuestionId = 9L;
        QuestElement questElement = QuestElement.builder()
                .position(position)
                .questID(questId)
                .questionID(questionId)
                .responseID(responseId)
                .nextQuestionID(nextQuestionId).build();
        mokGeneralService.find(questElement);
        Mockito.verify(mokGeneralRepository).find(questElement);
    }
}