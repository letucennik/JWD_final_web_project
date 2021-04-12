package by.tc.shop.service;

import by.tc.shop.bean.UserDetails;
import by.tc.shop.service.exception.ServiceException;
import by.tc.shop.service.validation.exception.InvalidAddressException;
import by.tc.shop.service.validation.exception.InvalidPhoneException;
import by.tc.shop.service.validation.exception.InvalidUserNameException;

public interface UserDetailsService {
    UserDetails selectByUserId(long userId) throws ServiceException;

    boolean insertUserDetails(UserDetails userDetails) throws ServiceException, InvalidUserNameException, InvalidPhoneException, InvalidAddressException;
}
