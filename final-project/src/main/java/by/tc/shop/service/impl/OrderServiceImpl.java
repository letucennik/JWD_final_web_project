package by.tc.shop.service.impl;

import by.tc.shop.bean.Order;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.OrderDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.service.OrderService;
import by.tc.shop.service.exception.ServiceException;

import java.sql.Timestamp;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public Long insertNewOrder(Order order) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderDAO orderDAO = provider.getOrderDAO();
        Long inserted;
        try {
            inserted = orderDAO.insertNewOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return inserted;
    }

    @Override
    public Order selectUserCartOrder(long userId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderDAO orderDAO = provider.getOrderDAO();
        Order order;
        try {
            order = orderDAO.selectUserCartOrder(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return order;
    }

    @Override
    public List<Order> selectActiveOrdersByUser(long userId) throws ServiceException {
        List<Order> activeUserOrders;
        DAOProvider provider = DAOProvider.getInstance();
        OrderDAO orderDAO = provider.getOrderDAO();
        try {
            activeUserOrders = orderDAO.selectActiveOrdersByUser(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return activeUserOrders;
    }

    @Override
    public List<Order> selectOrdersByStatus(int status) throws ServiceException {
        List<Order> ordersByStatus;
        DAOProvider provider = DAOProvider.getInstance();
        OrderDAO orderDAO = provider.getOrderDAO();
        try {
            ordersByStatus = orderDAO.selectOrdersByStatus(status);
            return ordersByStatus;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateOrderStatus(long orderId, int status) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderDAO orderDAO = provider.getOrderDAO();
        try {
            orderDAO.updateOrderStatus(orderId, status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateConfirmationDate(long orderId, Timestamp confirmationDate) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderDAO orderDAO = provider.getOrderDAO();
        try {
            orderDAO.updateConfirmationDate(orderId, confirmationDate);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateReadyDate(long orderId, Timestamp readyDate) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderDAO orderDAO = provider.getOrderDAO();
        try {
            orderDAO.updateReadyDate(orderId, readyDate);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteOrder(long orderId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderDAO orderDAO = provider.getOrderDAO();
        try {
            orderDAO.deleteOrder(orderId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}
