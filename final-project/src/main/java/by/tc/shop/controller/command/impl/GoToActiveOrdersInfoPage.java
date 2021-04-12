package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.OrderItem;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.OrderItemService;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToActiveOrdersInfoPage implements Command {
    public static final String ORDER_ID = "orderId";
    public static final String ACTIVE_ORDERS = "activeOrders";
    public static final String GO_TO_ACTIVE_ORDERS_INFO_PAGE = "/WEB-INF/jsp/active_orders_info.jsp";
    private static final Logger logger = Logger.getLogger(GoToActiveOrdersInfoPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        List<OrderItem> activeOrderItems;
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderItemService orderItemService = serviceProvider.getOrderItemService();
        try {
            activeOrderItems = orderItemService.selectOrderItemsByOrder(orderId);
            request.setAttribute(ACTIVE_ORDERS, activeOrderItems);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_ACTIVE_ORDERS_INFO_PAGE);
        requestDispatcher.forward(request, response);
    }
}
