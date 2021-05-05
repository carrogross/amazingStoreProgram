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
     * @param quantity1 Quantity of the first item in the sale.
     * @param itemIdentifier1 Identifier of the first item in the sale.
     * @param quantity2 Quantity of the second item in the sale.
     * @param itemIdentifier2 Identifier of the second item in the sale.
     * @param quantity3 Quantity of the third item in the sale.
     * @param itemIdentifier3 Identifier of the third item in the sale.
     * @param customerPersonalNumber A personal number used to request discount on current sale.
     * @param amountPaid Total value of cash that is used to pay for the sale.
     */
    public void runExampleSession(int quantity1, int itemIdentifier1, int quantity2, int itemIdentifier2, int quantity3, int itemIdentifier3, long customerPersonalNumber, double amountPaid) {

        initializeNewSale();
        scanItem(quantity1, itemIdentifier1);
        scanItem(quantity2, itemIdentifier2);
        Sale saleDetails = scanItem(quantity3, itemIdentifier3);
        endSale(saleDetails);
        //requestDiscount(customerPersonalNumber);
        calculateChange(amountPaid);

    }

    private void initializeNewSale() {
        controller.initializeSale();
        System.out.println("A new sale har been initialized. \n");
    }

    private Sale scanItem(int quantity1, int itemIdentifier1) {
        Sale saleDetails = controller.scanItem(quantity1, itemIdentifier1);
        ItemDTO latestScannedItem = saleDetails.getItemListInSale().get(saleDetails.getItemListInSale().size() - 1);
        System.out.println("Latest scanned item: " + latestScannedItem.getItemName() + " - " + latestScannedItem.getItemDescription() + ", Price: "
                + latestScannedItem.getItemPrice() + " SEK" + ", Quantity: " + quantity1 + ", Total price: "
                + latestScannedItem.getItemPrice() * quantity1 + " SEK \nRunning total incl VAT: " + saleDetails.getTotalPriceInclVat() + " SEK \n");
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
