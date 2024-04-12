package com.javarush.khmelov.controller;

import com.javarush.khmelov.cmd.Command;
import com.javarush.khmelov.config.HttpResolver;
import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.tools.Keys;
import com.javarush.khmelov.tools.Route;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.javarush.khmelov.config.NanoSpring;
import com.javarush.khmelov.tools.RequestHelper;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet({
        Route.EMPTY_PATH, Route.HOME,
        Route.SIGNUP, Route.LOGIN, Route.LOGOUT,
        Route.LIST_USER, Route.PROFILE, Route.EDIT_USER,
        Route.ADD_QUEST, Route.QUEST, Route.EDIT_QUEST,
        Route.SELECT_QUEST, Route.SELECT_QUESTIONS,
        Route.STATISTICS, Route.HELP,Route.SELECT_RESPONSE, Route.SELECT_NEXT_QUESTIONS
})
public class FrontController extends HttpServlet {

    private HttpResolver httpResolver;

    @Override
    public void init(ServletConfig config) {
        httpResolver = NanoSpring.find(HttpResolver.class);
//        NanoSpring.find(Config.class).fillStartData();
//        config.getServletContext().setAttribute("roles", Role.values());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uriCommand = RequestHelper.getCommand(req, resp);
        String cmdName = fixRootCase(uriCommand);
        Command command = httpResolver.resolve(cmdName);
        if (req.getMethod().equalsIgnoreCase("get")) {
            String view = command.doGet(req, resp);
            req.getRequestDispatcher(view).forward(req, resp);
        } else if (req.getMethod().equalsIgnoreCase("post")) {
            String redirect = command.doPost(req, resp);
            redirect = fixAbsoluteAddressing(redirect);
            resp.sendRedirect(redirect);
        } else {
            throw new UnsupportedOperationException(req.getMethod());
        }
    }

    private static String fixRootCase(String uriCommand) {
        return uriCommand.equals("/")
                ? "home"
                : uriCommand.substring(1);
    }

    private static String fixAbsoluteAddressing(String redirect) {
        return redirect.startsWith("/")
                ? redirect.substring(1)
                : redirect;
    }
}

