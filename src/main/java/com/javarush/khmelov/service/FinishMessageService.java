package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.FinishMessage;
import com.javarush.khmelov.entity.QuestResponse;
import com.javarush.khmelov.repository.QuestResponsesRepository;

import java.util.Collection;
import com.javarush.khmelov.repository.FinishMessageRepository;

public class FinishMessageService {
    private final FinishMessageRepository finishMessageRepository;

    public FinishMessageService(FinishMessageRepository finishMessageRepository) {
        this.finishMessageRepository = finishMessageRepository;
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
}
