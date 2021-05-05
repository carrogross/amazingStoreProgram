package se.kth.iv1350.processSale.integration;

/**
 * -------------------
 */
public class SystemStartup {
    Register register;
    Printer printer;
    InventorySystem inventorySystem;
    AccountingSystem accountingSystem;
    /**
     * ----------------------
     * ska vara public SystemStartup bootIntegration() och returnera systemStartup
     */
    public SystemStartup(){
        this.register = new Register();
        this.printer = new Printer();
        this.inventorySystem = new InventorySystem();
        this.accountingSystem = new AccountingSystem();
    }

    public Register getRegister() {
        return register;
    }

    public Printer getPrinter() {
        return printer;
    }

    public InventorySystem getInventorySystem() {
        return inventorySystem;
    }

    public AccountingSystem getAccountingSystem(){
        return accountingSystem;
    }
}
