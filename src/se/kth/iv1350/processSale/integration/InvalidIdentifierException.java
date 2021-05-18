package se.kth.iv1350.processSale.integration;

/**
 * Contains the exception thrown when an identifier that is not in the inventory system is entered. Extends Exception because
 * it describes a checked exception.
 */
public class InvalidIdentifierException extends Exception {
    /**
     * Creates a new instance of the <code>InvalidIdentifierException</code> with the specified message.
     * @param message The message included in the exception.
     */
    public InvalidIdentifierException(String message) {
        super(message);
    }
}
