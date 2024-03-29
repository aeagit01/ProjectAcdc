package com.javarush.khmelov.service;

import java.util.Collection;
import java.util.Optional;
import com.javarush.khmelov.repository.QuestObjectRepository;
import com.javarush.khmelov.entity.QuestObject;

public class QuestService {
    private final QuestObjectRepository questRepository;

    public QuestService(QuestObjectRepository questRepository) {
        this.questRepository = questRepository;
    }

    public void create(QuestObject quest) {
        questRepository.create(quest);
    }

    public void update(QuestObject quest) {
        questRepository.update(quest);
    }

    public void delete(QuestObject quest) {
        questRepository.delete(quest);
    }

    public Collection<QuestObject> getAll() {
        return questRepository.getAll();
    }

    public QuestObject get(long id) {
        return questRepository.get(id);
    }
}
