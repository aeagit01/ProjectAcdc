package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.FinishMessage;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.repository.FinishMessageReporitory;
import com.javarush.khmelov.repository.QuestRepository;

import java.util.Collection;
import java.util.Optional;

public class FinishMessageService {
    private final FinishMessageReporitory finishMessageReporitory;

    public FinishMessageService(FinishMessageReporitory finishMessageReporitory) {
        this.finishMessageReporitory = finishMessageReporitory;
    }
    public void create(FinishMessage finishMessage) {
        finishMessageReporitory.create(finishMessage);
    }
    public void update(FinishMessage finishMessage) {
        finishMessageReporitory.update(finishMessage);
    }
    public void delete(FinishMessage finishMessage) {
        finishMessageReporitory.delete(finishMessage);
    }
    public Collection<FinishMessage> getAll() {
        return finishMessageReporitory.getAll();
    }
    public Optional<FinishMessage> get(long id) {
        return finishMessageReporitory.get(id);
    }

}
