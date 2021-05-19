package se.kth.iv1350.processSale.integration;

/**
 * Contains the logic updating the external inventory system.
 */
public class UpdateInventorySystem implements UpdateExternalSystems{
    SystemStartup systemStartup;
    InventorySystem inventorySystem;

    /**
     * Creates a new instance.
     */
    public UpdateInventorySystem(){
    }

    /**
     * Creates a new instance with the specified <code>SystemStartup</code> and its <code>accountingSystem</code>
     * to suitable fields.
     * @param systemStartup The object holding the <code>SystemStartup</code>.
     */
    public UpdateInventorySystem(SystemStartup systemStartup){
        this.systemStartup = systemStartup;
        this.inventorySystem = systemStartup.getInventorySystem();
    }

    /**
     * Updates the external inventory system.
     * @param receiptDTO The object holding all information about current sale.
     */
    @Override
    public void updateSystems(ReceiptDTO receiptDTO) {
        inventorySystem.updateInventorySystem(receiptDTO);
    }
}
