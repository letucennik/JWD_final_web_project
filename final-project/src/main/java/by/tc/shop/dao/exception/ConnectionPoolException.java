package by.tc.shop.dao.exception;

public class ConnectionPoolException extends Exception {
    private static final long serialVersionUID = -1L;

    public ConnectionPoolException(Exception e) {
        super(e);
    }

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
