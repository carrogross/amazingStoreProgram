package se.kth.iv1350.processSale.controller;

import se.kth.iv1350.processSale.integration.*;
import se.kth.iv1350.processSale.model.DiscountRules;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.SalesLog;

/**
 * The class containing the programs only Controller. All calls from view must go
 * through Controller which distributes to the right class and method.
 */
public class Controller {
    SalesLog salesLog;
    DiscountRules discountRules;
    Sale saleDetails;
    SystemStartup systemStartup;
    InventorySystem inventorySystem;
    Register register;
    Printer printer;
    ItemDTO latestScannedItemDTO;

    /**
     * The only Controller in the program. All calls from view must go
     * through Controller which distributes to the right class and method.
     */
    public Controller(){
        this.systemStartup = new SystemStartup();
        this.inventorySystem = systemStartup.getInventorySystem();
        this.printer = systemStartup.getPrinter();
        this.register = systemStartup.getRegister();
        this.salesLog = new SalesLog(systemStartup);
        this.discountRules = new DiscountRules();
    }

    /**
     * Creates a new, empty Sale, named saleDetails. Must be called before doing anything
     * else during a sale. Does not apply any values to fields in the Sale.
     */
    public void initializeSale(){
        saleDetails = new Sale();
    }

    /**
     * Gets item details regarding the item of the <code>itemIdentifier</code> from inventory system, adds the scanned item to the sale.
     * If a quantity 0 is entered this is changed to 1. Returns the updated sale object with the latest scanned item added to it.
     * @param itemIdentifier The number code identifying the item scanned.
     * @return The updated sale object with the latest scanned item added to it.
     */
    public Sale scanItem(int itemIdentifier) {
        int quantity = 1;
        try {
            latestScannedItemDTO = inventorySystem.getItemDetails(itemIdentifier);
        } catch(InvalidIdentifierException invalidIdentifierException){
            System.out.println("The entered identifier is invalid!\n");
            return saleDetails;
        }

        saleDetails.addItem(latestScannedItemDTO, quantity);
        return saleDetails;
    }

    /**
     * Returns the total price of the sale, including the total VAT-price.
     * @return The total price of the sale, including the total VAT-price.
     */
    public double endSale(){
        return saleDetails.getTotalPriceInclVat();
    }

    /**
     * Updates the amount of cash in the register with the total price for the sale, incl. VAT. Sends a created
     * <code>ReceiptDTO</code> to the printer to print, logs the sale in the sales log and returns the
     * value of cash that should be given to the customer as change.
     * @param amountPaid The value of cash that is entered as payment for the purchase.
     * @return The value of cash that should be given to customer as change.
     */
    public double calculateChange(double amountPaid){
        double amountChange = register.updateAmountInRegister(amountPaid, saleDetails);

        ReceiptDTO receiptDTO = new ReceiptDTO(saleDetails, amountPaid, amountChange);
        printer.printReceipt(receiptDTO);
        salesLog.logSale(receiptDTO);

        return amountChange;
    }

    /**
     * Not a part of this exercise, yet...
     * @param customerPersonalNumber
     * @return
     */
    public double requestDiscount(long customerPersonalNumber) {
        return discountRules.calculateDiscount(customerPersonalNumber, saleDetails);
    }

    /**
     * Gets the <code>ItemDTO</code> that represents the latest scanned item.
     * @return The <code>ItemDTO</code> that represents the latest scanned item.
     */
    public ItemDTO getLatestScannedItemDTO() {
        return latestScannedItemDTO;
    }
}
