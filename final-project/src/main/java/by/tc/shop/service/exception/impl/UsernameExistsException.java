package by.tc.shop.service.exception.impl;

public class UsernameExistsException extends Exception {
    public UsernameExistsException() {
        super();
    }

    public UsernameExistsException(String message) {
        super(message);
    }

    public UsernameExistsException(Exception e) {
        super(e);
    }

    public UsernameExistsException(String message, Exception e) {
        super(message, e);
    }
}
