package by.tc.shop.controller.command.impl;

import by.tc.shop.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToUserDetailsPage implements Command {
    private static final String GO_TO_USER_DETAILS_PAGE = "/WEB-INF/jsp/user_details.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_USER_DETAILS_PAGE);
        requestDispatcher.forward(request, response);
    }
}
