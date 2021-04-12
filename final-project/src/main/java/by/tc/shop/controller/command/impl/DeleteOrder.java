package by.tc.shop.controller.command.impl;

import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.OrderService;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOrder implements Command {
    private static String ID = "id";
    public static final String URL = "url";
    private static Logger logger = Logger.getLogger(DeleteOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(ID);
        String url = request.getParameter(URL);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();
        try {
            orderService.deleteOrder(Long.parseLong(id));
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        response.sendRedirect(url);
    }
}
