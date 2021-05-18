package se.kth.iv1350.processSale.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Contains the <code>FileLogger</code> responsible for logging to a file.
 */
public class FileLogger implements Logger{
    String fileLocation = "src/se/kth/iv1350/processSale/util/ExceptionsLog.txt";
    PrintWriter printWriter;

    /**
     * Creates a new instance of <code>FileLogger</code> and assigns a new <code>PrintWriter</code> with hard coded
     * file Location to <code>printWriter</code>.
     * @throws IOException is thrown
     */
    public FileLogger() throws IOException {
        printWriter = new PrintWriter(fileLocation);
    }

    /**
     * Creates a new instance of <code>FileLogger</code> and assigns a new <code>PrintWriter</code> with specified
     * file Location to <code>printWriter</code>.
     * @param fileLocation The location where the file should be created.
     * @throws FileNotFoundException is thrown if the file is not found.
     */
    public FileLogger(String fileLocation) throws FileNotFoundException {
        printWriter = new PrintWriter(fileLocation);
    }

    /**
     * Logs the current time and specified message to file and closes the stream.
     * @param message The message that should be logged in the file.
     */
    @Override
    public void log(String message) {
        printWriter.println(currentTime() + ": " + message);
        printWriter.close();
    }

    private LocalDateTime currentTime(){
        return LocalDateTime.now();
    }
}
