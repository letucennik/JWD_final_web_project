package by.tc.shop.service.impl;

import by.tc.shop.bean.Product;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.ProductDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.service.ProductService;
import by.tc.shop.service.exception.ServiceException;
import by.tc.shop.service.validation.AddProductValidator;
import by.tc.shop.service.validation.exception.InvalidImagePathException;
import by.tc.shop.service.validation.exception.InvalidNameEnException;
import by.tc.shop.service.validation.exception.InvalidNameRuException;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> takeProductsByCategory(long categoryId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ProductDAO productDAO = provider.getProductDAO();
        List<Product> productsByCategory;
        try {
            productsByCategory = productDAO.selectProductsByCategory(categoryId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return productsByCategory;
    }

    @Override
    public List<Product> selectAllProducts() throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ProductDAO productDAO = provider.getProductDAO();
        List<Product> products;
        try {
            products = productDAO.selectAllProducts();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return products;
    }

    @Override
    public Product selectById(long id) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ProductDAO productDAO = provider.getProductDAO();
        Product product;
        try {
            product = productDAO.selectById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return product;
    }

    @Override
    public Long insertProduct(Product product) throws ServiceException, InvalidNameEnException, InvalidNameRuException, InvalidImagePathException {
        DAOProvider provider = DAOProvider.getInstance();
        ProductDAO productDAO = provider.getProductDAO();
        if (!AddProductValidator.isNameEnValid(product.getNameEn())) {
            throw new InvalidNameEnException();
        }
        if (!AddProductValidator.isNameRuValid(product.getNameRu())) {
            throw new InvalidNameRuException();
        }
        if (!AddProductValidator.isImagePathValid(product.getPicturePath())) {
            throw new InvalidImagePathException();
        }
        Long inserted;
        try {
            inserted = productDAO.insertProduct(product);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return inserted;
    }

    @Override
    public void deleteProduct(long productId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ProductDAO productDAO = provider.getProductDAO();
        try {
            productDAO.deleteProduct(productId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}
