package by.tc.shop.dao.impl;

import by.tc.shop.bean.Delivery;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.DeliveryDAO;
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

import static org.junit.Assert.*;

public class DeliveryDAOTest {
    private static final Logger logger = Logger.getLogger(DeliveryDAOTest.class);
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
    public void insertNewDelivery() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        DeliveryDAO deliveryDAO=daoProvider.getDeliveryDAO();
        Delivery delivery=new Delivery();
        delivery.setDate(Timestamp.from(Instant.now()));
        Long inserted=null;
        try{
            inserted= deliveryDAO.insertNewDelivery(delivery);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertNotNull(inserted);
    }

    @Test
    public void selectById() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        DeliveryDAO deliveryDAO=daoProvider.getDeliveryDAO();
        Delivery delivery=null;
        long id=1;
        try{
            delivery=deliveryDAO.selectById(id);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertNotNull(delivery);
    }
}