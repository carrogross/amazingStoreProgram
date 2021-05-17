package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.InvalidIdentifierException;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.OperationFailedException;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.FileLogger;

import java.io.IOException;

/**
 * A placeholder for the actual View. The user will see what View prints out.
 */
public class View {
    private Controller controller;
    private Sale saleDetails;

    /**
     * Creates the view and starts a new Controller and assigns it to the controller field.
     */
    public View() throws IOException {
        this.controller = new Controller(new FileLogger());
    }

    /**
     * Performs a fake sale session to demonstrate the program by calling all system operations in
     * the controller.
     */
    public void runExampleSession() {

        initializeNewSale();
        scanItem(3, 123456);
        scanItem(2, 111111);
        scanItem(2, 123456);
        scanItem(0, 666666);
        scanItem(0, 234567);
        endSale(saleDetails);
        //requestDiscount(customerPersonalNumber);
        calculateChange(100);
    }

    private void initializeNewSale() {
        controller.initializeSale();
        System.out.println("A new sale har been initialized. \n");
    }

    private void scanItem(int quantity, int itemIdentifier) {
        if(quantity == 0)
            quantity = 1;

        try{
        this.saleDetails = controller.scanItem(quantity, itemIdentifier);
        } catch(InvalidIdentifierException invalidIdentifierException){
            System.out.println("The entered identifier is invalid!\n");
            return;
        } catch(OperationFailedException operationFailedException){
            System.out.println("Something went wrong, please try again or contact developer. " + operationFailedException);
            return;
        } catch(IOException ioException){
            ioException.printStackTrace();
        }

        ItemDTO latestScannedItem = controller.getLatestScannedItemDTO();
        System.out.println("Scanned item: " + latestScannedItem.getItemName() + " - " + latestScannedItem.getItemDescription() + ", Price: "
                + latestScannedItem.getItemPrice() + " SEK" + ", Quantity: " + quantity + ", Total price: "
                + latestScannedItem.getItemPrice() * quantity + " SEK \nRunning total incl VAT: " + saleDetails.getTotalPriceInclVat() + " SEK \n");
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
        System.out.println("TOTAL PRICE INCL VAT: " + saleDetails.getTotalPriceInclVat() + " SEK \n");
    }
}
