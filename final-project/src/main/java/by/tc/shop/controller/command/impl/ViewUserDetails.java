package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.UserDetails;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.UserDetailsService;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewUserDetails implements Command {
    public static final String USER_ID = "userId";
    public static final String USER_DETAILS = "userDetails";
    public static final String GO_TO_USER_DETAILS_PAGE = "/WEB-INF/jsp/view_user_details.jsp";
    private static final Logger logger = Logger.getLogger(ViewUserDetails.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDetails userDetails;
        long userId = Long.parseLong(request.getParameter(USER_ID));

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserDetailsService userDetailsService = serviceProvider.getUserDetailsService();
        try {
            userDetails = userDetailsService.selectByUserId(userId);
            request.setAttribute(USER_DETAILS, userDetails);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_USER_DETAILS_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }

    }
}
