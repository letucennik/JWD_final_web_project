package by.tc.shop.dao;

import by.tc.shop.bean.Order;
import by.tc.shop.dao.exception.DAOException;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDAO {
    Order selectById(long id) throws DAOException;

    Long insertNewOrder(Order order) throws DAOException;

    Order selectUserCartOrder(long userId) throws DAOException;

    List<Order> selectActiveOrdersByUser(long userId) throws DAOException;

    List<Order> selectOrdersByStatus(int status) throws DAOException;

    void updateOrderStatus(long orderId, int status) throws DAOException;

    void updateConfirmationDate(long orderId, Timestamp confirmationDate) throws DAOException;

    void deleteOrder(long orderId) throws DAOException;

    void updateReadyDate(long orderId, Timestamp readyDate) throws DAOException;
}
