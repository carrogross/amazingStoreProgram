package se.kth.iv1350.processSale.controller;

import se.kth.iv1350.processSale.integration.*;
import se.kth.iv1350.processSale.model.DiscountRules;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.SalesLog;

/**
 *
 */
public class Controller {
    SalesLog salesLog;
    DiscountRules discountRules;
    Sale saleDetails;
    SystemStartup systemStartup;
    InventorySystem inventorySystem;
    Register register;
    Printer printer;

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
     *
     * @param quantity
     * @param itemIdentifier
     * @return
     */
    public Sale scanItem(int quantity, int itemIdentifier){
        if(quantity == 0)
            quantity = 1;

        ItemDTO itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity);
        return saleDetails;
    }

    /**
     *
     * @return
     */
    public double endSale(){
        return saleDetails.getTotalPriceInclVat();
    }

    /**
     *
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
     *
     * @param customerPersonalNumber
     * @return
     */
    public double requestDiscount(long customerPersonalNumber) {
        return discountRules.calculateDiscount(customerPersonalNumber, saleDetails);
    }
}
