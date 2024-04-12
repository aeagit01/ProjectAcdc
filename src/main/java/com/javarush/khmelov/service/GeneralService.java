package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.QuestElement;

import com.javarush.khmelov.entity.QuestObject;
import com.javarush.khmelov.exeption.QuestException;
import com.javarush.khmelov.repository.GeneralRepository;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import static com.javarush.khmelov.tools.Keys.*;

@Log4j2
public class GeneralService {
    private GeneralRepository generalRepository;

    //public static final Logger logger = (Logger) LogManager.getLogger(GeneralService.class);
    public GeneralService(GeneralRepository generalRepository) {
        this.generalRepository = generalRepository;
        loadSavedQuestData();
    }

    public void create(QuestElement questElement) {
        generalRepository.create(questElement);
    }

    public void update(QuestElement questElement) {
        generalRepository.update(questElement);
    }

    public void delete(QuestElement questElement) {
        generalRepository.delete(questElement);
    }

    public Collection<QuestElement> getAll() {
        return generalRepository.getAll();
    }

    public QuestElement get(long id) {
        return (QuestElement) generalRepository.get(id);
    }

    public QuestElement getByQuest(Long questID) {
        return (QuestElement) generalRepository.getByQuest(questID);
    }

    public Stream<QuestElement> find(QuestElement pattern) {
        return generalRepository.find(pattern);
    }

    public List<QuestElement> sortedFind(QuestElement pattern, Comparator comparator) {
        Stream<QuestElement> questElementStream = find(pattern).sorted(comparator);
        return questElementStream.collect(Collectors.toList());
    }

    public List<QuestElement> findQuestQuestions(Long questId) {
        QuestElement pattern = QuestElement.builder().questID(questId).build();
        return find(pattern).distinct()
                .sorted(Comparator.comparingLong(QuestElement::getQuestionID))
                .collect(Collectors.toList());
    }

    public QuestElement getPrevQuestElement(Long currentQuestion, List<QuestElement> questElementList) {
        Integer nextIndex;
        QuestElement nextElement = null;
        Integer currentIndex = questElementList.size();

        Optional<QuestElement> currentElement = questElementList.stream()
                .filter(questElement -> questElement.getQuestionID() == currentQuestion)
                .findFirst();
        if (currentElement.isPresent()) {
            currentIndex = getArrayIndex(currentElement.get(), questElementList);
        }
        if (currentIndex > 0) {
            nextIndex = currentIndex - 1;
            nextElement = questElementList.get(nextIndex);
        }
        return nextElement;
    }

    public QuestElement getNextQuestElement(QuestElement pattern, List<QuestElement> questElementList) {
        Integer nextIndex;
        QuestElement nextElement = null;
        Integer currentIndex = questElementList.size();
        Optional<QuestElement> currentElement = find(pattern).findFirst();

        if (currentElement.isPresent()) {
            currentIndex = getArrayIndex(currentElement.get(), questElementList);
        }
        if (currentIndex < questElementList.size() - 1) {
            nextIndex = currentIndex + 1;
            nextElement = questElementList.get(nextIndex);
        }
        return nextElement;
    }

    private void loadSavedQuestData() {
        Path dataPath = WEB_INF.resolve(Route.DATA_PATH + QUESTS_DATA_FILE);
        try (Stream<String> questData = Files.lines(Paths.get(dataPath.toUri()))) {
            List<String> list = questData.toList();
            for (String questElement : list) {
                String[] dataLines = questElement.split(",");
                generalRepository.create(
                        QuestElement.builder()
                                .position(Long.parseLong(dataLines[0])) //dataLines[0]
                                .questID(Long.parseLong(dataLines[1]))
                                .questionID(Long.parseLong(dataLines[2]))
                                .responseID(Long.parseLong(dataLines[3]))
                                .nextQuestionID(Long.parseLong(dataLines[4]))
                                .build());
            }
            ;
            log.info("Load quests data");
        } catch (IOException e) {
            log.error("Quest data load error");
            throw new QuestException(e);
        }
    }

    public Long getFirstQuestElement(Long questID) {
        Long firstQuestionId = 0L;
        QuestElement pattern = QuestElement.builder()
                .questID(questID)
                .position(ELEMENT_FIRST).build();
        Optional<QuestElement> questElement = find(pattern).findFirst();
        if (questElement.isPresent()) {
            firstQuestionId = questElement.get().getQuestionID();
        }
        return firstQuestionId;
    }

    private int getArrayIndex(QuestElement questObject, List<QuestElement> list) {
        int retindex = -1;
        for (int i = 0; i < list.size(); i++) {
            if (questObject.getId() == list.get(i).getId()) {
                retindex = i;
                break;
            }
        }
        return retindex;
    }
}

