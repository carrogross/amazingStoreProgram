package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;

/**
 * Class containing all communication with External Inventory System. In this version also contains a
 * hard coded item registry with some <code>ItemDTO</code>s to make it possible to run the program.
 */
public class InventorySystem {
    ItemDTO itemDTO;
    ArrayList<ItemDTO> inventoryItemList;
    ArrayList<Integer> inventoryQuantityList;

    InventorySystem() {
        inventoryItemList = new ArrayList<>();
        inventoryQuantityList = new ArrayList<>();

        inventoryItemList.add(new ItemDTO(5, 234567, "Mjölk", 25, "Arla"));
        inventoryItemList.add(new ItemDTO(7, 123456, "Gurka", 12, "Ekologisk"));
        inventoryItemList.add(new ItemDTO(10, 345678, "Glass", 25, "Jordgubb"));
        inventoryItemList.add(new ItemDTO(3, 456789, "Popcorn", 12, "Smörsmak"));
    }

    /**
     * Would contain a method for updating the Inventory System with information from <code>ReceiptDTO</code>,
     * for this exercise: does nothing.
     * @param receiptDTO The object containing all information about the current sale.
     */
    public void updateInventorySystem(ReceiptDTO receiptDTO){

    }

    /**
     * Collects all information about the item represented by <code>itemIdentifier</code> and puts these together in an <code>ItemDTO</code>.
     * @param itemIdentifier The 6 figured number code on the items bar code.
     * @return The <code>ItemDTO</code> containing all information in the item registry about the item with the specified <code>itemIdentifier</code>.
     */
    public ItemDTO getItemDetails(int itemIdentifier) {
        return findItem(itemIdentifier);
    }

    private ItemDTO findItem(int itemIdentifier){
        for(int index = 0; index < inventoryItemList.size(); index++){
            itemDTO = inventoryItemList.get(index);
            if(itemFound(itemIdentifier)){
                return itemDTO;
            }
        }
        return itemDTO = null;
    }

    private boolean itemFound(int itemIdentifier){
        return itemDTO.getItemIdentifier() == itemIdentifier;
    }

}
