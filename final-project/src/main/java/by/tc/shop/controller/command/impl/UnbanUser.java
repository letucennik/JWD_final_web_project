package by.tc.shop.controller.command.impl;

import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.UserService;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnbanUser implements Command {
    public static final String USER_ID = "userId";
    public static final String URL = "url";
    public static final String UNBANNED = "unbanned";
    private static final Logger logger = Logger.getLogger(UnbanUser.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long userId = Long.parseLong(request.getParameter(USER_ID));
        String url = request.getParameter(URL);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        try {
            userService.unbanUser(userId);
            request.setAttribute(UNBANNED, userId);
            response.sendRedirect(url);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }

    }
}
