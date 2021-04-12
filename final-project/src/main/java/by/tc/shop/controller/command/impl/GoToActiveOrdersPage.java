package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.Order;
import by.tc.shop.bean.User;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.OrderService;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToActiveOrdersPage implements Command {
    public static final String USER_PARAMETER = "user";
    public static final String USER_ID = "userId";
    public static final String USER_ACTIVE_ORDER_ITEMS = "userOrdersActive";
    public static final String GO_TO_ACTIVE_ORDERS_PAGE = "/WEB-INF/jsp/active_orders.jsp";
    private static final Logger logger = Logger.getLogger(GoToActiveOrdersPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> activeOrdersOfUser;
        long userId;

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(USER_PARAMETER);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();
        try {
            if (user.getRole() == User.ROLE_ADMIN) {
                userId = Long.parseLong(request.getParameter(USER_ID));
            } else {
                userId = user.getId();
            }
            activeOrdersOfUser = orderService.selectActiveOrdersByUser(userId);
            request.setAttribute(USER_ACTIVE_ORDER_ITEMS, activeOrdersOfUser);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_ACTIVE_ORDERS_PAGE);
        requestDispatcher.forward(request, response);
    }
}
