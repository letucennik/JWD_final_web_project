package by.tc.shop.service.impl;

import by.tc.shop.bean.UserDetails;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.UserDetailsDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.service.UserDetailsService;
import by.tc.shop.service.exception.ServiceException;
import by.tc.shop.service.validation.UserDetailsValidator;
import by.tc.shop.service.validation.exception.InvalidAddressException;
import by.tc.shop.service.validation.exception.InvalidPhoneException;
import by.tc.shop.service.validation.exception.InvalidUserNameException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails selectByUserId(long userId) throws ServiceException {
        UserDetails userDetails;
        DAOProvider provider = DAOProvider.getInstance();
        UserDetailsDAO userDetailsDAO = provider.getUserDetailsDAO();
        try {
            userDetails = userDetailsDAO.selectByUserID(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDetails;
    }

    @Override
    public boolean insertUserDetails(UserDetails userDetails) throws ServiceException, InvalidUserNameException, InvalidPhoneException, InvalidAddressException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDetailsDAO userDetailsDAO = provider.getUserDetailsDAO();
        if (!UserDetailsValidator.isNameValid(userDetails.getFirstName()) || !UserDetailsValidator.isNameValid(userDetails.getLastName())) {
            throw new InvalidUserNameException();
        }
        if (!UserDetailsValidator.isPhoneValid(userDetails.getPhone())) {
            throw new InvalidPhoneException();
        }
        if (!UserDetailsValidator.isAddressValid(userDetails.getAddress())) {
            throw new InvalidAddressException();
        }
        boolean inserted;
        try {
            inserted = userDetailsDAO.insertUserDetails(userDetails);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return inserted;
    }
}
