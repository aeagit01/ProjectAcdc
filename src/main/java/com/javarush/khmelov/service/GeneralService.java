package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.QuestElement;

import com.javarush.khmelov.exeption.QuestException;
import com.javarush.khmelov.repository.GeneralRepository;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import lombok.extern.flogger.Flogger;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static com.javarush.khmelov.tools.Keys.*;


public class GeneralService {
    private GeneralRepository generalRepository;
//    public static final Logger logger = LogManager.getLogger(GeneralService.class);
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

    private void loadSavedQuestData() {
        Path dataPath = WEB_INF.resolve(Route.DATA_PATH + QUESTS_DATA_FILE);
        try (Stream<String> questData = Files.lines(Paths.get(dataPath.toUri()))) {
            List<String> list = questData.toList();
            for(String questElement: list){
                String[] dataLines = questElement.split(",");
                generalRepository.create(
                        QuestElement.builder()
                                .position(Long.parseLong(dataLines[0])) //dataLines[0]
                                .questID(Long.parseLong(dataLines[1]))
                                .questionID(Long.parseLong(dataLines[2]))
                                .responseID(Long.parseLong(dataLines[3]))
                                .nextQuestionID(Long.parseLong(dataLines[4]))
                                .build());
            };
//            logger.info("Load quests data");
        } catch (IOException e) {
//            logger.error("Quest data load error");
            throw new QuestException(e);
        }
    }
}
