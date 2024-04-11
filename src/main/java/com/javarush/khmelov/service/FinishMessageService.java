package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.FinishMessage;
import com.javarush.khmelov.entity.QuestResponse;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.exeption.QuestException;
import com.javarush.khmelov.repository.QuestResponsesRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import com.javarush.khmelov.repository.FinishMessageRepository;
import com.javarush.khmelov.tools.Route;
import lombok.extern.log4j.Log4j2;

import static com.javarush.khmelov.tools.Keys.*;
@Log4j2
public class FinishMessageService {
    private final FinishMessageRepository finishMessageRepository;

    public FinishMessageService(FinishMessageRepository finishMessageRepository) {
        this.finishMessageRepository = finishMessageRepository;
        loadSavedData();
    }

    public void create(FinishMessage response) {
        finishMessageRepository.create(response);
    }

    public void update(FinishMessage response) {
        finishMessageRepository.update(response);
    }

    public void delete(FinishMessage response) {
        finishMessageRepository.delete(response);
    }

    public Collection<FinishMessage> getAll() {
        return finishMessageRepository.getAll();
    }

    public FinishMessage get(long id) {
        return (FinishMessage) finishMessageRepository.get(id);
    }
    private void loadSavedData(){
        Path dataPath = WEB_INF.resolve(Route.DATA_PATH+FINISH_FILE);
        try (Stream<String> questData = Files.lines(Paths.get(dataPath.toUri()))) {
            List<String> list = questData.toList();
            for (String questElement : list) {
                finishMessageRepository.create(
                        FinishMessage.builder()
                                .description(questElement)
                                .build());
            };

            log.info("Load question data");
        } catch (IOException e){
            log.error("Question data load error");
            throw new QuestException(e);
        }
    }
}
