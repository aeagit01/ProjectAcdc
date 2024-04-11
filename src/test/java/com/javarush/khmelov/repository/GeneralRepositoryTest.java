package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.QuestElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneralRepositoryTest {
    GeneralRepository generalRepository;
    @BeforeEach
    void setUp() {
        GeneralRepository generalRepository = Mockito.mock(GeneralRepository.class);
    }

    @Test
    void getByQuest() {
        List<QuestElement> questElementList =
        Mockito.verify(generalRepository).getByQuest(2L);
    }
}