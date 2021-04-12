package by.tc.shop.dao.impl;

import by.tc.shop.bean.User;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.UserDAO;
import by.tc.shop.dao.exception.ConnectionPoolException;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.pool.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

import static org.junit.Assert.*;

public class UserDAOTest {
    private static final Logger logger = Logger.getLogger(UserDAOTest.class);

    @org.junit.Before
    public void setUp() throws Exception {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            logger.log(Level.FATAL, e);
        }
    }

    @org.junit.AfterClass
    public static void tearDown() {
        try {
            ConnectionPool.getInstance().dispose();
        } catch (ConnectionPoolException e) {
            logger.log(Level.FATAL, e);
        }
    }

    @org.junit.Test
    public void selectByUserName() {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        User selected = null;
        try {
            selected = userDAO.selectByUserName("letucennik");
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(selected.getId(), 1);
    }

    @org.junit.Test
    public void selectByEmail() {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        User selected = null;
        try {
            selected = userDAO.selectByEmail("mirabulhakava@gmail.com");
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(selected.getId(), 1);
    }


    @org.junit.Test
    public void registrationOk() {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        Long inserted = null;
        User user;
        user = User.getBuilderInstance().setUsername("User1").setPassword("Peter979").setEmail("lala@gmail.com").
                setStatus(User.STATUS_ACTIVE).setRole(User.ROLE_CLIENT).build();
        try {
            inserted = userDAO.registration(user);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertNotNull(inserted);
    }

    @org.junit.Test
    public void registrationUsernameExists() {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        Long inserted = null;
        User user;
        user = User.getBuilderInstance().setUsername("User1").setPassword("Peter979").setEmail("lala1@gmail.com").
                setStatus(User.STATUS_ACTIVE).setRole(User.ROLE_CLIENT).build();
        try {
            inserted = userDAO.registration(user);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertNull(inserted);
    }

    @org.junit.Test
    public void selectAllClients() {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        List<User> allClients=null;
        try{
            allClients=userDAO.selectAllClients();
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(allClients.size(),2);
    }

    @org.junit.Test
    public void selectBannedClients() {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        List<User> bannedClients=null;
        try{
            bannedClients=userDAO.selectBannedClients();
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(bannedClients.size(),1);
    }

    @org.junit.Test
    public void banUser() {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        long id=7;
        String username="User2";
        try{
            userDAO.banUser(id);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        User user=null;
        try{
            user=userDAO.selectByUserName(username);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(user.getStatus(),2);

    }

    @org.junit.Test
    public void authorization() {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        User user=null;
        String username="letucennik";
        String password="Peter979";
        try {
            user=userDAO.authorization(username,password);
        }catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        assertNotNull(user);
    }
}