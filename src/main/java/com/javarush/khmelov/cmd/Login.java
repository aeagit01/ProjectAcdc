package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@SuppressWarnings("unused")
public class Login implements Command {

    private final UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String doPost(HttpServletRequest request) {
        String login = request.getParameter(Keys.LOGIN);
        String password = request.getParameter(Keys.PASSWORD);
        Optional<User> user = userService.get(login, password);
        if (user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute(Keys.USER, user.get());
            return Route.PROFILE;
        } else {
            return Route.LOGIN; //todo add error message
        }
    }
}