package by.tc.shop.service;

import by.tc.shop.bean.Category;
import by.tc.shop.service.exception.ServiceException;

import java.util.List;

public interface CategoryService {
    Category selectByName(String name) throws ServiceException;

    Category selectByNameRu(String name) throws ServiceException;

    Category selectById(long id) throws ServiceException;

    List<Category> selectAllCategories() throws ServiceException;
}
