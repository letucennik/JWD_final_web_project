package by.tc.shop.controller.command.impl;

import by.tc.shop.bean.User;
import by.tc.shop.bean.UserDetails;
import by.tc.shop.controller.command.Command;
import by.tc.shop.controller.command.exception.CommandException;
import by.tc.shop.service.ServiceProvider;
import by.tc.shop.service.UserDetailsService;
import by.tc.shop.service.exception.ServiceException;
import by.tc.shop.service.validation.exception.InvalidAddressException;
import by.tc.shop.service.validation.exception.InvalidPhoneException;
import by.tc.shop.service.validation.exception.InvalidUserNameException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveUserDetails implements Command {
    private static final String USER_PARAMETER = "user";
    private static final String FIRST_NAME = "firstName";
    private static final String SECOND_NAME = "secondName";
    private static final String CITY = "city";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String GO_TO_CART_PAGE = "Controller?command=gotoactiveorderspage&message=insertedUserDetails";
    private static final String GO_TO_USER_DETAILS_PAGE_INVALID_NAME = "Controller?command=gotouserdetailspage&message=Invalid name";
    private static final String GO_TO_USER_DETAILS_PAGE_INVALID_ADDRESS = "Controller?command=gotouserdetailspage&message=Invalid address";
    private static final String GO_TO_USER_DETAILS_PAGE_INVALID_PHONE = "Controller?command=gotouserdetailspage&message=Invalid phone";
    private static final Logger logger = Logger.getLogger(SaveUserDetails.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDetails userDetails = null;

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(USER_PARAMETER);
        String firstName = request.getParameter(FIRST_NAME);
        String secondName = request.getParameter(SECOND_NAME);
        String city = request.getParameter(CITY);
        String address = request.getParameter(ADDRESS);
        String phone = request.getParameter(PHONE);

        userDetails = UserDetails.getBuilderInstance().setUserId(user.getId()).setFirstName(firstName).
                setLastName(secondName).
                setCity(city).
                setPhone(phone).
                setAddress(address).build();
        ServiceProvider provider = ServiceProvider.getInstance();
        UserDetailsService userDetailsService = provider.getUserDetailsService();
        boolean inserted;
        try {
            inserted = userDetailsService.insertUserDetails(userDetails);
            if (!inserted) {
                throw new CommandException();
            }
            user.setUserDetails(userDetails);
            session.setAttribute(USER_PARAMETER, user);
            response.sendRedirect(GO_TO_CART_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        } catch (InvalidUserNameException e) {
            response.sendRedirect(GO_TO_USER_DETAILS_PAGE_INVALID_NAME);
        } catch (InvalidAddressException e) {
            response.sendRedirect(GO_TO_USER_DETAILS_PAGE_INVALID_ADDRESS);
        } catch (InvalidPhoneException e) {
            response.sendRedirect(GO_TO_USER_DETAILS_PAGE_INVALID_PHONE);
        }


    }
}
