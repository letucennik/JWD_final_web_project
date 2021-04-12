package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.Category;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.CategoryService;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToAddProductPage implements Command {
    private static final Logger logger = Logger.getLogger(GoToAddProductPage.class);
    private static final String ALL_CATEGORIES = "allCategories";
    private static final String GO_TO_ADD_PRODUCT_PAGE = "/WEB-INF/jsp/add_product.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        CategoryService categoryService = serviceProvider.getCategoryService();
        List<Category> allCategories;
        try {
            allCategories = categoryService.selectAllCategories();
            request.setAttribute(ALL_CATEGORIES, allCategories);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_ADD_PRODUCT_PAGE);
        requestDispatcher.forward(request, response);
    }
}
