package se.kth.iv1350.processSale.util;

import java.io.IOException;

/**
 * Interface that enables implementing classes to use more than one type of logger without changing a lot of code.
 */
public interface Logger {
    void log(String message) throws IOException;
}
