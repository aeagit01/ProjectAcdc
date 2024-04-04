package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.QuestElement;
import com.javarush.khmelov.repository.GeneralRepository;

import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class GeneralService {
    private GeneralRepository generalRepository;

    public GeneralService(GeneralRepository generalRepository) {
        this.generalRepository = generalRepository;
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
    public QuestElement getByQuest(Long questID){
        return (QuestElement) generalRepository.getByQuest(questID);
    }
    public Stream<QuestElement> find(QuestElement pattern){
     return generalRepository.find(pattern);
    }

}
