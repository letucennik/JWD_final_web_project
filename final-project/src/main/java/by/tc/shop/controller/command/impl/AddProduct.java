package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.Category;
import by.tc.shop.bean.Product;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.CategoryService;
import by.tc.shop.service.ProductAtStoreService;
import by.tc.shop.service.ProductService;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.exception.ServiceException;
import by.tc.shop.service.validation.exception.InvalidImagePathException;
import by.tc.shop.service.validation.exception.InvalidNameEnException;
import by.tc.shop.service.validation.exception.InvalidNameRuException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

@MultipartConfig
public class AddProduct implements Command {
    private static final String CATEGORY = "category";
    private static final String NAME_EN = "name";
    private static final String NAME_RU = "nameRu";
    private static final String IMAGE = "image";
    private static final String URL = "url";
    private static final String PRICE = "price";
    private static final String AMOUNT = "amount";
    private static final String IMAGE_ROOT = "/img/";
    private static final String DIRECTORY = "url.directory";
    private static final String GO_TO_MAIN_PAGE = "Controller?command=gotomainpage&insertedProduct=true";
    private static final String GO_TO_ADD_PRODUCT_PAGE_ERROR = "Controller?command=gotoaddproductpage&error=true";
    private static final String GO_TO_ADD_PRODUCT_PAGE_INVALID_NAME_RU = "Controller?command=gotoaddproductpage&message=invalidNameRu";
    private static final String GO_TO_ADD_PRODUCT_PAGE_INVALID_NAME_EN = "Controller?command=gotoaddproductpage&message=invalidNameEn";
    private static final String GO_TO_ADD_PRODUCT_PAGE_INVALID_IMAGE = "Controller?command=gotoaddproductpage&message=invalidImage";
    private static final Logger logger = Logger.getLogger(AddProduct.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter(CATEGORY);
        String nameEn = request.getParameter(NAME_EN);
        String nameRu = request.getParameter(NAME_RU);
        String price = request.getParameter(PRICE);
        String amount = request.getParameter(AMOUNT);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(URL);
        String directory = resourceBundle.getString(DIRECTORY);
        Part part = request.getPart(IMAGE);
        String fileName = part.getSubmittedFileName();
        String path = directory + File.separator + fileName;
        String serverPath = request.getServletContext().getRealPath(IMAGE_ROOT + File.separator + fileName);
        InputStream inputStream = part.getInputStream();
        InputStream inputStreamCopy = part.getInputStream();
        boolean success = uploadFile(inputStream, path, response) && uploadFile(inputStreamCopy, serverPath, response);
        if (success) {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            CategoryService categoryService = serviceProvider.getCategoryService();
            ProductService productService = serviceProvider.getProductService();
            ProductAtStoreService productAtStoreService = serviceProvider.getProductAtStoreService();
            Category category;
            Product product;
            try {
                category = categoryService.selectById(Long.parseLong(categoryId));
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                throw new CommandException(e);
            }
            product = Product.getBuilderInstance().setCategory(category).
                    setNameRu(nameRu).setNameEn(nameEn).setPrice(Double.parseDouble(price)).
                    setPicturePath(IMAGE_ROOT + fileName).build();
            Long productId = null;
            try {
                productId = productService.insertProduct(product);
            } catch (InvalidNameEnException e) {
                response.sendRedirect(GO_TO_ADD_PRODUCT_PAGE_INVALID_NAME_EN);
            } catch (InvalidNameRuException e) {
                response.sendRedirect(GO_TO_ADD_PRODUCT_PAGE_INVALID_NAME_RU);
            } catch (InvalidImagePathException e) {
                response.sendRedirect(GO_TO_ADD_PRODUCT_PAGE_INVALID_IMAGE);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                throw new CommandException(e);
            }
            try {
                productAtStoreService.insertProductAmount(productId, Integer.parseInt(amount));
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                throw new CommandException(e);
            }
            response.sendRedirect(GO_TO_MAIN_PAGE);
        } else {
            throw new CommandException();
        }

    }

    private boolean uploadFile(InputStream inputStream, String path, HttpServletResponse response) throws IOException {
        boolean test = false;
        FileOutputStream fileOutputStream = null;
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            test = true;
        } catch (IOException e) {
            response.sendRedirect(GO_TO_ADD_PRODUCT_PAGE_ERROR);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                response.sendRedirect(GO_TO_ADD_PRODUCT_PAGE_ERROR);
            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                response.sendRedirect(GO_TO_ADD_PRODUCT_PAGE_ERROR);
            }

        }

        return test;
    }
}
