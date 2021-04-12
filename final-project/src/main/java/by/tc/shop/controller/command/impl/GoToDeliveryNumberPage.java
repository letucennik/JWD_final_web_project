package by.tc.shop.controller.command.impl;

import by.tc.shop.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToDeliveryNumberPage implements Command {

    public static final String DELIVERY_NUMBER_PAGE = "/WEB-INF/jsp/delivery_number.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(DELIVERY_NUMBER_PAGE);
        requestDispatcher.forward(request, response);
    }
}
