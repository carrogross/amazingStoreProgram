package se.kth.iv1350.processSale.integration;

/**
 * Contains the exception thrown when the database can not be reached. Extends Exception because
 * it describes an unchecked exception.
 */
public class FailureDBReachException extends Exception {
    /**
     *Creates a new instance of the <code>FailureDBReachException</code> with specified message.
     * @param message The message included in the exception.
     */
    public FailureDBReachException(String message) {
        super(message);
    }
}
