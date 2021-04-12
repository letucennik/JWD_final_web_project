package by.tc.shop.service;

import by.tc.shop.bean.Product;
import by.tc.shop.service.exception.ServiceException;
import by.tc.shop.service.validation.exception.InvalidImagePathException;
import by.tc.shop.service.validation.exception.InvalidNameEnException;
import by.tc.shop.service.validation.exception.InvalidNameRuException;

import java.util.List;

public interface ProductService {
    List<Product> takeProductsByCategory(long categoryId) throws ServiceException;

    List<Product> selectAllProducts() throws ServiceException;

    Product selectById(long id) throws ServiceException;

    Long insertProduct(Product product) throws ServiceException, InvalidNameEnException, InvalidNameRuException, InvalidImagePathException;

    void deleteProduct(long productId) throws ServiceException;
}
