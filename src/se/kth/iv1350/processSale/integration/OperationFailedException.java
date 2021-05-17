package se.kth.iv1350.processSale.integration;

/**
 *
 */
public class OperationFailedException extends Exception {
    /**
     *
     * @param message
     */
    public OperationFailedException(String message, Exception cause) {
        super(message, cause);
    }
}
