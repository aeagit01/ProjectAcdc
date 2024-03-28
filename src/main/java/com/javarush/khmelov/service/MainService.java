package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.repository.ImageRepository;
import com.javarush.khmelov.repository.QuestRepository;
import com.javarush.khmelov.repository.QuestionsRepository;
import com.javarush.khmelov.repository.ResponsesRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class MainService {

    private QuestRepository questRepository;
    private ImageRepository imageRepository;
    private QuestionsRepository questionsRepository;
    private ResponsesRepository responsesRepository;

    public Optional<Quest> startQuest(int questId){
        return questRepository.get(questId);
    }



}
