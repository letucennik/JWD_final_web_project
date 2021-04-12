package by.tc.shop.dao;

import by.tc.shop.dao.impl.*;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();
    private final UserDAO userDAO = new SQLUserDAO();
    private final UserDetailsDAO userDetailsDAO = new SQLUserDetailsDAO();
    private final ProductDAO productDAO = new SQLProductDAO();
    private final CategoryDAO categoryDAO = new SQLCategoryDAO();
    private final OrderDAO orderDAO = new SQLOrderDAO();
    private final OrderItemDAO orderItemDAO = new SQLOrderItemDAO();
    private final ProductAtStoreDAO productAtStoreDAO = new SQLProductAtStoreDAO();
    private final DeliveryDAO deliveryDAO = new SQLDeliveryDAO();
    private final DeliveryItemDAO deliveryItemDAO = new SQLDeliveryItemDAO();

    private DAOProvider() {
    }

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public OrderItemDAO getOrderItemDAO() {
        return orderItemDAO;
    }

    public UserDetailsDAO getUserDetailsDAO() {
        return userDetailsDAO;
    }

    public ProductAtStoreDAO getProductAtStoreDAO() {
        return productAtStoreDAO;
    }

    public DeliveryDAO getDeliveryDAO() {
        return deliveryDAO;
    }

    public DeliveryItemDAO getDeliveryItemDAO() {
        return deliveryItemDAO;
    }
}
