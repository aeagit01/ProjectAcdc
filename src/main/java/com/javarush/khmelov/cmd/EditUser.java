package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.tools.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class EditUser implements Command {

    private final UserService userService;

    public EditUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse res) {
        String stringId = req.getParameter("id");
        if (stringId != null) {
            long id = Long.parseLong(stringId);
            Optional<User> optionalUser = userService.get(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                req.setAttribute(Keys.USER, user);
            }
        }
        return getJspPage();
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse res) {
        User user = User.builder()
                .login(req.getParameter(Keys.LOGIN))
                .password(req.getParameter(Keys.PASSWORD))
                .role(Role.valueOf(req.getParameter(Keys.ROLE)))
                .build();
        if (req.getParameter(Keys.CREATE) != null) {
            userService.create(user);
        } else if (req.getParameter(Keys.UPDATE) != null) {
            user.setId(Long.parseLong(req.getParameter("id")));
            userService.update(user);
        }
        return getPage()+"?id="+user.getId();
    }
}
