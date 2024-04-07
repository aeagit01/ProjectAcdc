package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@SuppressWarnings("unused")
public class Login implements Command {

    private final UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse res) {
        String login = req.getParameter(Keys.LOGIN);
        String password = req.getParameter(Keys.PASSWORD);
        Optional<User> user = userService.get(login, password);
        if (user.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute(Keys.USER, user.get());
            return Route.SELECT_QUEST;
        } else {
            return Route.LOGIN; //todo add error message
        }
    }
}