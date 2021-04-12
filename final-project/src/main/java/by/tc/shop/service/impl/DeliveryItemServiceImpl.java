package by.tc.shop.service.impl;

import by.tc.shop.bean.DeliveryItem;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.DeliveryItemDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.service.DeliveryItemService;
import by.tc.shop.service.exception.ServiceException;

public class DeliveryItemServiceImpl implements DeliveryItemService {
    @Override
    public Long insertDeliveryItem(DeliveryItem deliveryItem) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        DeliveryItemDAO deliveryItemDAO = provider.getDeliveryItemDAO();
        Long insertedId;
        try {
            insertedId = deliveryItemDAO.insertDeliveryItem(deliveryItem);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return insertedId;
    }
}
