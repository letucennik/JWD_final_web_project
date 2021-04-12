package by.tc.shop.dao.impl;

import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.executor.QueryExecutor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<B> {
    public abstract B parseResultSet(ResultSet resultSet) throws DAOException;

    public B executeSelectByID(String query, long id) throws DAOException {
        return QueryExecutor.executeSimpleQuery(query, statement -> {
            statement.setLong(1, id);
            return executeStatementAndParseResultSet(statement);
        });
    }

    public B executeStatementAndParseResultSet(PreparedStatement preparedStatement) throws DAOException {
        B bean;
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bean = parseResultSet(resultSet);
            } else {
                bean = null;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return bean;
    }
    List<B> executeStatementAndParseResultSetToList(PreparedStatement statement) throws SQLException, DAOException {
        ArrayList<B> questions = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                questions.add(parseResultSet(resultSet));
            }
        }
        questions.trimToSize();
        return questions;
    }
}
