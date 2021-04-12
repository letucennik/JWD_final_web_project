package by.tc.shop.service.impl;

import by.tc.shop.bean.OrderItem;
import by.tc.shop.bean.Product;
import by.tc.shop.dao.DAOProvider;
import by.tc.shop.dao.OrderItemDAO;
import by.tc.shop.dao.ProductDAO;
import by.tc.shop.dao.exception.DAOException;
import by.tc.shop.service.OrderItemService;
import by.tc.shop.service.exception.ServiceException;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    @Override
    public Long insertNewOrderItem(OrderItem item) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderItemDAO orderItemDAO = provider.getOrderItemDAO();
        Long insertedId;
        try {
            insertedId = orderItemDAO.insertOrderItem(item);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return insertedId;
    }

    @Override
    public List<OrderItem> selectOrderItemsByOrder(long orderId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderItemDAO orderItemDAO = provider.getOrderItemDAO();
        ProductDAO productDAO = provider.getProductDAO();

        Product product;
        List<OrderItem> orderItemsByOrder;
        try {
            orderItemsByOrder = orderItemDAO.selectOrderItemsByOrder(orderId);
            for (OrderItem item : orderItemsByOrder) {
                product = productDAO.selectById(item.getProduct().getId());
                item.setProduct(product);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return orderItemsByOrder;
    }

    @Override
    public boolean isProductAlreadyInCart(long orderId, long productId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderItemDAO orderItemDAO = provider.getOrderItemDAO();
        boolean isInCart = false;
        try {
            isInCart = orderItemDAO.isProductAlreadyInOrder(orderId, productId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isInCart;
    }

    @Override
    public void deleteOrderItem(long id) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderItemDAO orderItemDAO = provider.getOrderItemDAO();
        try {
            orderItemDAO.deleteOrderItem(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void editNumberOfProducts(long id, int newNumberOfProducts) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderItemDAO orderItemDAO = provider.getOrderItemDAO();
        try {
            orderItemDAO.editProductAmount(id, newNumberOfProducts);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
