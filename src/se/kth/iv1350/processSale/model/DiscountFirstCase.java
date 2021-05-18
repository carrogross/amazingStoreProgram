package se.kth.iv1350.processSale.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the first algorithm of the strategy pattern which gives discount if customer is in the <code>customerList</code>.
 */
public class DiscountFirstCase implements DiscountEligibility{
    private List<Long> customerList;

    /**
     * Creates a new instance of <code>DiscountFirstCase</code> and adds customers to the <code>customerList</code>.
     */
    public DiscountFirstCase() {
        this.customerList = new ArrayList<>();
        customerList.add(1L);
        customerList.add(2L);
        customerList.add(3L);

    }

    /**
     * Checks if <code>customerPersonalNumber</code> is in the <code>customerList</code> and if it is; gives 10% discount.
     * @param customerPersonalNumber The customer's personal number entered at POS.
     * @param saleDetails Information about the current sale.
     */
    @Override
    public void calculateDiscount(long customerPersonalNumber, Sale saleDetails) {
        for(long customer : customerList){
            if(customerPersonalNumber == customer)
                saleDetails.setTotalPriceForSale(saleDetails.getTotalPriceForSale() * 0.9);
        }
    }
}
