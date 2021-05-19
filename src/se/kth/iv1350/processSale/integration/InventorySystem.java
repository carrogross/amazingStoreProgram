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
        inventoryItemList.add(new ItemDTO(0, 666666, "DB failure", 12, "Always generates DB failure"));

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
     * @throws InvalidIdentifierException Is thrown if an identifier is entered that is not in the item registry.
     * @throws FailureDBReachException Is thrown if the database is not reached.
     */
    public ItemDTO getItemDetails(int itemIdentifier) throws InvalidIdentifierException, FailureDBReachException {
        if(itemIdentifier == 666666)
            throw new FailureDBReachException("error code: 10104040333");
        return findItem(itemIdentifier);
    }

    private ItemDTO findItem(int itemIdentifier) throws InvalidIdentifierException{
        for (ItemDTO itemDTO : inventoryItemList) {
            this.itemDTO = itemDTO;
            if (itemFound(itemIdentifier)) {
                return this.itemDTO;
            }
        }

        throw new InvalidIdentifierException("Invalid identifier entered" + itemIdentifier);
    }

    private boolean itemFound(int itemIdentifier){
        return itemDTO.getItemIdentifier() == itemIdentifier;
    }
}
