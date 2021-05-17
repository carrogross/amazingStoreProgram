package se.kth.iv1350.processSale.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 */
public class FileLogger implements Logger{
    String fileLocation = "log.txt";
    File file = new File(fileLocation);

    PrintWriter printWriter;

    /**
     *
     * @throws IOException
     */
    public FileLogger() throws IOException {
        printWriter = new PrintWriter(file);
    }

    /**
     *
     * @param message
     */
    @Override
    public void log(String message) {
        printWriter.println("Error occured: " + currentTime() + ": " + message);
        printWriter.close();
    }

    private LocalDateTime currentTime(){
        return LocalDateTime.now();
    }
}
