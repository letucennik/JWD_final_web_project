package by.tc.shop.service.impl;

import by.tc.shop.bean.Delivery;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.DeliveryDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.service.DeliveryService;
import by.tc.shop.service.exception.ServiceException;

public class DeliveryServiceImpl implements DeliveryService {
    @Override
    public Long insertNewDelivery(Delivery delivery) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        DeliveryDAO deliveryDAO = provider.getDeliveryDAO();
        Long inserted;
        try {
            inserted = deliveryDAO.insertNewDelivery(delivery);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return inserted;
    }

    @Override
    public Delivery selectById(long id) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        DeliveryDAO deliveryDAO = provider.getDeliveryDAO();
        Delivery delivery;
        try {
            delivery = deliveryDAO.selectById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return delivery;
    }
}
