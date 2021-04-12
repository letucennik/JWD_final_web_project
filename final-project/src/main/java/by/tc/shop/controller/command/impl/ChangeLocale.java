package by.tc.shop.controller.command.impl;

import by.tc.shop.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocale implements Command {
    private static final String LOCALE_PARAMETER = "locale";
    private static final String LOCALE = "locale";
    private static final String URL = "url";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String url = request.getParameter(URL);
        session.setAttribute(LOCALE, request.getParameter(LOCALE_PARAMETER));
        response.sendRedirect(url);
    }
}
