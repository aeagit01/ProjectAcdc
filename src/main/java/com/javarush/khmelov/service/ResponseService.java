package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.QuestObject;
import com.javarush.khmelov.entity.QuestResponse;
import com.javarush.khmelov.repository.ResponsesRepository;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.entity.Quest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ResponseService {
    private final ResponsesRepository responsesRepository;

    public ResponseService(ResponsesRepository responsesRepository) {
        this.responsesRepository = responsesRepository;
    }

    public void create(QuestResponse response) {
        responsesRepository.create(response);
    }

    public void update(QuestResponse questResponse) {
        responsesRepository.update(questResponse);
    }

    public void delete(QuestResponse questResponse) {
        responsesRepository.delete(questResponse);
    }

    public Collection<QuestResponse> getAll() {
        return responsesRepository.getAll();
    }

    public Optional<QuestResponse> get(int id) {
        return responsesRepository.get(id);
    }
    public ArrayList<QuestResponse> getObjectResponses (QuestObject questObject){
        ArrayList<QuestResponse> responseList = new ArrayList<>();
        return responseList;
    }

}
