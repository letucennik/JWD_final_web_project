package by.tc.shop.service.validation;

public final class UserDetailsValidator {
    public static final String NAME_PATTERN = "^[а-яА-я]{2,30}$";
    public static final String PHONE_PATTERN = "^(375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";

    public static boolean isNameValid(String name) {
        return name.matches(NAME_PATTERN);
    }

    public static boolean isPhoneValid(String phone) {
        return phone.matches(PHONE_PATTERN);
    }

    public static boolean isAddressValid(String address) {
        return !address.equals("");
    }
}
