package by.tc.shop.service;

import by.tc.shop.bean.OrderItem;
import by.tc.shop.service.exception.ServiceException;

import java.util.List;

public interface OrderItemService {
    Long insertNewOrderItem(OrderItem item) throws ServiceException;

    List<OrderItem> selectOrderItemsByOrder(long orderId) throws ServiceException;

    boolean isProductAlreadyInCart(long orderId, long productId) throws ServiceException;

    void deleteOrderItem(long id) throws ServiceException;

    void editNumberOfProducts(long id, int newNumberOfProducts) throws ServiceException;
}
