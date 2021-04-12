package by.tc.shop.dao.impl;

import by.tc.shop.bean.DeliveryItem;
import by.tc.shop.dao.DeliveryItemDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.executor.QueryExecutor;

import java.sql.ResultSet;

public class SQLDeliveryItemDAO implements DeliveryItemDAO {
    public static final String INSERT_NEW_DELIVERY_ITEM = "INSERT INTO m2m_products_deliveries (pd_delivery_id,pd_product_id,pd_number_of_products) VALUES (?,?,?)";

    @Override
    public Long insertDeliveryItem(DeliveryItem deliveryItem) throws DAOException {
        return (Long) QueryExecutor.executeSimpleQuery(INSERT_NEW_DELIVERY_ITEM, (connection, statement) -> {
            statement.setLong(1, deliveryItem.getDelivery().getId());
            statement.setLong(2, deliveryItem.getProduct().getId());
            statement.setInt(3, deliveryItem.getNumberOfProducts());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return null;
        });
    }
}
