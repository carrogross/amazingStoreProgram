package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.util.FileLogger;
import java.io.IOException;

/**
 * Contains the class responsible for writing the total revenue to a file.
 */
public class TotalRevenueFileOutput implements SaleObserver {
    FileLogger fileLogger;
    String fileLocation = "src/se/kth/iv1350/processSale/model/Revenue.txt";
    double totalRevenue;

    /**
     * Creates a new instance of <code>TotalRevenueFileOutput</code> and creates a new <code>FileLogger</code> at
     * <code>fileLocation</code> and assigns this to <code>fileLogger</code>.
     * @throws IOException Exception thrown
     */
    public TotalRevenueFileOutput() throws IOException {
        fileLogger = new FileLogger(fileLocation);
    }

    /**
     * Calculates the new total revenue and logs this to a file.
     * @param totalRev The total price for the current sale, incl VAT.
     */
    @Override
    public void saleRevenue(double totalRev) {
        calculateTotalRev(totalRev);
        fileLogger.log(revToString());
        System.out.println();
    }

    /**
     * Calculates the new total revenue since the program was started.
     * @param totalPrice The total price for the current sale, incl VAT.
     */
    @Override
    public void calculateTotalRev(double totalPrice) {
        totalRevenue += totalPrice;
    }

    private String revToString() {
        return Double.toString(totalRevenue);
    }
}