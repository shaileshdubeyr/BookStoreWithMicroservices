package cart.carts.exception;

/**
 * @author shailesh dubey
 * @Version 16.0.2
 * @Since 24-03-2022
 */
public class CartException extends  RuntimeException {
    public enum ExceptionType{
        DATA_ALREADY_EXISTS,
        QUANTITY_IS_GRATER,
        ENDPOINT_INVALID_OR_NULL,
        NOT_VALID_CART_DATA,
        NO_DATA_REGISTERED
    }

    public ExceptionType type;
    public CartException(String message, ExceptionType type){
        super(message);
        this.type = type;
    }
}
