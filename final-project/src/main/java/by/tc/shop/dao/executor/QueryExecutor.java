package by.tc.shop.dao.executor;

import by.tc.shop.dao.exception.ConnectionPoolException;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * wrapper methods for getting connections from pool
 * removes duplicate code
 */
public class QueryExecutor {

    public static <T> T executeSimpleQuery(String query, DAOFunction<T> function) throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            return function.apply(connection, preparedStatement);
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    public static void executeSimpleQuery(String query, DAOVoidFunction function) throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            function.apply(connection, preparedStatement);
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    public static <R> R executeSimpleQuery(String query, DAOFunctionWithoutConnection<R> function) throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            return function.apply(preparedStatement);
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Cannot get connection", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    public static void executeSimpleQuery(String query, DAOVoidFunctionWithoutConnection function) throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            function.apply(preparedStatement);
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Cannot get connection", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
        }

    }


    @FunctionalInterface
    public interface DAOFunction<T> {
        T apply(Connection connection, PreparedStatement statement) throws SQLException, ConnectionPoolException, DAOException;
    }

    @FunctionalInterface
    public interface DAOVoidFunction<T> {
        void apply(Connection connection, PreparedStatement statement) throws SQLException, ConnectionPoolException, DAOException;
    }

    @FunctionalInterface
    public interface DAOFunctionWithoutConnection<T> {
        T apply(PreparedStatement statement) throws SQLException, ConnectionPoolException, DAOException;
    }

    @FunctionalInterface
    public interface DAOVoidFunctionWithoutConnection {
        void apply(PreparedStatement statement) throws SQLException, ConnectionPoolException, DAOException;
    }

}
