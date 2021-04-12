package by.tc.shop.dao.impl;

import by.tc.shop.bean.UserDetails;
import by.tc.shop.dao.UserDetailsDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.executor.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDetailsDAO extends BaseDAO<UserDetails> implements UserDetailsDAO {
    private static final String SELECT_BY_USER_ID = "SELECT * FROM user_details WHERE ud_users_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM user_details WHERE ud_id = ?";
    private static final String INSERT_INTO_USER_DETAILS = "INSERT INTO user_details (ud_users_id,ud_first_name,ud_last_name,ud_city,ud_phone,ud_adress) VALUES (?,?,?,?,?,?)";

    public UserDetails selectById(long id) throws DAOException {
        return selectByLongParameter(SELECT_BY_ID, id);
    }

    @Override
    public UserDetails selectByUserID(long id) throws DAOException {
        return selectByLongParameter(SELECT_BY_USER_ID, id);
    }

    @Override
    public boolean insertUserDetails(UserDetails userDetails) throws DAOException {
        return (Boolean) QueryExecutor.executeSimpleQuery(INSERT_INTO_USER_DETAILS, ((connection, statement) -> {
            statement.setLong(1, userDetails.getUserID());
            statement.setString(2, userDetails.getFirstName());
            statement.setString(3, userDetails.getLastName());
            statement.setString(4, userDetails.getCity());
            statement.setString(5, userDetails.getPhone());
            statement.setString(6, userDetails.getAddress());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            return resultSet.next();
        }));
    }

    private UserDetails selectByLongParameter(String query, long parameter) throws DAOException {
        return (UserDetails) QueryExecutor.executeSimpleQuery(query, statement -> {
            statement.setLong(1, parameter);
            return executeStatementAndParseResultSet(statement);
        });
    }


    public UserDetails parseResultSet(ResultSet resultSet) throws DAOException {
        UserDetails userDetails = null;
        try {
            long id = resultSet.getLong(1);
            String firstName = resultSet.getString(3);
            String lastName = resultSet.getString(4);
            String phone = resultSet.getString(6);
            String city = resultSet.getString(5);
            String address = resultSet.getString(7);
            userDetails = UserDetails.getBuilderInstance().setID(id).setFirstName(firstName).setLastName(lastName).
                    setPhone(phone).setCity(city).setAddress(address).build();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return userDetails;
    }


    public Long insertIntoDB(UserDetails bean) throws DAOException {
        return QueryExecutor.executeSimpleQuery(INSERT_INTO_USER_DETAILS, ((connection, statement) -> {
            statement.setLong(1, bean.getUserID());
            statement.setString(2, bean.getFirstName());
            statement.setString(3, bean.getLastName());
            statement.setString(4, bean.getCity());
            statement.setString(5, bean.getPhone());
            statement.setString(6, bean.getAddress());
            statement.executeUpdate();
            return statement.getGeneratedKeys().getLong(1);
        }));
    }
}
