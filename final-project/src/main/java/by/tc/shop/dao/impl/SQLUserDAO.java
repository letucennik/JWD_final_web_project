package by.tc.shop.dao.impl;

import by.tc.shop.bean.User;
import by.tc.shop.dao.UserDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.executor.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLUserDAO extends BaseDAO<User> implements UserDAO {

    private static final String INSERT_INTO_USERS = "INSERT INTO users (u_login,u_password,u_email,u_role,u_status) VALUES (?,?,?,?,?)";
    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE u_login = ? AND u_password = ?";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM users WHERE u_email = ?";
    private static final String SELECT_BY_USER_NAME = "SELECT * FROM users WHERE u_login = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE u_id = ?";
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM users WHERE u_role = ? AND u_status = ?";
    private static final String BAN_USER = "UPDATE users set u_status = ? WHERE u_id = ?";

    public User selectById(long id) throws DAOException {
        return (User) executeSelectByID(SELECT_BY_ID, id);
    }


    @Override
    public User selectByUserName(String userName) throws DAOException {
        return selectByStringParameter(SELECT_BY_USER_NAME, userName);
    }

    @Override
    public User selectByEmail(String email) throws DAOException {
        return selectByStringParameter(SELECT_BY_EMAIL, email);
    }

    private User selectByStringParameter(String query, String parameter) throws DAOException {
        return (User) QueryExecutor.executeSimpleQuery(query, statement -> {
            statement.setString(1, parameter);
            return executeStatementAndParseResultSet(statement);
        });
    }

    public User parseResultSet(ResultSet resultSet) throws DAOException {
        User user = null;
        try {
            long id = resultSet.getLong(1);
            String userName = resultSet.getString(2);
            String password = resultSet.getString(3);
            String email = resultSet.getString(4);
            int role = resultSet.getInt(5);
            int status = resultSet.getInt(6);
            user = User.getBuilderInstance().setId(id).setEmail(email).setUsername(userName).setRole(role).setPassword(password).
                    setStatus(status).build();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    public Long registration(User user) throws DAOException {
        Long insertedUserID = insertIntoDB(user);

        return insertedUserID;
    }

    @Override
    public List<User> selectAllClients() throws DAOException {
        return QueryExecutor.executeSimpleQuery(SELECT_ALL_CLIENTS, statement -> {
            statement.setInt(1, User.ROLE_CLIENT);
            statement.setInt(2, User.STATUS_ACTIVE);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public List<User> selectBannedClients() throws DAOException {
        return QueryExecutor.executeSimpleQuery(SELECT_ALL_CLIENTS, statement -> {
            statement.setInt(1, User.ROLE_CLIENT);
            statement.setInt(2, User.STATUS_BANNED);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public void banUser(long userId) throws DAOException {
        QueryExecutor.executeSimpleQuery(BAN_USER, statement -> {
            statement.setInt(1, User.STATUS_BANNED);
            statement.setLong(2, userId);
            statement.executeUpdate();
        });
    }

    @Override
    public void unbanUser(long userId) throws DAOException {
        QueryExecutor.executeSimpleQuery(BAN_USER, statement -> {
            statement.setInt(1, User.STATUS_ACTIVE);
            statement.setLong(2, userId);
            statement.executeUpdate();
        });
    }

    public Long insertIntoDB(User bean) throws DAOException {
        return (Long) QueryExecutor.executeSimpleQuery(INSERT_INTO_USERS, (connection, statement) -> {
            statement.setString(1, bean.getUsername());
            statement.setString(2, bean.getPassword());
            statement.setString(3, bean.getEmail());
            statement.setInt(4, User.ROLE_CLIENT);
            statement.setInt(5, User.STATUS_ACTIVE);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return null;

        });
    }

    @Override
    public User authorization(String login, String password) throws DAOException {
        return selectByUserNameAndPassword(login, password);
    }

    private User selectByUserNameAndPassword(String userName, String password) throws DAOException {
        return (User) QueryExecutor.executeSimpleQuery(SELECT_USER_BY_LOGIN_AND_PASSWORD, statement -> {
            statement.setString(1, userName);
            statement.setString(2, password);
            return executeStatementAndParseResultSet(statement);
        });
    }

}
