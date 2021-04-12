package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.User;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.UserService;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewAllClients implements Command {
    private static Logger logger = Logger.getLogger(ViewAllClients.class);
    public static final String ALL_CLIENTS = "allClients";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        List<User> allClients;
        try {
            allClients = userService.selectAllClients();
            request.setAttribute(ALL_CLIENTS, allClients);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/clients.jsp");
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
    }
}
