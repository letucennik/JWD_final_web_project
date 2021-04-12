package by.tc.shop.dao;

import by.tc.shop.dao.exception.DAOException;

public interface ProductAtStoreDAO {
    Integer getProductAmount(long productId) throws DAOException;

    void changeProductAmount(long productId, int amount) throws DAOException;

    Long insertProductAmount(long productId, int amount) throws DAOException;
}
