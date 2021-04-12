package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.Order;
import by.tc.shop.bean.OrderItem;
import by.tc.shop.bean.Product;
import by.tc.shop.bean.User;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.OrderItemService;
import by.tc.shop.service.OrderService;
import by.tc.shop.service.ProductService;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*
    у 1 пользователя может быть только 1 заказ в корзине, сколько угодно - в процессе, готовых и отмененных
 */

public class SaveNewOrder implements Command {
    public static final String URL = "url";
    public static final String USER_PARAMETER = "user";
    public static final String CURRENT_PRODUCT_ID = "product";
    private static final Logger logger = Logger.getLogger(SaveNewOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order;
        OrderItem orderItem;
        Product product;

        String url = request.getParameter(URL);
        HttpSession session = request.getSession(true);
        User currentUser = (User) session.getAttribute(USER_PARAMETER);
        long currentProductId = Long.parseLong(request.getParameter(CURRENT_PRODUCT_ID));

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();
        OrderItemService orderItemService = serviceProvider.getOrderItemService();
        ProductService productService = serviceProvider.getProductService();

        order = Order.getBuilderInstance().setUserId(currentUser.getId()).setStatus(Order.STATUS_IN_CART).build();
        Long inserted = null;
        boolean isProductAlreadyInCart = false;
        try {
            product = productService.selectById(currentProductId);
            if (orderService.selectUserCartOrder(currentUser.getId()) == null) {
                inserted = orderService.insertNewOrder(order);
                if (inserted == null) {
                    throw new CommandException();
                }
                order.setId(inserted);
            } else {
                order = orderService.selectUserCartOrder(currentUser.getId());
            }
            isProductAlreadyInCart = orderItemService.isProductAlreadyInCart(order.getId(), currentProductId);
            if (!isProductAlreadyInCart) {
                orderItem = OrderItem.getBuilderInstance().setOrder(order).setProduct(product).setNumberOfProducts(1).build();
                orderItemService.insertNewOrderItem(orderItem);
            }
            response.sendRedirect(url);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }


    }
}
