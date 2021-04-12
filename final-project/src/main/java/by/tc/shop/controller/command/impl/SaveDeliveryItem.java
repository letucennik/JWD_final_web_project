package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.Delivery;
import by.tc.shop.bean.DeliveryItem;
import by.tc.shop.bean.Product;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.*;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveDeliveryItem implements Command {
    private static final Logger logger = Logger.getLogger(SaveDeliveryItem.class);
    public static final String DELIVERY_ID = "delivery";
    public static final String PRODUCT_ID = "product";
    public static final String AMOUNT = "amount";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        DeliveryService deliveryService = serviceProvider.getDeliveryService();
        ProductService productService = serviceProvider.getProductService();
        String deliveryId = request.getParameter(DELIVERY_ID);
        String productId = request.getParameter(PRODUCT_ID);
        String numberOfProducts = request.getParameter(AMOUNT);
        Delivery delivery;
        try {
            delivery = deliveryService.selectById(Long.parseLong(deliveryId));
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }

        Product product;
        try {
            product = productService.selectById(Long.parseLong(productId));
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }

        DeliveryItem deliveryItem = new DeliveryItem();
        deliveryItem.setDelivery(delivery);
        deliveryItem.setProduct(product);
        deliveryItem.setNumberOfProducts(Integer.parseInt(numberOfProducts));

        DeliveryItemService deliveryItemService = serviceProvider.getDeliveryItemService();
        try {
            deliveryItemService.insertDeliveryItem(deliveryItem);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        ProductAtStoreService productAtStoreService = serviceProvider.getProductAtStoreService();
        long productID = Long.parseLong(productId);
        try {
            int currentAmount = productAtStoreService.getProductAmount(productID);
            productAtStoreService.changeProductAmount(Long.parseLong(productId), currentAmount + Integer.parseInt(numberOfProducts));
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }

    }
}
