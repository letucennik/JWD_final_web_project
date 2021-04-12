package by.tc.shop.service.validation;

public class AddProductValidator {
    public static final String NAME_EN_PATTERN = "^[a-zA-Z]{3,45}$";
    public static final String NAME_RU_PATTERN = "^[а-яА-Я]{3,45}$";

    public static boolean isNameEnValid(String name) {
        return name.matches(NAME_EN_PATTERN);
    }

    public static boolean isNameRuValid(String name) {
        return name.matches(NAME_RU_PATTERN);
    }

    public static boolean isImagePathValid(String imagePath) {
        return imagePath != null && !imagePath.equals("");
    }
}
