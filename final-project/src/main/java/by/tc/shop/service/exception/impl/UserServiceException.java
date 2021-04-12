package by.tc.shop.service.exception.impl;

import by.tc.shop.service.exception.ServiceException;

public class UserServiceException extends ServiceException {

    public UserServiceException(Exception e) {
        super(e);
    }

    public UserServiceException(String message, Exception e) {
        super(message, e);
    }
}
