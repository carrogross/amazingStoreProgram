package se.kth.iv1350.processSale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.ReceiptDTO;
import se.kth.iv1350.processSale.integration.SystemStartup;

import static org.junit.jupiter.api.Assertions.*;


class SalesLogTest {
    Controller controller;
    SystemStartup systemStartup;
    SalesLog salesLog;

    @BeforeEach
    void setUp() {
        this.controller = new Controller();
        this.systemStartup = new SystemStartup();
        this.salesLog = new SalesLog(systemStartup);

    }

    @AfterEach
    void tearDown() {
        controller = null;
        systemStartup = null;
        salesLog = null;
    }

    @Test
    public void testLogSaleLogsReceiptDTO(){
        Sale saleDetails = new Sale();
        ReceiptDTO receiptDTO = new ReceiptDTO(saleDetails, 100, 20);
        salesLog.logSale(receiptDTO);
        int amountOfReceiptsInSalesLog = salesLog.salesLog.size();
        int expected = 1;
        assertEquals(expected, amountOfReceiptsInSalesLog, "ReceiptDTO is not added to salesLog correctly. ");
    }
}