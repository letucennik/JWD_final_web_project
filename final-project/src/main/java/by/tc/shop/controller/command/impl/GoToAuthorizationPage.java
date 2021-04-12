package by.tc.shop.controller.command.impl;

import by.tc.shop.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToAuthorizationPage implements Command {

    public static final String AUTHORIZATION_PAGE = "/WEB-INF/jsp/login.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(AUTHORIZATION_PAGE);
        requestDispatcher.forward(request, response);
    }
}
