package by.tc.shop.dao;

import by.tc.shop.bean.User;
import by.tc.shop.dao.exception.DAOException;

import java.util.List;

public interface UserDAO {
    User selectByUserName(String userName) throws DAOException;

    User selectByEmail(String email) throws DAOException;

    User authorization(String login, String password) throws DAOException;

    Long registration(User user) throws DAOException;

    List<User> selectAllClients() throws DAOException;

    List<User> selectBannedClients() throws DAOException;

    void banUser(long userId) throws DAOException;

    void unbanUser(long userId) throws DAOException;
}
