package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.Order;
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
import java.io.IOException;
import java.util.List;

public class ViewOrdersByStatus implements Command {
    private static final String STATUS = "status";
    private static final String ORDERS_BY_STATUS = "ordersByStatus";
    private static final String GO_TO_ORDERS_BY_STATUS_PAGE = "/WEB-INF/jsp/orders_by_status.jsp";
    private static final Logger logger = Logger.getLogger(ViewOrdersByStatus.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> ordersByStatus;
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();
        try {
            ordersByStatus = orderService.selectOrdersByStatus(Integer.parseInt(request.getParameter(STATUS)));
            request.setAttribute(ORDERS_BY_STATUS, ordersByStatus);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_ORDERS_BY_STATUS_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
    }
}
