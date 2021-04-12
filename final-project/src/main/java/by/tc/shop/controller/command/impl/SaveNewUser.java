package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.User;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.UserService;
import by.tc.shop.service.exception.impl.EmailExistsException;
import by.tc.shop.service.exception.impl.UserServiceException;
import by.tc.shop.service.exception.impl.UsernameExistsException;
import by.tc.shop.service.validation.exception.InvalidEmailException;
import by.tc.shop.service.validation.exception.InvalidLoginException;
import by.tc.shop.service.validation.exception.InvalidPasswordException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveNewUser implements Command {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";

    private static final String GO_TO_MAIN_PAGE = "Controller?command=gotomainpage";
    private static final String GO_TO_REGISTRATION_PAGE_EMAIL_EXISTS = "Controller?command=gotoregistrationpage&message=Email exists";
    private static final String GO_TO_REGISTRATION_PAGE_USERNAME_EXISTS = "Controller?command=gotoregistrationpage&message=Username exists";
    private static final String GO_TO_REGISTRATION_PAGE_INVALID_LOGIN = "Controller?command=gotoregistrationpage&message=Invalid login";
    private static final String GO_TO_REGISTRATION_PAGE_INVALID_PASSWORD = "Controller?command=gotoregistrationpage&message=Invalid password";
    private static final String GO_TO_REGISTRATION_PAGE_INVALID_EMAIL = "Controller?command=gotoregistrationpage&message=Invalid email";
    private static final String USER_PARAMETER = "user";
    private static final Logger logger = Logger.getLogger(SaveNewUser.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        user = User.getBuilderInstance().setUsername(request.getParameter(USERNAME)).setEmail(request.getParameter(EMAIL)).
                setPassword(request.getParameter(PASSWORD)).setRole(User.ROLE_CLIENT).setStatus(User.STATUS_ACTIVE).build();

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        Long registered;
        try {
            registered = userService.registration(user);
            if (registered == null) {
                throw new CommandException();
            }
            user.setId(registered);
            HttpSession session = request.getSession(true);
            session.setAttribute(USER_PARAMETER, user);
            response.sendRedirect(GO_TO_MAIN_PAGE);
        } catch (UsernameExistsException e) {
            response.sendRedirect(GO_TO_REGISTRATION_PAGE_USERNAME_EXISTS);
        } catch (EmailExistsException e) {
            response.sendRedirect(GO_TO_REGISTRATION_PAGE_EMAIL_EXISTS);
        } catch (InvalidLoginException e) {
            response.sendRedirect(GO_TO_REGISTRATION_PAGE_INVALID_LOGIN);
        } catch (InvalidPasswordException e) {
            response.sendRedirect(GO_TO_REGISTRATION_PAGE_INVALID_PASSWORD);
        } catch (InvalidEmailException e) {
            response.sendRedirect(GO_TO_REGISTRATION_PAGE_INVALID_EMAIL);
        } catch (UserServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }

    }
}
