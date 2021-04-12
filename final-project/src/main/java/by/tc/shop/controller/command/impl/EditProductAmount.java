package by.tc.shop.controller.command.impl;

import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.OrderItemService;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProductAmount implements Command {
    private static String ID = "id";
    private static String AMOUNT = "amount";
    private static Logger logger = Logger.getLogger(EditProductAmount.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(ID);
        String amount = request.getParameter(AMOUNT);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderItemService orderItemService = serviceProvider.getOrderItemService();
        try {
            orderItemService.editNumberOfProducts(Long.parseLong(id), Integer.parseInt(amount));
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
    }
}
