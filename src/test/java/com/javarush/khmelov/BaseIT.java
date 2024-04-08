package com.javarush.khmelov;

import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.entity.QuestResponse;
import com.javarush.khmelov.repository.FinishMessageRepository;
import com.javarush.khmelov.repository.QuestRepository;
import com.javarush.khmelov.repository.QuestResponsesRepository;
import com.javarush.khmelov.repository.QuestionRepository;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseIT {
    protected final HttpServletRequest request;
    protected final HttpServletResponse response;
    protected final HttpSession session;
    protected final ServletConfig servletConfig;
    protected final ServletContext servletContext;
    protected final QuestionRepository questionRepository;
    protected final QuestRepository questRepository;
    protected final QuestResponsesRepository questResponsesRepository;
    protected final FinishMessageRepository finishMessageRepository;

    //        protected final Config config;
//        protected final User testAdmin;
//        protected final User testUser;
//        protected final User testGuest;

    protected BaseIT() {
        questionRepository = Winter.find(QuestionRepository.class);
        questRepository = Winter.find(QuestRepository.class);
        questResponsesRepository = Winter.find(QuestResponsesRepository.class);
        finishMessageRepository = Winter.find(FinishMessageRepository.class);
        servletConfig = mock(ServletConfig.class);
        servletContext = mock(ServletContext.class);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        //current op
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        //test data
    }
}