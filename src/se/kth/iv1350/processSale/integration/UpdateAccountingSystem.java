package se.kth.iv1350.processSale.integration;

/**
 * Contains the logic updating the external accounting system.
 */
public class UpdateAccountingSystem implements UpdateExternalSystems{
    SystemStartup systemStartup;
    AccountingSystem accountingSystem;

    /**
     * Creates a new instance.
     */
    public UpdateAccountingSystem(){

    }

    /**
     * Creates a new instance with the specified <code>SystemStartup</code> and its <code>accountingSystem</code>
     * to suitable fields.
     * @param systemStartup The object holding the <code>SystemStartup</code>.
     */
    public UpdateAccountingSystem(SystemStartup systemStartup){
        this.systemStartup = systemStartup;
        this.accountingSystem = systemStartup.getAccountingSystem();
    }

    /**
     * Updates the external accounting system.
     * @param receiptDTO The object holding all information about current sale.
     */
    @Override
    public void updateSystems(ReceiptDTO receiptDTO) {
        accountingSystem.updateAccountingSystem(receiptDTO);
    }
}
