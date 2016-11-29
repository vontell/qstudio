package core.expection;

/**
 * An exception that can be thrown which indicates that a
 * parameter into the function is invalid
 */
public class InvalidParameterException extends RuntimeException {

    /**
     * Creates the exception with the given message
     * @param message A detailed explanation of the error to the user
     */
    public InvalidParameterException(String message) {
        super(message);
    }
}
