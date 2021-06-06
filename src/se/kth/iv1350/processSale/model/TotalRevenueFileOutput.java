package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.util.FileLogger;
import se.kth.iv1350.processSale.view.TotalRevenueDisplay;

import java.io.IOException;

/**
 * Contains the class responsible for writing the total revenue to a file.
 */
public class TotalRevenueFileOutput extends TotalRevenueDisplay {
    FileLogger fileLogger;
    String fileLocation = "src/se/kth/iv1350/processSale/model/Revenue.txt";

    /**
     * Creates a new instance of <code>TotalRevenueFileOutput</code> and creates a new <code>FileLogger</code> at
     * <code>fileLocation</code> and assigns this to <code>fileLogger</code>.
     * @throws IOException Exception thrown
     */
    public TotalRevenueFileOutput() throws IOException {
        fileLogger = new FileLogger(fileLocation);
    }

    @Override
    protected void handleErrors(Exception e) {
        System.out.println("Something went wrong, cannot log current total revenue. ");
    }

    @Override
    protected void showTotalIncome(double totalRev) {
        fileLogger.log(Double.toString(totalRev));
        System.out.println();
    }
}