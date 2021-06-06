package se.kth.iv1350.processSale.model;

/**
 * Observes when a sale is completed and the notification with total price for that sale is sent.
 */
public interface SaleObserver {

    void saleCompleted(double totalPriceOfCurrentSale);
}
