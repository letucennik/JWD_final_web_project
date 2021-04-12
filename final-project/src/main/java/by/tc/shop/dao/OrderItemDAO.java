package by.tc.shop.dao;

import by.tc.shop.bean.OrderItem;
import by.tc.shop.dao.exception.DAOException;

import java.util.List;

public interface OrderItemDAO {
    OrderItem selectById(long id) throws DAOException;

    Long insertOrderItem(OrderItem item) throws DAOException;

    List<OrderItem> selectOrderItemsByOrder(long orderId) throws DAOException;

    boolean isProductAlreadyInOrder(long orderId, long productId) throws DAOException;

    void deleteOrderItem(long id) throws DAOException;

    void editProductAmount(long id, int newProductAmount) throws DAOException;
}
