package se.kth.iv1350.processSale.model;

/**
 * Contains information and calculations about discounts.
 */
public class DiscountRules {
    DiscountEligibility discountFirstCase;
    DiscountEligibility discountSecondCase;

    /**
     * Creates a new instance of <code>DiscountRules</code> with the specified <code>DiscountFirstCase</code> and
     * <code>DiscountSecondCase</code>
     * @param discountFirstCase The first algorithm of the strategy pattern which gives discount if customer is
     *                          in the <code>customerList</code>.
     * @param discountSecondCase The second algorithm of the strategy pattern which gives discount if a specific
     *                           item is part on the sale.
     */
    public DiscountRules(DiscountFirstCase discountFirstCase, DiscountSecondCase discountSecondCase){
        this.discountFirstCase = discountFirstCase;
        this.discountSecondCase = discountSecondCase;
    }

    /**
     * Checks if customer got the member's discount and then checks for eligibility for the item discount.
     * @param customerPersonalNumber A long representing the customer's personal number.
     * @param saleDetails The object containing all information about the sale and it's items.
     * @return The total price for the sale after discount, incl VAT-price.
     */
    public double calculateDiscount(long customerPersonalNumber, Sale saleDetails){
        double priceHolder = saleDetails.getTotalPriceForSale();
        discountFirstCase.calculateDiscount(customerPersonalNumber, saleDetails);
        if(priceHolder != saleDetails.getTotalPriceForSale())
            discountSecondCase.calculateDiscount(customerPersonalNumber, saleDetails);

        return saleDetails.getTotalPriceInclVat();
    }
}
