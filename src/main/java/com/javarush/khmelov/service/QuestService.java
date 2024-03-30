package com.javarush.khmelov.service;

import java.util.Collection;
import java.util.Optional;
import com.javarush.khmelov.repository.QuestRepository;
import com.javarush.khmelov.entity.Quest;

public class QuestService {
    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
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
}
