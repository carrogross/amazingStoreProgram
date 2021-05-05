package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;

/**
 *
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
     * Updates external inventory system.
     * @param receiptDTO Object containing all available information about current sale.
     */
    public void updateInventorySystem(ReceiptDTO receiptDTO){

    }

    /**
     * -------------------
     * ofullständig
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
