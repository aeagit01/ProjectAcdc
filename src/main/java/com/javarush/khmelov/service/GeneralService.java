package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.QuestElement;
import com.javarush.khmelov.repository.GeneralRepository;

import java.util.Collection;

public class GeneralService {
    private GeneralRepository generalRepository;


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

}
