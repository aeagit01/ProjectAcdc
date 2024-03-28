package com.javarush.khmelov;

import com.javarush.khmelov.repository.ImageRepository;
import com.javarush.khmelov.repository.QuestionsRepository;
import com.javarush.khmelov.repository.ResponsesRepository;

public class QuestMain {
    private ImageRepository imageRepository;
    private QuestionsRepository questionsRepository;
    private ResponsesRepository responsesRepository;


    //выводим страницу приветствия
    //регистрируем пользователя
    //инициализируем репозитории

    public QuestMain(ImageRepository imageRepository, QuestionsRepository questionsRepository, ResponsesRepository responsesRepository) {
        this.imageRepository = imageRepository;
        this.questionsRepository = questionsRepository;
        this.responsesRepository = responsesRepository;
    }

    //Цикл
        //получем первый вопрос
        //получаем ответ
        //выбираем следующий вопрос
            //Есть следующий?
                //нет - завершаем работу
                //да - выводим его
    //КонецЦикла





}
