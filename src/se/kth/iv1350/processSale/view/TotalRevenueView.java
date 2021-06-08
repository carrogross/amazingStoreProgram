package se.kth.iv1350.processSale.view;

/**
 * Shows the total income of all sales completed since program started on the user interface.
 */
public class TotalRevenueView extends TotalRevenueDisplay {

    @Override
    protected void handleErrors(Exception e) {
        System.out.println("Something went wrong, the revenue cannot be displayed.");
    }

    @Override
    protected void showTotalIncome(double totalRevenue) {
        System.out.println("****************************************");
        System.out.println("*         Current revenue: " + totalRevenue + "       *");
        System.out.println("****************************************");
    }
}
