package se.kth.iv1350.processSale.integration;

/**
 * Initiates the start of each external system: register, printer, inventory system, accounting system.
 */
public class SystemStartup {
    Register register;
    Printer printer;
    InventorySystem inventorySystem;
    AccountingSystem accountingSystem;
    Composite composite;
    UpdateExternalSystems UIS;
    UpdateExternalSystems UAS;


    /**
     * Creates new objects of each external system: register, printer, inventory system, accounting system and
     * assigns these to appropriate fields.
     */
    public SystemStartup(){
        this.register = new Register();
        this.printer = new Printer();
        this.inventorySystem = new InventorySystem();
        this.accountingSystem = new AccountingSystem();
        this.composite = new Composite();
        this.UIS = new UpdateInventorySystem(this);
        this.UAS = new UpdateAccountingSystem(this);
    }

    /**
     * Gets the register started by <code>systemStartup</code>.
     * @return The register started by <code>systemStartup</code>.
     */
    public Register getRegister() {
        return register;
    }

    /**
     * Gets the printer started by <code>systemStartup</code>.
     * @return The printer started by <code>systemStartup</code>.
     */
    public Printer getPrinter() {
        return printer;
    }

    /**
     * Gets the inventory system started by <code>systemStartup</code>.
     * @return The inventory system started by <code>systemStartup</code>.
     */
    public InventorySystem getInventorySystem() {
        return inventorySystem;
    }

    /**
     * Gets the accounting system started by <code>systemStartup</code>.
     * @return The accounting system started by <code>systemStartup</code>.
     */
    public AccountingSystem getAccountingSystem(){
        return accountingSystem;
    }

    /**
     * Gets the <code>UpdateInventorySystem</code> started by <code>systemStartup</code>.
     * @return The object holding a <code>UpdateInventorySystem</code> started by <code>systemStartup</code>.
     */
    public UpdateExternalSystems getUIS(){
        return UIS;
    }

}

