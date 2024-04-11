package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.QuestResponse;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.exeption.QuestException;
import com.javarush.khmelov.repository.QuestResponsesRepository;
import com.javarush.khmelov.tools.Route;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.javarush.khmelov.tools.Keys.*;
@Log4j2
public class QuestResponsesService {
    private final QuestResponsesRepository questResponsesRepository;
    public QuestResponsesService(QuestResponsesRepository questResponsesRepository) {
        this.questResponsesRepository = questResponsesRepository;
        loadSavedResponses();
    }

    public void create(QuestResponse response) {
        questResponsesRepository.create(response);
    }

    public void update(QuestResponse response) {
        questResponsesRepository.update(response);
    }

    public void delete(QuestResponse response) {
        questResponsesRepository.delete(response);
    }

    public Collection<QuestResponse> getAll() {
        return questResponsesRepository.getAll();
    }

    public QuestResponse get(long id) {
        return (QuestResponse) questResponsesRepository.get(id);
    }

    private void loadSavedResponses() {
        Path dataPath = WEB_INF.resolve(Route.DATA_PATH + RESPONSES_FILE);
        try (Stream<String> questData = Files.lines(Paths.get(dataPath.toUri()))) {
            List<String> list = questData.toList();
            for (String questElement : list) {
                String[] dataLines = questElement.split(",");
                questResponsesRepository.create(
                        QuestResponse.builder()
                                .description(dataLines[1])
                                .build());
            };
            log.info("Load responses data");
        } catch (IOException e) {
            log.error("Responses data load error");
            throw new QuestException(e);
        }
    }
}
