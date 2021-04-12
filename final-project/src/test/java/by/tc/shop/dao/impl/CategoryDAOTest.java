package by.tc.shop.dao.impl;

import by.tc.shop.bean.Category;
import by.tc.shop.dao.CategoryDAO;
import by.tc.shop.dao.DAOProvider;
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

public class CategoryDAOTest {
    private static final Logger logger = Logger.getLogger(CategoryDAOTest.class);
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
    public void selectByName() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        CategoryDAO categoryDAO=daoProvider.getCategoryDAO();
        Category category=null;
        String name="Eye & lip care";
        try{
            category=categoryDAO.selectByName(name);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(1,category.getId());
    }

    @Test
    public void selectByNameRu() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        CategoryDAO categoryDAO=daoProvider.getCategoryDAO();
        Category category=null;
        String name="Для глаз и губ";
        try{
            category=categoryDAO.selectByNameRu(name);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(1,category.getId());
    }

    @Test
    public void selectAllCategories() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        CategoryDAO categoryDAO=daoProvider.getCategoryDAO();
        List<Category> allCategories=null;
        try{
            allCategories=categoryDAO.selectAllCategories();
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(2,allCategories.size());
    }
}