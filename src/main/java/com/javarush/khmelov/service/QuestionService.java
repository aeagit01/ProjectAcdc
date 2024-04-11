package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.exeption.QuestException;
import com.javarush.khmelov.repository.QuestionRepository;
import com.javarush.khmelov.tools.Route;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static com.javarush.khmelov.tools.Keys.*;

@Log4j2
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        loadSavedQuestion();
    }
    public void create(Question question) {
        questionRepository.create(question);
    }
    public void update(Question question) {
        questionRepository.update(question);
    }
    public void delete(Question question) {
        questionRepository.delete(question);
    }
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }
    public Question get(long id) {
        return (Question) questionRepository.get(id);
    }

    private void loadSavedQuestion(){
        Path dataPath = WEB_INF.resolve(Route.DATA_PATH+QUESTIONS_FILE);
        try (Stream<String> questData = Files.lines(Paths.get(dataPath.toUri()))) {
            List<String> list = questData.toList();
            for (String questElement : list) {
                String[] dataLines = questElement.split(",");
                questionRepository.create(
                        Question.builder()
                                .description(dataLines[1])
                                .build());
            }
            log.info("Load question data");
        } catch (IOException e){
            log.error("Question data load error");
            throw new QuestException(e);
        }
    }
}
