package by.tc.shop.dao.impl;

import by.tc.shop.bean.Delivery;
import by.tc.shop.dao.DeliveryDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.executor.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SQLDeliveryDAO extends BaseDAO<Delivery> implements DeliveryDAO {
    private static final String INSERT_DELIVERY = "INSERT INTO deliveries (d_date) VALUES (?)";
    private static final String SELECT_BY_ID = "SELECT * FROM deliveries WHERE d_id = ?";

    @Override
    public Long insertNewDelivery(Delivery delivery) throws DAOException {
        return (Long) QueryExecutor.executeSimpleQuery(INSERT_DELIVERY, (connection, statement) -> {
            statement.setTimestamp(1, delivery.getDate());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return null;
        });
    }

    @Override
    public Delivery selectById(long id) throws DAOException {
        return (Delivery) executeSelectByID(SELECT_BY_ID, id);
    }

    @Override
    public Delivery parseResultSet(ResultSet resultSet) throws DAOException {
        Delivery delivery = null;
        try {
            long id = resultSet.getLong(1);
            Timestamp date = resultSet.getTimestamp(2);
            delivery = new Delivery(id, date);

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return delivery;
    }
}
