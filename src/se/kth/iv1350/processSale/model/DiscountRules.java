package se.kth.iv1350.processSale.model;

/**
 * Contains information and calculations about discounts.
 */
public class DiscountRules {

    /**
     * Not a part of seminar 3.
     * @param customerPersonalNumber A long representing the customer's personal number.
     * @param saleDetails The object containing all information about the sale and it's items.
     * @return
     */
    public double calculateDiscount(long customerPersonalNumber, Sale saleDetails){
        double totalPriceAfterDiscount;
        int totalItemQuantityInSale;
        if(saleDetails.getTotalPriceForSale() > 100){
            saleDetails.setTotalPriceForSale(saleDetails.getTotalPriceForSale() * 0.9);
        }
        if(saleDetails.getTotalItemQuantityInSale() > 10){
            saleDetails.setTotalPriceForSale(saleDetails.getTotalPriceForSale() * 0.9);

        }

        return saleDetails.getTotalPriceInclVat();
    }
}
