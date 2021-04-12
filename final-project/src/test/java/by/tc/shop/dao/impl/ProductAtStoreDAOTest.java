package by.tc.shop.dao.impl;

import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.ProductAtStoreDAO;
import by.tc.shop.dao.exception.ConnectionPoolException;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.pool.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductAtStoreDAOTest {
    private static final Logger logger = Logger.getLogger(ProductAtStoreDAOTest.class);
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
    public void getProductAmount() {

        long productId=1;
        int productAmount=productAmount(productId);
        assertEquals(4,productAmount);
    }

    @Test
    public void changeProductAmount() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        ProductAtStoreDAO productAtStoreDAO=daoProvider.getProductAtStoreDAO();

        long productId=1;
        try{
            productAtStoreDAO.changeProductAmount(1,6);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        int productAmount=productAmount(productId);
        assertEquals(6,productAmount);
    }

    @Test
    public void insertProductAmount() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        ProductAtStoreDAO productAtStoreDAO=daoProvider.getProductAtStoreDAO();
        Long inserted=null;
        long productId=2;
        try{
            inserted=productAtStoreDAO.insertProductAmount(productId,3);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        int productAmount=productAmount(productId);
        assertEquals(3,productAmount);

    }
    private int productAmount(long productId){
        DAOProvider daoProvider=DAOProvider.getInstance();
        ProductAtStoreDAO productAtStoreDAO=daoProvider.getProductAtStoreDAO();
        int productAmount=0;
        try{
            productAmount=productAtStoreDAO.getProductAmount(productId);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        return productAmount;
    }
}