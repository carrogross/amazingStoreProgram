package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.model.SaleObserver;

/**
 * Shows the total income of all sales completed since program started on the user interface.
 */
public class TotalRevenueView implements SaleObserver {
    double totalRevenue;

    /**
     * Prints the total revenue to the console.
     * @param totalRev The total revenue that should be printed.
     */
    @Override
    public void saleRevenue(double totalRev) {
        calculateTotalRev(totalRev);
        System.out.println("****************************************");
        System.out.println("*         Current revenue: " + totalRevenue + "        *");
        System.out.println("****************************************");
    }

    /**
     * Calculates the total revenue of sales completed since the program was started.
     * @param totalPrice The total price, incl VAT, for the current sale.
     */
    @Override
    public void calculateTotalRev(double totalPrice) {
        totalRevenue += totalPrice;
    }
}
