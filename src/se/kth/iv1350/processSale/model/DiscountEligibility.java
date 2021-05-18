package se.kth.iv1350.processSale.model;

/**
 * Interface specifying the strategy pattern algorithm.
 */
public interface DiscountEligibility {

    void calculateDiscount(long customerPersonalNumber, Sale saleDetails);
}
