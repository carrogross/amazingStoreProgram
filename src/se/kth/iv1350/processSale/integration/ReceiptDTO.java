package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.model.Sale;

import java.time.LocalDateTime;

/**
 * Representa a <code>ReceiptDTO</code> containing all information about the sale, the store and the time of the sale.
 */
public class ReceiptDTO {
    static final String storeStreetName = "Amazing Av.";
    static final int storeStreetNumber = 1;
    static final String storeCityName = "Stockholm";
    static final int storePostalCode = 12345;
    static final String storeName = "Amazing Store";
    Sale saleDetails;
    double amountPaid;
    LocalDateTime timeAndDate;
    double amountChange;


    /**
     * Creates the <code>ReceiptDTO</code> for current sale.
     * @param saleDetails Information about the sale and every item in this.
     * @param amountPaid Total value of cash entered as payment for the sale.
     * @param amountChange Total value of cash that is given back to the customer as change.
     */
    public ReceiptDTO(Sale saleDetails, double amountPaid, double amountChange){
        this.saleDetails = saleDetails;
        this.amountPaid = amountPaid;
        this.timeAndDate = LocalDateTime.now();
        this.amountChange = amountChange;
    }

    static String getStoreStreetName() {
        return storeStreetName;
    }

    static int getStoreStreetNumber() {
        return storeStreetNumber;
    }

    static String getStoreCityName() {
        return storeCityName;
    }

    static int getStorePostalCode() {
        return storePostalCode;
    }

    static String getStoreName() {
        return storeName;
    }

    Sale getSaleDetails() {
        return saleDetails;
    }

    double getAmountPaid() {
        return amountPaid;
    }

    LocalDateTime getTimeAndDate() {
        return timeAndDate;
    }

    double getAmountChange() {
        return amountChange;
    }
}
