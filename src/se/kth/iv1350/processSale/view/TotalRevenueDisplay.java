package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.model.SaleObserver;

/**
 * Template class containing the template for the observer classes extending it.
 */
public abstract class TotalRevenueDisplay implements SaleObserver {
    private double totalRevenue;

    protected TotalRevenueDisplay(){
        this.totalRevenue = 0;
    }

    /**
     * Calculates the updated total income, including the specified price for the
     * last completed sale.
     * @param priceOfTheSaleThatWasJustMade A double representing the total price of the sale
     *                                      that was just completed.
     */
    @Override
    public void saleCompleted(double priceOfTheSaleThatWasJustMade) {
        calculateTotalIncome(priceOfTheSaleThatWasJustMade);
        showTotalIncome();
    }

    private void showTotalIncome() {
        try {
            showTotalIncome(totalRevenue);
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    private void calculateTotalIncome(double priceOfTheSaleThatWasJustMade){
        totalRevenue += priceOfTheSaleThatWasJustMade;
    }

    protected abstract void handleErrors(Exception e);

    protected abstract void showTotalIncome(double totalRev) throws Exception;
}
