package by.tc.shop.service;

import by.tc.shop.service.impl.*;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {
    }

    private final UserService userService = new UserServiceImpl();

    private final ProductService productService = new ProductServiceImpl();

    private final CategoryService categoryService = new CategoryServiceImpl();

    private final OrderService orderService = new OrderServiceImpl();

    private final OrderItemService orderItemService = new OrderItemServiceImpl();

    private final UserDetailsService userDetailsService = new UserDetailsServiceImpl();

    private final ProductAtStoreService productAtStoreService = new ProductAtStoreServiceImpl();

    private final DeliveryService deliveryService = new DeliveryServiceImpl();

    private final DeliveryItemService deliveryItemService = new DeliveryItemServiceImpl();

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public OrderItemService getOrderItemService() {
        return orderItemService;
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public ProductAtStoreService getProductAtStoreService() {
        return productAtStoreService;
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public DeliveryItemService getDeliveryItemService() {
        return deliveryItemService;
    }
}
