package by.tc.shop.service;

import by.tc.shop.bean.DeliveryItem;
import by.tc.shop.service.exception.ServiceException;

public interface DeliveryItemService {
    Long insertDeliveryItem(DeliveryItem deliveryItem) throws ServiceException;
}
