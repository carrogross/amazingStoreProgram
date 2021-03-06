package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains information about the current sale and each entered item.
 */
public class Sale {
    ArrayList<ItemDTO> itemListInSale;
    List<Integer> itemQuantityListInSale;
    double totalPriceForSale;
    double totalVatPrice;
    int totalItemQuantityInSale;

    /**
     * Creates a new <code>Sale</code> to enter information about the sale in.
     */
    public Sale(){
        this.itemQuantityListInSale = new ArrayList<>();
        this.itemListInSale = new ArrayList<>();
        totalPriceForSale = 0;
        totalVatPrice = 0;
        totalItemQuantityInSale = 0;
    }

    /**
     * Adds an <code>ItemDTO</code> to the current sale, according to entered quantity.
     * If the item har already been scanned before, only the quantity for that item will be increased.
     * @param itemDTO The object containing all information about the item that should be entered to the current sale.
     * @param quantity The amount of the item in <code>ItemDTO</code> that should be added to the current sale.
     */
    public void addItem(ItemDTO itemDTO,int quantity){

        if(!itemAlreadyScanned(itemDTO)){
            this.itemListInSale.add(itemDTO);
            this.itemQuantityListInSale.add(quantity);
        }
        else{
            updateAlreadyScannedItemQuantity(itemDTO, quantity);
        }

        this.totalPriceForSale += itemDTO.getItemPrice() * quantity;
        this.totalVatPrice += getItemVatPrice(itemDTO) * quantity;
        this.totalItemQuantityInSale += quantity;
    }

    private boolean itemAlreadyScanned(ItemDTO itemDTO){
        return itemListInSale.contains(itemDTO);
    }

    private void updateAlreadyScannedItemQuantity(ItemDTO itemDTO, int quantity){
        for (int index = 0; index < itemListInSale.size(); index++){
            if(itemFound(itemDTO, index)){
                updateQuantityList(quantity, index);
            }
        }
    }

    private void updateQuantityList(int quantity, int i) {
        int previousQuantity = itemQuantityListInSale.get(i);
        int newQuantity = previousQuantity + quantity;
        itemQuantityListInSale.set(i, newQuantity);
    }

    private boolean itemFound(ItemDTO itemDTO, int i) {
        return itemListInSale.get(i).getItemIdentifier() == itemDTO.getItemIdentifier();
    }

    private double getItemVatPrice(ItemDTO itemDTO) {
        return calculateItemVatPrice(itemDTO);
    }

    private double calculateItemVatPrice(ItemDTO itemDTO) {
        return (itemDTO.getItemVatRate() / 100) * itemDTO.getItemPrice();
    }

    /**
     * Returns a list of all entered items in the current sale.
     * @return The list of all entered items in the current sale.
     */
    public ArrayList<ItemDTO> getItemListInSale() {
        return itemListInSale;
    }

    /**
     * Returns a list of all quantities of the entered items in the current sale.
     * @return The list of the quantities of all entered items in the current sale.
     */
    public List<Integer> getItemQuantityListInSale() {
        return itemQuantityListInSale;
    }

    /**
     * Returns the total price of the entered items in the current sale.
     * @return The total price of the entered items in the current sale.
     */
    public double getTotalPriceForSale() {
        return totalPriceForSale;
    }

    /**
     * Updates the total price of the entered items in the current sale.
     * @param totalPriceForSale The total price of the entered items in the current sale.
     */
    public void setTotalPriceForSale(double totalPriceForSale) {
        this.totalPriceForSale = totalPriceForSale;
    }

    /**
     * Returns the total VAT-price of all entered items in the current sale.
     * @return The total VAT-price of all entered items in the current sale.
     */
    public double getTotalVatPrice() {
        return totalVatPrice;
    }

    /**
     * Updates the total VAT-price of all entered items in the current sale.
     * @param totalVatPrice The total VAT-price of all entered items in the current sale.
     */
    public void setTotalVatPrice(double totalVatPrice) {
        this.totalVatPrice = totalVatPrice;
    }

    /**
     * Returns the total price for the entire sale, e.g. total price for
     * all items + total VAT-price for all items in the current sale.
     * @return The total price for the entire sale, e.g. total price for
     * all items + total VAT-price for all items in the current sale.
     */
    public double getTotalPriceInclVat(){
        return totalPriceForSale + totalVatPrice;
    }

    /**
     * Returns the total quantity of items scanned in current sale.
     * @return The total quantity of items scanned in current sale.
     */
    public int getTotalItemQuantityInSale() {
        return totalItemQuantityInSale;
    }
}
