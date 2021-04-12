package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.Delivery;
import by.tc.shop.bean.Product;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.DeliveryService;
import by.tc.shop.service.ProductService;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class GoToDeliveryPage implements Command {
    private static final String GO_TO_DELIVERY_PAGE = "/WEB-INF/jsp/delivery.jsp";
    private static final String ALL_PRODUCTS = "allProducts";
    private static final String NUMBER_OF_PRODUCT_TYPES = "numberOfProductTypes";
    private static final String DELIVERY = "delivery";
    private static final Logger logger = Logger.getLogger(GoToDeliveryPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ProductService productService = serviceProvider.getProductService();
        List<Product> allProducts;
        DeliveryService deliveryService = serviceProvider.getDeliveryService();
        Delivery delivery = new Delivery();
        delivery.setDate(Timestamp.from(Instant.now()));
        long deliveryId;
        try {
            deliveryId = deliveryService.insertNewDelivery(delivery);
            delivery.setId(deliveryId);
            request.setAttribute(DELIVERY, delivery);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        try {
            allProducts = productService.selectAllProducts();
            request.setAttribute(NUMBER_OF_PRODUCT_TYPES, Integer.parseInt(request.getParameter(NUMBER_OF_PRODUCT_TYPES)));
            request.setAttribute(ALL_PRODUCTS, allProducts);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_DELIVERY_PAGE);
        requestDispatcher.forward(request, response);
    }
}
