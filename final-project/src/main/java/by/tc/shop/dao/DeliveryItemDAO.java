package by.tc.shop.dao;

import by.tc.shop.bean.DeliveryItem;
import by.tc.shop.dao.exception.DAOException;

public interface DeliveryItemDAO {
    Long insertDeliveryItem(DeliveryItem deliveryItem) throws DAOException;
}
