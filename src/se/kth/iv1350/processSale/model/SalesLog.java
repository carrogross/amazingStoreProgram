package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.AccountingSystem;
import se.kth.iv1350.processSale.integration.InventorySystem;
import se.kth.iv1350.processSale.integration.ReceiptDTO;
import se.kth.iv1350.processSale.integration.SystemStartup;

import java.util.ArrayList;

/**
 * Logs all information about each performed sales in an array as <code>ReceiptDTO</code>.
 */
public class SalesLog {
    ArrayList<ReceiptDTO> salesLog;
    InventorySystem inventorySystem;
    AccountingSystem accountingSystem;

    /**
     * Creates the sales log that each <code>ReceiptDTO</code> is logged in.
     */
    public SalesLog(SystemStartup systemStartup){
        salesLog = new ArrayList<>();
        this.inventorySystem = systemStartup.getInventorySystem();
        this.accountingSystem = systemStartup.getAccountingSystem();
    }

    /**
     * Sends all information about the sale to inventory system and accounting system.
     * @param receiptDTO The object that is added to the sales log.
     */
    public void logSale(ReceiptDTO receiptDTO){
        salesLog.add(receiptDTO);
        inventorySystem.updateInventorySystem(receiptDTO);
        accountingSystem.updateAccountingSystem(receiptDTO);
    }

    public ArrayList<ReceiptDTO> getSalesLog(){
        return salesLog;
    }
}
