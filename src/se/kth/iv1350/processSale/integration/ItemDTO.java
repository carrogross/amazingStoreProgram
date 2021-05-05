package se.kth.iv1350.processSale.integration;

public class ItemDTO {
    private double itemPrice;
    private String itemName;
    private int itemIdentifier;
    private String itemDescription;
    private double itemVatRate;

    /**
     * PAKETPRIVAT
     * @param price
     * @param identifier
     * @param name
     * @param vatRate
     * @param description
     */
    ItemDTO (double price, int identifier, String name, int vatRate, String description){
        this.itemPrice = price;
        this.itemName = name;
        this.itemIdentifier = identifier;
        this.itemDescription = description;
        this.itemVatRate = vatRate;

    }

    /**
     * ----------------------
     * @return
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     * ----------------------
     * @return
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * ----------------------
     * @return
     */
    public int getItemIdentifier() {
        return itemIdentifier;
    }

    /**
     * ----------------------
     * @return
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * ---------------------
     * @return
     */
    public double getItemVatRate() {
        return itemVatRate;
    }
}
