package se.kth.iv1350.processSale.model;

/**
 * -------------------
 */
public class DiscountRules {

    /**
     * --------------------
     * ska vara public DiscountRules bootDiscountRules och returnera discountRules
     */
    public DiscountRules(){

    }

    /**
     *
     * @param customerPersonalNumber
     * @param saleDetails
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
