package by.tc.shop.service;

import by.tc.shop.service.exception.ServiceException;

public interface ProductAtStoreService {
    Integer getProductAmount(long productId) throws ServiceException;

    void changeProductAmount(long productId, int amount) throws ServiceException;

    Long insertProductAmount(long productId, int amount) throws ServiceException;
}
