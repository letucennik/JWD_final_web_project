package by.tc.shop.service.impl;

import by.tc.shop.bean.Category;
import by.tc.shop.dao.CategoryDAO;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.service.CategoryService;
import by.tc.shop.service.exception.ServiceException;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public Category selectByName(String name) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        CategoryDAO categoryDAO = provider.getCategoryDAO();
        Category category;
        try {
            category = categoryDAO.selectByName(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return category;
    }

    @Override
    public Category selectByNameRu(String name) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        CategoryDAO categoryDAO = provider.getCategoryDAO();
        Category category;
        try {
            category = categoryDAO.selectByNameRu(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return category;
    }

    @Override
    public Category selectById(long id) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        CategoryDAO categoryDAO = provider.getCategoryDAO();
        Category category;
        try {
            category = categoryDAO.selectById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return category;
    }

    @Override
    public List<Category> selectAllCategories() throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        CategoryDAO categoryDAO = provider.getCategoryDAO();
        List<Category> allCategories;
        try {
            allCategories = categoryDAO.selectAllCategories();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return allCategories;
    }
}
