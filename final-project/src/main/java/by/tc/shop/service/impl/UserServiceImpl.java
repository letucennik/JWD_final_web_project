package by.tc.shop.service.impl;

import by.tc.shop.bean.User;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.UserDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.service.UserService;
import by.tc.shop.service.exception.ServiceException;
import by.tc.shop.service.exception.impl.EmailExistsException;
import by.tc.shop.service.exception.impl.UserServiceException;
import by.tc.shop.service.exception.impl.UsernameExistsException;
import by.tc.shop.service.validation.SignUpValidator;
import by.tc.shop.service.validation.exception.InvalidEmailException;
import by.tc.shop.service.validation.exception.InvalidLoginException;
import by.tc.shop.service.validation.exception.InvalidPasswordException;

import java.util.List;

public class UserServiceImpl implements UserService {
    public static final String INVALID_LOGIN = "Invalid login";

    @Override
    public User authorization(String login, String password) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        User user;
        try {
            user = userDAO.authorization(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public Long registration(User user) throws UserServiceException, UsernameExistsException, EmailExistsException, InvalidLoginException, InvalidPasswordException, InvalidEmailException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        Long registered;
        if (!SignUpValidator.isLoginValid(user.getUsername())) {
            throw new InvalidLoginException(INVALID_LOGIN);
        }
        if (!SignUpValidator.isPasswordValid(user.getPassword())) {
            throw new InvalidPasswordException();
        }
        if (!SignUpValidator.isEmailValid(user.getEmail())) {
            throw new InvalidEmailException();
        }
        try {
            if (userDAO.selectByEmail(user.getEmail()) != null) {
                throw new EmailExistsException();
            }
            if (userDAO.selectByUserName(user.getUsername()) != null) {
                throw new UsernameExistsException();
            }
            registered = userDAO.registration(user);
        } catch (DAOException e) {
            throw new UserServiceException(e);
        }
        return registered;
    }

    @Override
    public List<User> selectAllClients() throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        List<User> allClients;
        try {
            allClients = userDAO.selectAllClients();
        } catch (DAOException e) {
            throw new UserServiceException(e);
        }
        return allClients;
    }

    @Override
    public List<User> selectBannedClients() throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        List<User> bannedClients;
        try {
            bannedClients = userDAO.selectBannedClients();
        } catch (DAOException e) {
            throw new UserServiceException(e);
        }
        return bannedClients;
    }

    @Override
    public void banUser(long userId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        try {
            userDAO.banUser(userId);
        } catch (DAOException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public void unbanUser(long userId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        try {
            userDAO.unbanUser(userId);
        } catch (DAOException e) {
            throw new UserServiceException(e);
        }
    }
}
