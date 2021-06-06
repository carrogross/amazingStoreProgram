package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.FileLogger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


class PrinterTest {
    Controller controller;
    SystemStartup systemStartup;
    Printer printer;
    private ByteArrayOutputStream printBuffer;
    private PrintStream originalPrint;



    @BeforeEach
    void setUp() throws IOException {
        changeStreamOutput();
        this.controller = new Controller(new FileLogger());
        this.systemStartup = new SystemStartup();
        this.printer = systemStartup.getPrinter();

    }

    @AfterEach
    void tearDown() {
        controller = null;
        systemStartup = null;
        printer = null;
        restoreStreamOutput();
    }

    @Test
    void Printer() {
    }

    @Test
    public void testReceiptPrinted() {
        Sale saleDetails = new Sale();
        ReceiptDTO receiptDTO = new ReceiptDTO(saleDetails, 100, 20);
        printer.printReceipt(receiptDTO);
        String printout = this.printBuffer.toString();
        String expected = "RECEIPT";
        assertTrue(printout.contains(expected), "Receipt not printed correctly.");
    }

    private void changeStreamOutput(){
        printBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printBuffer);
        originalPrint = System.out;
        System.setOut(inMemSysOut);
    }

    private void restoreStreamOutput(){
        printBuffer = null;
        System.setOut(originalPrint);
    }
}