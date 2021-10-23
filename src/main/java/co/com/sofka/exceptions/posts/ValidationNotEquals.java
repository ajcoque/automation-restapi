package co.com.sofka.exceptions.posts;

public class ValidationNotEquals extends AssertionError {

    public static final String VALIDATION_IS_NOT_EQUALS = "The received values do not match the expected. %s";

    public ValidationNotEquals(String message, Throwable cause) {
        super(message, cause);
    }

}
