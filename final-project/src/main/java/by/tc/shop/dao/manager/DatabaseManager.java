package by.tc.shop.dao.manager;

import java.util.ResourceBundle;

public final class DatabaseManager {
    private final static DatabaseManager instance = new DatabaseManager();
    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    public static DatabaseManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
