package by.tc.shop.dao;

import by.tc.shop.bean.Product;
import by.tc.shop.dao.exception.DAOException;

import java.util.List;

public interface ProductDAO {
    List<Product> selectProductsByCategory(long categoryId) throws DAOException;

    List<Product> selectAllProducts() throws DAOException;

    Product selectById(long id) throws DAOException;

    Long insertProduct(Product product) throws DAOException;

    void deleteProduct(long productId) throws DAOException;
}
