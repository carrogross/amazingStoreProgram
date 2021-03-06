package se.kth.iv1350.processSale.integration;

/**
 * Contains all communication with the printer.
 */
public class Printer {
    ReceiptDTO receiptDTO;

    Printer(){

    }

    /**
     * Prints a receipt containing information about the current sale, incl information about the store and time of sale.
     * @param receiptDTO The object containing all information about the current sale.
     */
    public void printReceipt(ReceiptDTO receiptDTO){
        this.receiptDTO = receiptDTO;
        int itemsLeftToPrint = receiptDTO.getSaleDetails().getItemListInSale().size();
        String storeName = receiptDTO.getStoreName();
        String storeStreetName = receiptDTO.getStoreStreetName();
        int storeStreetNumber = receiptDTO.getStoreStreetNumber();
        int storePostalCode = receiptDTO.getStorePostalCode();
        String storeCityName = receiptDTO.getStoreCityName();

        System.out.println("----------------------------------------------------");
        System.out.println("RECEIPT: ");
        System.out.println(receiptDTO.getTimeAndDate() + "\n");
        System.out.println(storeName);
        System.out.println(storeStreetName + " " + storeStreetNumber);
        System.out.println(storePostalCode + " " + storeCityName + "\n");

        for(int i = 0; i < itemsLeftToPrint; i++){
            ItemDTO itemToPrint = receiptDTO.getSaleDetails().getItemListInSale().get(i);
            String itemName = itemToPrint.getItemName();
            int itemQuantity = receiptDTO.getSaleDetails().getItemQuantityListInSale().get(i);
            double itemPrice = itemToPrint.getItemPrice();
            double totalItemPrice = itemPrice * itemQuantity;

            System.out.println(itemName + "    " + itemPrice + " SEK    x" + itemQuantity + "   " + totalItemPrice + " SEK");
        }
        System.out.println();
        System.out.println("Total price excl VAT: " + receiptDTO.getSaleDetails().getTotalPriceForSale() + " SEK");
        System.out.println("Total VAT-price: " + receiptDTO.getSaleDetails().getTotalVatPrice() + " SEK");
        System.out.println("TOTAL PRICE INCL VAT: " + receiptDTO.getSaleDetails().getTotalPriceInclVat() + " SEK\n");

        System.out.println("Amount paid: " + receiptDTO.getAmountPaid() + " SEK");
        System.out.println("Amount change: " + receiptDTO.getAmountChange() + " SEK\n");
        System.out.println("Thank you for shopping at Amazing Store! \nWelcome back soon!");
        System.out.println("----------------------------------------------------\n");
    }
}
