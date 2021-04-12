package by.tc.shop.service.impl;

import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.ProductAtStoreDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.service.ProductAtStoreService;
import by.tc.shop.service.exception.ServiceException;

public class ProductAtStoreServiceImpl implements ProductAtStoreService {
    @Override
    public Integer getProductAmount(long productId) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ProductAtStoreDAO productAtStoreDAO = daoProvider.getProductAtStoreDAO();
        Integer productAmount;
        try {
            productAmount = productAtStoreDAO.getProductAmount(productId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return productAmount;
    }

    @Override
    public void changeProductAmount(long productId, int amount) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ProductAtStoreDAO productAtStoreDAO = daoProvider.getProductAtStoreDAO();
        try {
            productAtStoreDAO.changeProductAmount(productId, amount);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long insertProductAmount(long productId, int amount) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ProductAtStoreDAO productAtStoreDAO = daoProvider.getProductAtStoreDAO();
        Long inserted = null;
        try {
            inserted = productAtStoreDAO.insertProductAmount(productId, amount);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return inserted;
    }
}
