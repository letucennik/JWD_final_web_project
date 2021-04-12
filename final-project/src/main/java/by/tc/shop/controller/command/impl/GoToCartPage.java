package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.Order;
import by.tc.shop.bean.OrderItem;
import by.tc.shop.bean.User;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.OrderItemService;
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
import java.util.ArrayList;
import java.util.List;

public class GoToCartPage implements Command {
    public static final String USER_PARAMETER = "user";
    public static final String USER_CART_ORDER_ITEMS = "userOrdersInCart";
    private static final Logger logger = Logger.getLogger(GoToCartPage.class);
    public static final String CART_PAGE = "/WEB-INF/jsp/cart.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order;
        List<OrderItem> cartOrderItems;
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(USER_PARAMETER);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();
        OrderItemService orderItemService = serviceProvider.getOrderItemService();
        try {
            order = orderService.selectUserCartOrder(user.getId());
            if (order == null) {
                cartOrderItems = new ArrayList<>();
            } else {
                cartOrderItems = orderItemService.selectOrderItemsByOrder(order.getId());
            }
            request.setAttribute(USER_CART_ORDER_ITEMS, cartOrderItems);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CART_PAGE);
        requestDispatcher.forward(request, response);
    }
}
