package se.kth.iv1350.processSale.startup;

import se.kth.iv1350.processSale.view.View;

/**
 * Main class to start the program execution.
 */
public class Main {

    /**
     * Main method to start the program execution.
     * @param args
     */
    public static void main(String[] args) {
        View view = new View();
        view.runExampleSession(1, 123456, 2, 234567,
                3, 345678, 920413, 100);

    }
}
