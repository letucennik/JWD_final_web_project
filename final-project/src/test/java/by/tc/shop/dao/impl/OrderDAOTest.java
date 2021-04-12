package by.tc.shop.dao.impl;

import by.tc.shop.bean.Order;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.OrderDAO;
import by.tc.shop.dao.exception.ConnectionPoolException;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.pool.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDAOTest {
    private static final Logger logger = Logger.getLogger(OrderDAOTest.class);
    @Before
    public void setUp() throws Exception {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            logger.log(Level.FATAL, e);
        }
    }

    @After
    public void tearDown() throws Exception {
        try {
            ConnectionPool.getInstance().dispose();
        } catch (ConnectionPoolException e) {
            logger.log(Level.FATAL, e);
        }
    }


    @Test
    public void insertNewOrder() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        OrderDAO orderDAO=daoProvider.getOrderDAO();
        long userId=1;
        Order order=Order.getBuilderInstance().setUserId(1).setStatus(Order.STATUS_IN_CART).build();
        Long inserted=null;
        try{
            inserted= orderDAO.insertNewOrder(order);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertNotNull(inserted);
    }

    @Test
    public void selectUserCartOrder() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        OrderDAO orderDAO=daoProvider.getOrderDAO();
        Order userCartOrder=null;
        try{
            userCartOrder=orderDAO.selectUserCartOrder(1);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertNotNull(userCartOrder);
    }

    @Test
    public void selectActiveOrdersByUser() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        OrderDAO orderDAO=daoProvider.getOrderDAO();
        List<Order> activeOrders=null;
        try{
            activeOrders=orderDAO.selectActiveOrdersByUser(1);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(1,activeOrders.size());
    }


    @Test
    public void updateOrderStatus() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        OrderDAO orderDAO=daoProvider.getOrderDAO();
        long orderId=3;
        try{
            orderDAO.updateOrderStatus(orderId,Order.STATUS_IN_PROCESS);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        Order order=selectById(orderId);
        assertEquals(Order.STATUS_IN_PROCESS,order.getStatus());
    }

    @Test
    public void updateConfirmationDate() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        OrderDAO orderDAO=daoProvider.getOrderDAO();
        long orderId=3;
        try{
            orderDAO.updateConfirmationDate(orderId, Timestamp.from(Instant.now()));
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        Order order=selectById(orderId);
        assertNotNull(order.getConfirmationDate());
    }

    @Test
    public void deleteOrder() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        OrderDAO orderDAO=daoProvider.getOrderDAO();
        long orderId=3;
        try{
            orderDAO.deleteOrder(orderId);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        Order order=selectById(orderId);
        assertNull(order);
    }

    private Order selectById(long id){
        DAOProvider daoProvider=DAOProvider.getInstance();
        OrderDAO orderDAO=daoProvider.getOrderDAO();
        Order order=null;
        try{
            order=orderDAO.selectById(id);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        return order;
    }
}