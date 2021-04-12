package by.tc.shop.dao.impl;

import by.tc.shop.bean.Order;
import by.tc.shop.dao.OrderDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.executor.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class SQLOrderDAO extends BaseDAO<Order> implements OrderDAO {
    private static final String INSERT_INTO_ORDERS = "INSERT INTO orders (o_user_id,o_confirmation_date,o_ready_date,o_status) VALUES (?,?,?,?)";
    private static final String SELECT_CART_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE o_user_id=? AND o_status=1";
    private static final String UPDATE_ORDER_STATUS = "UPDATE orders SET o_status = ? WHERE o_id=?";
    private static final String UPDATE_CONFIRMATION_DATE = "UPDATE orders SET o_confirmation_date = ? WHERE o_id=?";
    private static final String UPDATE_READY_DATE = "UPDATE orders SET o_ready_date = ? WHERE o_id=?";
    private static final String SELECT_ACTIVE_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE o_user_id=? AND o_status=2";
    private static final String SELECT_ORDERS_BY_STATUS = "SELECT * FROM orders WHERE o_status = ?";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE o_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM orders WHERE o_id=?";


    @Override
    public Order selectById(long id) throws DAOException {
        return executeSelectByID(SELECT_BY_ID, id);
    }

    @Override
    public Long insertNewOrder(Order order) throws DAOException {
        return (Long) QueryExecutor.executeSimpleQuery(INSERT_INTO_ORDERS, (connection, statement) -> {
            statement.setLong(1, order.getUserID());
            statement.setTimestamp(2, order.getConfirmationDate());
            statement.setTimestamp(3, order.getReadyDate());
            statement.setInt(4, order.getStatus());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return null;
        });
    }

    @Override
    public Order selectUserCartOrder(long userId) throws DAOException {
        return selectByLongParameter(SELECT_CART_ORDERS_BY_USER_ID, userId);
    }

    @Override
    public List<Order> selectActiveOrdersByUser(long userId) throws DAOException {
        return QueryExecutor.executeSimpleQuery(SELECT_ACTIVE_ORDERS_BY_USER_ID, statement -> {
            statement.setLong(1, userId);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public List<Order> selectOrdersByStatus(int status) throws DAOException {
        return QueryExecutor.executeSimpleQuery(SELECT_ORDERS_BY_STATUS, statement -> {
            statement.setInt(1, status);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public void updateOrderStatus(long orderId, int status) throws DAOException {
        QueryExecutor.executeSimpleQuery(UPDATE_ORDER_STATUS, statement -> {
            statement.setInt(1, status);
            statement.setLong(2, orderId);
            statement.executeUpdate();
        });
    }

    @Override
    public void updateConfirmationDate(long orderId, Timestamp confirmationDate) throws DAOException {
        QueryExecutor.executeSimpleQuery(UPDATE_CONFIRMATION_DATE, statement -> {
            statement.setTimestamp(1, confirmationDate);
            statement.setLong(2, orderId);
            statement.executeUpdate();
        });
    }

    @Override
    public void deleteOrder(long orderId) throws DAOException {
        QueryExecutor.executeSimpleQuery(DELETE_ORDER, statement -> {
            statement.setLong(1, orderId);
            statement.executeUpdate();
        });
    }

    @Override
    public void updateReadyDate(long orderId, Timestamp readyDate) throws DAOException {
        QueryExecutor.executeSimpleQuery(UPDATE_READY_DATE, statement -> {
            statement.setTimestamp(1, readyDate);
            statement.setLong(2, orderId);
            statement.executeUpdate();
        });
    }

    private Order selectByLongParameter(String query, long parameter) throws DAOException {
        return (Order) QueryExecutor.executeSimpleQuery(query, statement -> {
            statement.setLong(1, parameter);
            return executeStatementAndParseResultSet(statement);
        });
    }


    @Override
    public Order parseResultSet(ResultSet resultSet) throws DAOException {
        Order order;
        try {
            long id = resultSet.getLong(1);
            long userID = resultSet.getLong(2);
            Timestamp confirmationDate = resultSet.getTimestamp(3);
            Timestamp readyDate = resultSet.getTimestamp(4);
            int status = resultSet.getInt(5);
            order = new Order(id, userID, confirmationDate, readyDate, status);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return order;
    }
}
