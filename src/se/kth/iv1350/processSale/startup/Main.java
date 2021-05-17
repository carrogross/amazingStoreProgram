package se.kth.iv1350.processSale.startup;

import se.kth.iv1350.processSale.integration.InvalidIdentifierException;
import se.kth.iv1350.processSale.view.View;

import java.io.IOException;

/**
 * Main class to start the program execution.
 */
public class Main {

    /**
     * Main method to start the program execution.
     * @param args Array of command line arguments .
     */
    public static void main(String[] args) throws IOException {
        View view = new View();
        view.runExampleSession();

    }
}
