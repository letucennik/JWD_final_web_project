package by.tc.shop.service.validation;

public class SignUpValidator {
    public static final String LOGIN_PATTERN = "^[a-zA-Z_0-9]{3,45}$";
    public static final String EMAIL_PATTERN = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 60;

    public static boolean isLoginValid(String login) {
        return login.matches(LOGIN_PATTERN);
    }

    public static boolean isPasswordValid(String password) {
        boolean isValid = false;
        if (password.length() >= MIN_PASSWORD_LENGTH && password.length() <= MAX_PASSWORD_LENGTH && hasDigit(password) &&
                hasLowercaseChar(password) && hasUppercaseChar(password)) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isEmailValid(String email) {
        return email.matches(EMAIL_PATTERN);
    }

    private static boolean hasDigit(String s) {
        char[] chars = s.toCharArray();
        boolean isDigit = false;
        for (char c : chars) {
            if (Character.isDigit(c)) {
                isDigit = true;
            }
        }
        return isDigit;
    }

    private static boolean hasUppercaseChar(String s) {
        return !s.equals(s.toLowerCase());
    }

    private static boolean hasLowercaseChar(String s) {
        return !s.equals(s.toUpperCase());
    }
}
