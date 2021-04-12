package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Authorization implements Command {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String GO_TO_AUTHORIZATION_PAGE_INVALID_LOGIN_OR_PASSWORD = "Controller?command=gotoauthorizationpage&message=Invalid login or password";
    public static final String GO_TO_AUTHORIZATION_PAGE_BANNED = "Controller?command=gotoauthorizationpage&banned=User was banned";
    public static final String GO_TO_MAIN_PAGE = "Controller?command=gotomainpage";
    public static final String USER_PARAMETER = "user";
    private static final Logger logger = Logger.getLogger(Authorization.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login;
        String password;
        login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        User user = null;
        try {
            user = userService.authorization(login, password);
            HttpSession session = request.getSession(true);
            if (user == null) {
                session.setAttribute(USER_PARAMETER, null);
                response.sendRedirect(GO_TO_AUTHORIZATION_PAGE_INVALID_LOGIN_OR_PASSWORD);
                return;
            }
            if (user.getStatus() == User.STATUS_BANNED) {
                response.sendRedirect(GO_TO_AUTHORIZATION_PAGE_BANNED);
                return;
            }
            session.setAttribute(USER_PARAMETER, user);
            response.sendRedirect(GO_TO_MAIN_PAGE);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }

    }
}
