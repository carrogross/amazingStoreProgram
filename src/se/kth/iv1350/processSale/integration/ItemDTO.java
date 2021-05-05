package se.kth.iv1350.processSale.integration;

/**
 * Represents on <code>ItemDTO</code>.
 */
public class ItemDTO {
    private double itemPrice;
    private String itemName;
    private int itemIdentifier;
    private String itemDescription;
    private double itemVatRate;

    ItemDTO (double price, int identifier, String name, int vatRate, String description){
        this.itemPrice = price;
        this.itemName = name;
        this.itemIdentifier = identifier;
        this.itemDescription = description;
        this.itemVatRate = vatRate;

    }

    /**
     * Gets the price for the item.
     * @return A double representing the price for the item.
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     * Gets the name of the item.
     * @return A String representing the name of the item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Gets the identifier of the item.
     * @return An int representing the identifier of the item.
     */
    public int getItemIdentifier() {
        return itemIdentifier;
    }

    /**
     * Gets the description of the item.
     * @return A String representing the description of the item.
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Gets the VAT-rate for the item.
     * @return A double representing the VAT-rate for the item.
     */
    public double getItemVatRate() {
        return itemVatRate;
    }
}
