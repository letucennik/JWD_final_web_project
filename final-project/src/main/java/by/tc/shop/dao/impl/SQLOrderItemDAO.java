package by.tc.shop.dao.impl;

import by.tc.shop.bean.Order;
import by.tc.shop.bean.OrderItem;
import by.tc.shop.bean.Product;
import by.tc.shop.dao.OrderItemDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.executor.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLOrderItemDAO extends BaseDAO<OrderItem> implements OrderItemDAO {
    public static final String INSERT_NEW_ORDER_ITEM = "INSERT INTO m2m_order_product (op_order,op_product,op_number_of_products) VALUES (?,?,?)";
    public static final String SELECT_CART_ORDER_ITEMS_BY_USER = "SELECT * FROM m2m_order_product WHERE op_order = ?";
    public static final String SELECT_PRODUCTS_BY_ORDER = "SELECT * FROM m2m_order_product WHERE op_order = ? AND op_product = ?";
    public static final String DELETE_ORDER_ITEM = "DELETE FROM m2m_order_product WHERE op_id = ?";
    public static final String EDIT_PRODUCT_AMOUNT = "UPDATE m2m_order_product SET op_number_of_products = ? WHERE op_id = ?";
    public static final String SELECT_BY_ID = "SELECT * FROM m2m_order_product WHERE op_id=?";

    @Override
    public OrderItem selectById(long id) throws DAOException {
        return executeSelectByID(SELECT_BY_ID, id);
    }

    @Override
    public Long insertOrderItem(OrderItem item) throws DAOException {
        return (Long) QueryExecutor.executeSimpleQuery(INSERT_NEW_ORDER_ITEM, (connection, statement) -> {
            statement.setLong(1, item.getOrder().getId());
            statement.setLong(2, item.getProduct().getId());
            statement.setInt(3, item.getNumberOfProducts());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return null;
        });
    }

    @Override
    public List<OrderItem> selectOrderItemsByOrder(long orderId) throws DAOException {
        return QueryExecutor.executeSimpleQuery(SELECT_CART_ORDER_ITEMS_BY_USER, statement -> {
            statement.setLong(1, orderId);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public boolean isProductAlreadyInOrder(long orderId, long productId) throws DAOException {
        return QueryExecutor.executeSimpleQuery(SELECT_PRODUCTS_BY_ORDER, statement -> {
            statement.setLong(1, orderId);
            statement.setLong(2, productId);
            return executeStatementAndParseResultSet(statement) != null;
        });
    }

    @Override
    public void deleteOrderItem(long id) throws DAOException {
        QueryExecutor.executeSimpleQuery(DELETE_ORDER_ITEM, statement -> {
            statement.setLong(1, id);
            statement.executeUpdate();
        });
    }

    @Override
    public void editProductAmount(long id, int newProductAmount) throws DAOException {
        QueryExecutor.executeSimpleQuery(EDIT_PRODUCT_AMOUNT, statement -> {
            statement.setInt(1, newProductAmount);
            statement.setLong(2, id);
            statement.executeUpdate();
        });
    }


    @Override
    public OrderItem parseResultSet(ResultSet resultSet) throws DAOException {
        OrderItem orderItem;
        try {
            long id = resultSet.getLong(1);
            long orderId = resultSet.getLong(2);
            long productId = resultSet.getLong(3);
            int numberOfProducts = resultSet.getInt(4);
            orderItem = OrderItem.getBuilderInstance().setId(id).setOrder(new Order(orderId)).
                    setProduct(new Product(productId)).setNumberOfProducts(numberOfProducts).build();
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return orderItem;
    }
}
