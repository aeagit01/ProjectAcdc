package com.javarush.khmelov.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import com.javarush.khmelov.exeption.QuestException;
import com.javarush.khmelov.repository.QuestRepository;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.tools.Route;
import lombok.extern.log4j.Log4j2;

import static com.javarush.khmelov.tools.Keys.QUESTS_FILE;
import static com.javarush.khmelov.tools.Keys.WEB_INF;

@Log4j2
public class QuestService {
    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
        loadSavedData();
    }

    public void create(Quest quest) {
        questRepository.create(quest);
    }

    public void update(Quest quest) {
        questRepository.update(quest);
    }

    public void delete(Quest quest) {
        questRepository.delete(quest);
    }

    public Collection<Quest> getAll() {
        return questRepository.getAll();
    }

    public Quest get(long id) {
        return (Quest) questRepository.get(id);
    }

    private void loadSavedData() {
        Path dataPath = WEB_INF.resolve(Route.DATA_PATH + QUESTS_FILE);
        try (Stream<String> questData = Files.lines(Paths.get(dataPath.toUri()))) {
            List<String> list = questData.toList();
            for (String questElement : list) {
                String[] dataLines = questElement.split(",");
                questRepository.create(
                        Quest.builder()
                                .name(dataLines[0])
                                .description(dataLines[1])
                                .build());
            }
            log.info("Load quests data");
        } catch (IOException e) {
            log.error("Quest data load error");
            throw new QuestException(e);
        }
    }
}
