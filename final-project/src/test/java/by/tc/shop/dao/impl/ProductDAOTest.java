package by.tc.shop.dao.impl;

import by.tc.shop.bean.Category;
import by.tc.shop.bean.Product;
import by.tc.shop.dao.DAOProvider;
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

public class ProductDAOTest {
    private static final Logger logger = Logger.getLogger(ProductDAOTest.class);
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
    public void selectProductsByCategory() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        ProductDAO productDAO=daoProvider.getProductDAO();
        List<Product> productsByCategory=null;
        try{
            productsByCategory= productDAO.selectProductsByCategory(1);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(1,productsByCategory.size());
    }

    @Test
    public void selectAllProducts() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        ProductDAO productDAO=daoProvider.getProductDAO();
        List<Product> allProducts=null;
        try{
            allProducts= productDAO.selectAllProducts();
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(2,allProducts.size());
    }

    @Test
    public void insertProduct() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        ProductDAO productDAO=daoProvider.getProductDAO();
        Product product=Product.getBuilderInstance().setCategory(new Category(1,"Eye & lip care","Для глаз и губ")).
                setNameEn("Wrinkle Smoothing Eye Cream").setNameRu("Омолаживающий крем для глаз").setPicturePath("/img/wrinkle_smoothing_eye_cream.jpg").setPrice(50).build();
        Long inserted=null;
        try{
            inserted= productDAO.insertProduct(product);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertNotNull(inserted);

    }

    @Test
    public void deleteProduct() {
    }
}