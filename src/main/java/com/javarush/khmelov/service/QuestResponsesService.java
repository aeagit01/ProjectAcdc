package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.QuestResponse;
import com.javarush.khmelov.repository.QuestResponsesRepository;

import java.util.Collection;

public class QuestResponsesService {
    private final QuestResponsesRepository questResponsesRepository;

    public QuestResponsesService(QuestResponsesRepository questResponsesRepository) {
        this.questResponsesRepository = questResponsesRepository;
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

}
