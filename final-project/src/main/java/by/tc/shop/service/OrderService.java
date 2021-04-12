package by.tc.shop.service;

import by.tc.shop.bean.Order;
import by.tc.shop.service.exception.ServiceException;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    Long insertNewOrder(Order order) throws ServiceException;

    Order selectUserCartOrder(long userId) throws ServiceException;

    List<Order> selectActiveOrdersByUser(long userId) throws ServiceException;

    List<Order> selectOrdersByStatus(int status) throws ServiceException;

    void updateOrderStatus(long orderId, int status) throws ServiceException;

    void updateConfirmationDate(long orderId, Timestamp confirmationDate) throws ServiceException;

    void updateReadyDate(long orderId, Timestamp readyDate) throws ServiceException;

    void deleteOrder(long orderId) throws ServiceException;
}
