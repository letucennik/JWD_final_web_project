package by.tc.shop.dao;

import by.tc.shop.bean.Delivery;
import by.tc.shop.dao.exception.DAOException;

public interface DeliveryDAO {
    Long insertNewDelivery(Delivery delivery) throws DAOException;

    Delivery selectById(long id) throws DAOException;
}
