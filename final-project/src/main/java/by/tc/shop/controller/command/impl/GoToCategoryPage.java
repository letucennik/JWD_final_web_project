package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.Category;
import by.tc.shop.bean.Product;
import by.tc.shop.bean.User;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.CategoryService;
import by.tc.shop.service.ProductAtStoreService;
import by.tc.shop.service.ProductService;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class GoToCategoryPage implements Command {
    public static final String USER_PARAMETER = "user";
    public static final String NAME_EN = "nameEn";
    public static final String PRODUCTS_BY_CATEGORY = "productsByCategory";
    public static final String CATEGORY = "category";
    private static final Logger logger = Logger.getLogger(GoToCategoryPage.class);
    public static final String CATEGORY_PAGE = "/WEB-INF/jsp/category.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(USER_PARAMETER);
        String nameEn = request.getParameter(NAME_EN);
        List<Product> productsByCategory;
        Category category;
        ServiceProvider serviceProvider = ServiceProvider.getInstance();

        ProductService productService = serviceProvider.getProductService();
        ProductAtStoreService productAtStoreService = serviceProvider.getProductAtStoreService();
        CategoryService categoryService = serviceProvider.getCategoryService();
        try {
            category = categoryService.selectByName(nameEn);
            productsByCategory = productService.takeProductsByCategory(category.getId());
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        request.setAttribute(CATEGORY, category);
        productsByCategory.forEach(x -> x.setCategory(category));
        Iterator<Product> iterator = productsByCategory.iterator();
        while (iterator.hasNext()) {
            Product currentProduct = iterator.next();
            Integer amount;
            try {
                amount = productAtStoreService.getProductAmount(currentProduct.getId());
                if (user.getRole() == User.ROLE_ADMIN) {
                    currentProduct.setNumberOfProducts(amount);
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                throw new CommandException(e);
            }
            if (user.getRole() == User.ROLE_CLIENT) {
                if (amount < 1) {
                    iterator.remove();
                }
            }
        }
        request.setAttribute(PRODUCTS_BY_CATEGORY, productsByCategory);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CATEGORY_PAGE);
        requestDispatcher.forward(request, response);
    }
}
