package by.tc.shop.controller.command.impl;

import by.tc.shop.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Logout implements Command {
    public static final String USER_PARAMETER = "user";
    public static final String GO_TO_AUTHORIZATION_PAGE = "Controller?command=gotoauthorizationpage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(USER_PARAMETER);
        response.sendRedirect(GO_TO_AUTHORIZATION_PAGE);
    }
}
