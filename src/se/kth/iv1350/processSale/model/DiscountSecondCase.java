package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.ItemDTO;

/**
 * Contains the second algorithm of the strategy pattern which gives discount if a specific item is part on the sale.
 */
public class DiscountSecondCase implements DiscountEligibility{
    String specificItem = "Popcorn";

    /**
     * Checks if <code>specificItem</code> is part of the sale, in that case: gives 50% discount of entire sale.
     * @param customerPersonalNumber The customer's personal number entered at POS.
     * @param saleDetails Information about the current sale.
     */
    @Override
    public void calculateDiscount(long customerPersonalNumber, Sale saleDetails) {
        for(ItemDTO itemDTO : saleDetails.getItemListInSale()){
            if(itemFound(itemDTO))
                saleDetails.setTotalPriceForSale(saleDetails.getTotalPriceForSale() * 0.5);
        }
    }

    private boolean itemFound(ItemDTO itemDTO) {
        return specificItem.equals(itemDTO.getItemName());
    }
}
