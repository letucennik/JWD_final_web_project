package by.tc.shop.service.exception.impl;

public class EmailExistsException extends Exception {
    public EmailExistsException() {
        super();
    }

    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException(Exception e) {
        super(e);
    }

    public EmailExistsException(String message, Exception e) {
        super(message, e);
    }
}
