package by.tc.shop.dao.impl;

import by.tc.shop.bean.Category;
import by.tc.shop.dao.CategoryDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.executor.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLCategoryDAO extends BaseDAO<Category> implements CategoryDAO {
    private static final String SELECT_BY_NAME = "SELECT * FROM categories WHERE c_name = ?";
    private static final String SELECT_BY_NAME_RU = "SELECT * FROM categories WHERE c_name_ru = ?";
    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM categories";
    private static final String SELECT_BY_ID = "SELECT * FROM categories WHERE c_id = ?";

    @Override
    public Category selectByName(String name) throws DAOException {
        return selectByStringParameter(SELECT_BY_NAME, name);
    }

    @Override
    public Category selectByNameRu(String name) throws DAOException {
        return selectByStringParameter(SELECT_BY_NAME_RU, name);
    }

    @Override
    public Category selectById(long id) throws DAOException {
        return (Category) executeSelectByID(SELECT_BY_ID, id);
    }

    @Override
    public List<Category> selectAllCategories() throws DAOException {
        return QueryExecutor.executeSimpleQuery(SELECT_ALL_CATEGORIES, this::executeStatementAndParseResultSetToList);
    }

    private Category selectByStringParameter(String query, String parameter) throws DAOException {
        return (Category) QueryExecutor.executeSimpleQuery(query, statement -> {
            statement.setString(1, parameter);
            return executeStatementAndParseResultSet(statement);
        });
    }

    @Override
    public Category parseResultSet(ResultSet resultSet) throws DAOException {
        Category category;
        try {
            long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            String nameRu = resultSet.getString(3);
            category = new Category(id, name, nameRu);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return category;
    }
}
