package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.model.SaleObserver;

public abstract class TotalRevenueDisplay implements SaleObserver {
    private double totalRevenue;

    protected TotalRevenueDisplay(){
        this.totalRevenue = 0;
    }

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
