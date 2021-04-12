package by.tc.shop.service;

import by.tc.shop.bean.User;
import by.tc.shop.service.exception.ServiceException;
import by.tc.shop.service.exception.impl.EmailExistsException;
import by.tc.shop.service.exception.impl.UserServiceException;
import by.tc.shop.service.exception.impl.UsernameExistsException;
import by.tc.shop.service.validation.exception.InvalidEmailException;
import by.tc.shop.service.validation.exception.InvalidLoginException;
import by.tc.shop.service.validation.exception.InvalidPasswordException;

import java.util.List;

public interface UserService {
    User authorization(String login, String password) throws ServiceException;

    Long registration(User user) throws UserServiceException, EmailExistsException, UsernameExistsException, InvalidLoginException, InvalidPasswordException, InvalidEmailException;

    List<User> selectAllClients() throws ServiceException;

    List<User> selectBannedClients() throws ServiceException;

    void banUser(long userId) throws ServiceException;

    void unbanUser(long userId) throws ServiceException;
}
