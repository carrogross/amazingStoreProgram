package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.model.SaleObserver;

/**
 * Shows the total income of all sales completed since program started on the user interface.
 */
public class TotalRevenueView implements SaleObserver {
    double totalRevenue;

    /**
     *
     * @param totalRev
     */
    @Override
    public void saleRevenue(double totalRev) {
        calculateTotalRev(totalRev);
        System.out.println("****************************************");
        System.out.println("*         Current revenue: " + totalRevenue + "        *");
        System.out.println("****************************************");
    }

    /**
     *
     * @param totalPrice
     */
    @Override
    public void calculateTotalRev(double totalPrice) {
        totalRevenue += totalPrice;
    }
}
