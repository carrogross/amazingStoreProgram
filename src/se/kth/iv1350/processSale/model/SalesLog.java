package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.AccountingSystem;
import se.kth.iv1350.processSale.integration.InventorySystem;
import se.kth.iv1350.processSale.integration.ReceiptDTO;
import se.kth.iv1350.processSale.integration.SystemStartup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the sales log that logs all <code>ReceiptDTO</code>s after completed sale and
 * sends information to inventory system and accounting system.
 */
public class SalesLog {
    ArrayList<ReceiptDTO> salesLog;
    InventorySystem inventorySystem;
    AccountingSystem accountingSystem;
    TotalRevenueFileOutput totalRevenueFileOutput;
    private List<SaleObserver> saleObservers= new ArrayList<>();

    /**
     * Creates the sales log that each <code>ReceiptDTO</code> is logged in.
     */
    public SalesLog(SystemStartup systemStartup) throws IOException {
        salesLog = new ArrayList<>();
        this.inventorySystem = systemStartup.getInventorySystem();
        this.accountingSystem = systemStartup.getAccountingSystem();
        this.totalRevenueFileOutput = new TotalRevenueFileOutput();
    }

    /**
     * Logs all information about each performed sales in an array as <code>ReceiptDTO</code> and
     * sends all information about the sale to inventory system and accounting system.
     * @param receiptDTO The object that is added to the sales log.
     */
    public void logSale(ReceiptDTO receiptDTO){
        salesLog.add(receiptDTO);
        notifyObservers(receiptDTO);
        totalRevenueFileOutput.saleRevenue(receiptDTO.getSaleDetails().getTotalPriceInclVat());
        inventorySystem.updateInventorySystem(receiptDTO);
        accountingSystem.updateAccountingSystem(receiptDTO);
    }

    private void notifyObservers(ReceiptDTO receiptDTO) {
        for(SaleObserver saleObs : saleObservers){
            saleObs.saleRevenue(receiptDTO.getSaleDetails().getTotalPriceInclVat());
        }
    }

    /**
     * Adds the specified <code>SaleObserver</code> to the list of observers.
     * @param saleObserver The observer added to the list.
     */
    public void addSaleObserver(SaleObserver saleObserver){
        saleObservers.add(saleObserver);
    }

    /**
     * Adds all <code>SaleObserver</code>s to the list of observers.
     * @param saleObservers The list of observers added.
     */
    public void addSaleObservers(List<SaleObserver> saleObservers){
        saleObservers.addAll(saleObservers);
    }

    /**
     * Gets the array representing the sales log containing all <code>ReceiptDTO</code>s.
     * @return The array representing the sales log containing all <code>ReceiptDTO</code>s.
     */
    public ArrayList<ReceiptDTO> getSalesLog(){
        return salesLog;
    }
}
