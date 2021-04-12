package by.tc.shop.dao.impl;

import by.tc.shop.bean.Delivery;
import by.tc.shop.bean.DeliveryItem;
import by.tc.shop.bean.Product;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.DeliveryItemDAO;
import by.tc.shop.dao.exception.ConnectionPoolException;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.pool.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeliveryItemDAOTest {
    private static final Logger logger = Logger.getLogger(DeliveryItemDAOTest.class);
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
    public void insertDeliveryItem() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        DeliveryItemDAO deliveryItemDAO=daoProvider.getDeliveryItemDAO();
        Delivery delivery=new Delivery();
        delivery.setId(1);
        Product product=new Product();
        product.setId(1);
        DeliveryItem deliveryItem=new DeliveryItem();
        deliveryItem.setDelivery(delivery);
        deliveryItem.setProduct(product);
        deliveryItem.setNumberOfProducts(5);
        Long inserted=null;
        try{
            inserted=deliveryItemDAO.insertDeliveryItem(deliveryItem);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertNotNull(inserted);
    }


}