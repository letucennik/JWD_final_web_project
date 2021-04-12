package by.tc.shop.dao.impl;

import by.tc.shop.bean.Order;
import by.tc.shop.bean.OrderItem;
import by.tc.shop.bean.Product;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.OrderDAO;
import by.tc.shop.dao.OrderItemDAO;
import by.tc.shop.dao.ProductDAO;
import by.tc.shop.dao.exception.ConnectionPoolException;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.pool.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDAOTest {
    private static final Logger logger = Logger.getLogger(OrderItemDAOTest.class);
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
    public void insertOrderItem() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        OrderItemDAO orderItemDAO=daoProvider.getOrderItemDAO();
        long orderId=4;
        long productId=1;
        Long inserted=null;
        Order order=selectOrderById(orderId);
        Product product=selectProductById(productId);
        OrderItem orderItem=OrderItem.getBuilderInstance().setOrder(order).setProduct(product).setNumberOfProducts(5).build();
        try{
            inserted=orderItemDAO.insertOrderItem(orderItem);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertNotNull(inserted);
    }

    @Test
    public void selectOrderItemsByOrder() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        OrderItemDAO orderItemDAO=daoProvider.getOrderItemDAO();
        long orderId=4;
        List<OrderItem> orderItemList=null;
        try{
            orderItemList=orderItemDAO.selectOrderItemsByOrder(orderId);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(1,orderItemList.size());
    }

    @Test
    public void deleteOrderItem() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        OrderItemDAO orderItemDAO=daoProvider.getOrderItemDAO();
        long id=1;
        try{
            orderItemDAO.deleteOrderItem(id);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        OrderItem orderItem=selectById(id);
        assertNull(orderItem);
    }

    @Test
    public void editProductAmount() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        OrderItemDAO orderItemDAO=daoProvider.getOrderItemDAO();
        long id=1;
        try{
            orderItemDAO.editProductAmount(id,7);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        OrderItem orderItem=selectById(id);
        assertEquals(7,orderItem.getNumberOfProducts());

    }

    private Order selectOrderById(long id){
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
    private Product selectProductById(long id){
        DAOProvider daoProvider=DAOProvider.getInstance();
        ProductDAO productDAO=daoProvider.getProductDAO();
        Product product=null;
        try{
            product=productDAO.selectById(id);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        return product;
    }
    private OrderItem selectById(long id){
        DAOProvider daoProvider=DAOProvider.getInstance();
        OrderItemDAO orderItemDAO=daoProvider.getOrderItemDAO();
        OrderItem orderItem=null;
        try{
            orderItem=orderItemDAO.selectById(id);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        return orderItem;
    }
}