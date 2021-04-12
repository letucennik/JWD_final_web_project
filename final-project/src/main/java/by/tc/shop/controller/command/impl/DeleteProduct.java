package by.tc.shop.controller.command.impl;

import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.ProductService;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProduct implements Command {
    private static final String ID = "id";
    private static final String URL = "url";
    private static final Logger logger = Logger.getLogger(DeleteProduct.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ProductService productService = serviceProvider.getProductService();
        String id = request.getParameter(ID);
        String url = request.getParameter(URL);
        try {
            productService.deleteProduct(Long.parseLong(id));
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        response.sendRedirect(url);
    }
}
