package by.tc.shop.dao;

import by.tc.shop.bean.Category;
import by.tc.shop.dao.exception.DAOException;

import java.util.List;

public interface CategoryDAO {
    Category selectByName(String name) throws DAOException;

    Category selectByNameRu(String name) throws DAOException;

    Category selectById(long id) throws DAOException;

    List<Category> selectAllCategories() throws DAOException;
}
