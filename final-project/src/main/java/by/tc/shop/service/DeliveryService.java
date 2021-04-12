package by.tc.shop.service;

import by.tc.shop.bean.Delivery;
import by.tc.shop.service.exception.ServiceException;

public interface DeliveryService {
    Long insertNewDelivery(Delivery delivery) throws ServiceException;

    Delivery selectById(long id) throws ServiceException;
}
