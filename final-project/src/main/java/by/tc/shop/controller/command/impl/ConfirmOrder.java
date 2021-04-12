package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.*;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.*;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class ConfirmOrder implements Command {
    public static final String USER_PARAMETER = "user";
    private static final String GO_TO_ACTIVE_ORDERS_PAGE = "Controller?command=gotoactiveorderspage&confirmed=true";
    private static final String GO_TO_USER_DETAILS_PAGE = "/WEB-INF/jsp/user_details.jsp";
    private static final String GO_TO_CART_PAGE_OVERFLOW = "Controller?command=gotocartpage&message=overflow";
    private static final String AVAILABLE_NUMBER = "availableProductAmount";
    private static final String PRODUCT_WITH_OVERFLOW = "productWithOverflow";
    private Product product;
    private int availableAmount;
    private static final Logger logger = Logger.getLogger(ConfirmOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order;

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(USER_PARAMETER);
        UserDetails userDetails;
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserDetailsService userDetailsService = serviceProvider.getUserDetailsService();
        OrderService orderService = serviceProvider.getOrderService();
        try {
            order = orderService.selectUserCartOrder(user.getId());
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        if (!updateProductsAmount(order.getId(), request, response)) {
            session.setAttribute(PRODUCT_WITH_OVERFLOW, product);
            session.setAttribute(AVAILABLE_NUMBER, availableAmount);
            response.sendRedirect(GO_TO_CART_PAGE_OVERFLOW);
            return;
        }
        try {
            userDetails = userDetailsService.selectByUserId(user.getId());
        } catch (ServiceException e) {
            throw new CommandException();
        }
        if (userDetails == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_USER_DETAILS_PAGE);
            requestDispatcher.forward(request, response);
        }
        try {
            orderService.updateOrderStatus(order.getId(), Order.STATUS_IN_PROCESS);
            orderService.updateConfirmationDate(order.getId(), Timestamp.from(Instant.now()));
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        response.sendRedirect(GO_TO_ACTIVE_ORDERS_PAGE);
    }

    private boolean updateProductsAmount(long orderId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ProductAtStoreService productAtStoreService = serviceProvider.getProductAtStoreService();
        OrderItemService orderItemService = serviceProvider.getOrderItemService();
        List<OrderItem> orderItems;
        try {
            orderItems = orderItemService.selectOrderItemsByOrder(orderId);
        } catch (ServiceException e) {
            throw new CommandException();
        }
        for (OrderItem orderItem : orderItems) {
            Product currentProduct = orderItem.getProduct();
            int productAmount = orderItem.getNumberOfProducts();
            Integer amountAtStore;
            try {
                amountAtStore = productAtStoreService.getProductAmount(currentProduct.getId());
                if (productAmount > amountAtStore) {
                    product = currentProduct;
                    availableAmount = amountAtStore;
                    return false;
                }
                productAtStoreService.changeProductAmount(currentProduct.getId(), amountAtStore - productAmount);
            } catch (ServiceException e) {
                throw new CommandException();
            }
        }
        return true;
    }
}
