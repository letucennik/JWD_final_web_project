package by.tc.shop.dao.impl;

import by.tc.shop.dao.ProductAtStoreDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.executor.QueryExecutor;

import java.sql.ResultSet;

public class SQLProductAtStoreDAO implements ProductAtStoreDAO {
    public static final String GET_PRODUCT_AMOUNT = "SELECT pas_number_of_products FROM products_at_store WHERE pas_product_id = ?";
    public static final String UPDATE_PRODUCT_AMOUNT = "UPDATE products_at_store SET pas_number_of_products = ? WHERE pas_product_id = ?";
    public static final String INSERT_PRODUCT_AMOUNT = "INSERT INTO products_at_store (pas_product_id,pas_number_of_products) VALUES (?,?)";

    @Override
    public Integer getProductAmount(long productId) throws DAOException {
        return (Integer) QueryExecutor.executeSimpleQuery(GET_PRODUCT_AMOUNT, statement -> {
            statement.setLong(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return null;
        });
    }

    @Override
    public void changeProductAmount(long productId, int amount) throws DAOException {
        QueryExecutor.executeSimpleQuery(UPDATE_PRODUCT_AMOUNT, statement -> {
            statement.setInt(1, amount);
            statement.setLong(2, productId);
            statement.executeUpdate();
        });
    }

    @Override
    public Long insertProductAmount(long productId, int amount) throws DAOException {
        return (Long) QueryExecutor.executeSimpleQuery(INSERT_PRODUCT_AMOUNT, (connection, statement) -> {
            statement.setLong(1, productId);
            statement.setLong(2, amount);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return null;
        });
    }


}
