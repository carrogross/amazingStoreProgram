package se.kth.iv1350.processSale.util;

/**
 *
 */
public class ConsoleLogger implements Logger{
    ConsoleLogger consoleLogger;

    public ConsoleLogger(ConsoleLogger consoleLogger){
        this.consoleLogger = consoleLogger;
    }

    /**
     *
     * @param message
     */
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
