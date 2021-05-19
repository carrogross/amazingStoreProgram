package se.kth.iv1350.processSale.integration;

/**
 * Contains the exception thrown when an unchecked exception is caught.
 */
public class OperationFailedException extends Exception {
    /**
     * Creates a new instance of the <code>OperationFailedException</code> with the specified message and information about the
     * original exception thrown.
     * @param message The message included in the exception.
     * @param cause The exception causing this exception to be thrown.
     */
    public OperationFailedException(String message, Exception cause) {
        super(message, cause);
    }
}
