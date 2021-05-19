package se.kth.iv1350.processSale.controller;

import se.kth.iv1350.processSale.integration.*;
import se.kth.iv1350.processSale.model.*;
import se.kth.iv1350.processSale.util.ConsoleLogger;
import se.kth.iv1350.processSale.util.Logger;
import se.kth.iv1350.processSale.view.TotalRevenueView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class containing the programs only Controller. All calls from view must go
 * through Controller which distributes to the right class and method.
 */
public class Controller {
    SalesLog salesLog;
    DiscountRules discountRules ;
    Sale saleDetails;
    SystemStartup systemStartup;
    InventorySystem inventorySystem;
    Register register;
    Printer printer;
    ItemDTO latestScannedItemDTO;
    Logger logger;
    ConsoleLogger consoleLogger;
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * The only Controller in the program. All calls from view must go
     * through Controller which distributes to the right class and method.
     */
    public Controller(Logger logger) throws IOException {
        this.systemStartup = new SystemStartup();
        this.inventorySystem = systemStartup.getInventorySystem();
        this.printer = systemStartup.getPrinter();
        this.register = systemStartup.getRegister();
        this.salesLog = new SalesLog(systemStartup);
        this.discountRules = new DiscountRules(new DiscountFirstCase(), new DiscountSecondCase());
        this.logger = logger;
        consoleLogger = new ConsoleLogger(consoleLogger);
        salesLog.addSaleObserver(new TotalRevenueView());
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
     * For this part of the exercise quantity is fixed to 1, variable is not deleted because of the option to later make it enter another quantity.
     * @param quantity The number of items with the entered identifier cashier wants to add to the sale.
     * @param itemIdentifier The number code identifying the item scanned.
     * @return The updated sale object with the latest scanned item added to it.
     * @throws InvalidIdentifierException Is thrown if an identifier is entered that is not in the item registry.
     * @throws OperationFailedException Is thrown if a FailureDBReachException is caught because the database is not reached.
     */
    public Sale scanItem(int quantity, int itemIdentifier) throws InvalidIdentifierException, OperationFailedException, IOException {
        try {
            latestScannedItemDTO = inventorySystem.getItemDetails(itemIdentifier);
            saleDetails.addItem(latestScannedItemDTO, quantity);
        } catch(FailureDBReachException failureDBReachException){
            logger.log(failureDBReachException.toString());
            throw new OperationFailedException("Unable to read from database\n", failureDBReachException);
        }
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
     * @param customerPersonalNumber The personal number identifying the customer, which can determine if the customer is eligible
     * for a discount or not.
     * @return The total price for the sale, after discount, incl VAT.
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

    /**
     * Adds the specified view to a list of observers that should be notified when a sale is completed.
     * @param totalRevenueView The view that should be added to the list.
     */
    public void addSaleObserver(TotalRevenueView totalRevenueView){
        saleObservers.add(totalRevenueView);
    }
}
