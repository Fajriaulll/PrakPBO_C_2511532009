package shop.exception;

/** Base class for every business error in the shop (checked exception). */
public class ShopException extends Exception {
    private static final long serialVersionUID = 1L;

    public ShopException(String message) {
        super(message);
    }

    public ShopException(String message, Throwable cause) {
        super(message, cause);
    }
}
