package by.tc.shop.dao.impl;

import by.tc.shop.bean.Product;
import by.tc.shop.dao.ProductDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.dao.executor.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLProductDAO extends BaseDAO<Product> implements ProductDAO {
    private static final String SELECT_BY_CATEGORY_ID = "SELECT * FROM products WHERE p_category = ?";
    private static final String SELECT_ALL = "SELECT * FROM products";
    private static final String SELECT_BY_ID = "SELECT * FROM products WHERE p_id = ?";
    private static final String INSERT_PRODUCT = "INSERT INTO products (p_name_en,p_name_ru,p_picture_path,p_price,p_category) VALUES(?,?,?,?,?)";
    public static final String DELETE_PRODUCT = "DELETE FROM products WHERE p_id = ?";

    @Override
    public List<Product> selectProductsByCategory(long categoryId) throws DAOException {
        return selectByLongParameter(SELECT_BY_CATEGORY_ID, categoryId);
    }

    @Override
    public List<Product> selectAllProducts() throws DAOException {
        return QueryExecutor.executeSimpleQuery(SELECT_ALL, this::executeStatementAndParseResultSetToList);
    }

    @Override
    public Product selectById(long id) throws DAOException {
        return (Product) executeSelectByID(SELECT_BY_ID, id);
    }

    @Override
    public Long insertProduct(Product product) throws DAOException {
        return (Long) QueryExecutor.executeSimpleQuery(INSERT_PRODUCT, (connection, statement) -> {
            statement.setString(1, product.getNameEn());
            statement.setString(2, product.getNameRu());
            statement.setString(3, product.getPicturePath());
            statement.setDouble(4, product.getPrice());
            statement.setLong(5, product.getCategory().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return null;
        });
    }

    @Override
    public void deleteProduct(long productId) throws DAOException {
        QueryExecutor.executeSimpleQuery(DELETE_PRODUCT, statement -> {
            statement.setLong(1, productId);
            statement.executeUpdate();
        });
    }


    private List<Product> selectByLongParameter(String query, long parameter) throws DAOException {
        return QueryExecutor.executeSimpleQuery(query, statement -> {
            statement.setLong(1, parameter);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public Product parseResultSet(ResultSet resultSet) throws DAOException {
        Product product;
        try {
            long id = resultSet.getLong(1);
            String nameEn = resultSet.getString(2);
            String nameRu = resultSet.getString(3);
            String picturePath = resultSet.getString(4);
            String descriptionEn = resultSet.getString(5);
            String descriptionRu = resultSet.getString(6);
            double price = resultSet.getDouble(7);
            long categoryId = resultSet.getLong(8);
            product = Product.getBuilderInstance().setId(id).
                    setNameEn(nameEn).setNameRu(nameRu).
                    setPicturePath(picturePath).setDescriptionEn(descriptionEn).
                    setDescriptionRu(descriptionRu).setPrice(price).build();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return product;
    }
}
