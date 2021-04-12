package by.tc.shop.dao.impl;

import by.tc.shop.bean.UserDetails;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.UserDetailsDAO;
import by.tc.shop.dao.exception.ConnectionPoolException;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.pool.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDetailsDAOTest {
    private static final Logger logger = Logger.getLogger(UserDetailsDAOTest.class);

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
    public void selectByUserID() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        UserDetailsDAO userDetailsDAO=daoProvider.getUserDetailsDAO();
        long userId=1;
        UserDetails userDetails=null;
        try{
            userDetails=userDetailsDAO.selectByUserID(userId);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(1,userDetails.getId());
    }

    @Test
    public void insertUserDetails() {
        DAOProvider daoProvider=DAOProvider.getInstance();
        UserDetailsDAO userDetailsDAO=daoProvider.getUserDetailsDAO();
        UserDetails userDetails=null;
        long userId=1;
        String firstName="Mира";
        String lastName="Булгакова";
        userDetails=UserDetails.getBuilderInstance().setUserId(userId).setFirstName(firstName).setLastName(lastName).
               setCity("Минск").setPhone("375293333333").setAddress("Кунцевщина 18").build();
        boolean inserted=false;
        try{
            inserted=userDetailsDAO.insertUserDetails(userDetails);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertTrue(inserted);

    }
}