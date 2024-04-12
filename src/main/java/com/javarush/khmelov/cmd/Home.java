package com.javarush.khmelov.cmd;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Home implements Command{
    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse res) {
        return getJspPage();
    }
}
