package by.tc.shop.dao;

import by.tc.shop.bean.UserDetails;
import by.tc.shop.dao.exception.DAOException;

public interface UserDetailsDAO {
    UserDetails selectByUserID(long id) throws DAOException;

    boolean insertUserDetails(UserDetails userDetails) throws DAOException;
}
