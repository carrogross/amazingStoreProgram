package se.kth.iv1350.processSale.util;

/**
 * Contains the <code>ConsoleLogger</code> responsible for writing logs to the console, implements Logger.
 */
public class ConsoleLogger implements Logger{
    ConsoleLogger consoleLogger;

    /**
     * Creates a new instance of <code>ConsoleLogger</code> with the specified <code>ConsoleLogger</code>.
     * @param consoleLogger The <code>ConsoleLogger</code> that is assigned.
     */
    public ConsoleLogger(ConsoleLogger consoleLogger){
        this.consoleLogger = consoleLogger;
    }

    /**
     * Prints the specified message to the console.
     * @param message The message that should be printed.
     */
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
