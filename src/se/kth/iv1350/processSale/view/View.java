package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.model.Sale;

/**
 * A placeholder for the actual View. The user will see what View prints out.
 */
public class View {
    private Controller controller;

    /**
     * Creates the view and starts a new Controller and assigns it to the controller field.
     */
    public View(){
        this.controller = new Controller();
    }

    /**
     * Performs a fake sale session to demonstrate the program by calling all system operations in
     * the controller.
     */
    public void runExampleSession() {

        initializeNewSale();
        scanItem(1, 123456);
        scanItem(2, 456789);
        Sale saleDetails = scanItem(3, 123456);
        endSale(saleDetails);
        //requestDiscount(customerPersonalNumber);
        calculateChange(100);
    }

    private void initializeNewSale() {
        controller.initializeSale();
        System.out.println("A new sale har been initialized. \n");
    }

    private Sale scanItem(int quantity, int itemIdentifier) {
        Sale saleDetails = controller.scanItem(quantity, itemIdentifier);
        ItemDTO latestScannedItem = controller.getLatestScannedItemDTO();
        System.out.println("Latest scanned item: " + latestScannedItem.getItemName() + " - " + latestScannedItem.getItemDescription() + ", Price: "
                + latestScannedItem.getItemPrice() + " SEK" + ", Quantity: " + quantity + ", Total price: "
                + latestScannedItem.getItemPrice() * quantity + " SEK \nRunning total incl VAT: " + saleDetails.getTotalPriceInclVat() + " SEK \n");
        return saleDetails;
    }

    private void calculateChange(double amountPaid) {
        double amountChange = controller.calculateChange(amountPaid);
        System.out.println("Amount paid: " + amountPaid + " SEK");
        System.out.println("Amount of change to give back to customer: " + amountChange + " SEK \n");
    }

    private void requestDiscount(long customerPersonalNumber) {
        controller.requestDiscount(customerPersonalNumber);
        System.out.println("Total price after discount: ");
    }

    private void endSale(Sale saleDetails) {
        controller.endSale();
        System.out.println("Total price: " + saleDetails.getTotalPriceInclVat() + " SEK \n");
    }
}
