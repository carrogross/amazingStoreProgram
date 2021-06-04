package se.kth.iv1350.processSale.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


class ViewTest {
    View view;
    private ByteArrayOutputStream printBuffer;
    private PrintStream originalPrint;

    @BeforeEach
    void setUp() throws IOException {
        this.view = new View();
        changeStreamOutput();

    }

    @AfterEach
    void tearDown() {
        restoreStreamOutput();
        view = null;
    }

    @Test
    void testRunExampleSessionInitializeNewSalePrintOut() {
        view.runExampleSession();

        String printout = this.printBuffer.toString();
        String expected = "new sale";
        assertTrue(printout.contains(expected), "Message about new sale not printed correctly.");
    }

    @Test
    void testRunExampleSessionScanItemPrintOut() {
        view.runExampleSession();

        String printout = this.printBuffer.toString();
        String expected = "item";
        assertTrue(printout.contains(expected), "Message about scanned item not printed correctly.");
    }

    @Test
    void testRunExampleSessionCalculateChangePrintOut() {
        view.runExampleSession();

        String printout = this.printBuffer.toString();
        String expected = "Amount";
        assertTrue(printout.contains(expected), "Message about change amount not printed correctly.");
    }

    @Test
    void testRunExampleSessionRequestDiscountPrintOut() {
        view.runExampleSession();

        String printout = this.printBuffer.toString();
        String expected = "discount";
        assertTrue(printout.contains(expected), "Message about discount not printed correctly.");
    }

    @Test
    void testRunExampleSessionCatchOperationFailedExceptionPrintOut() {
        view.runExampleSession();

        String printout = this.printBuffer.toString();
        String expected = "wrong";
        assertTrue(printout.contains(expected), "Message about operation failure not printed correctly.");
    }

    @Test
    void testRunExampleSessionCatchInvalidIdentifierExceptionPrintOut() {
        view.runExampleSession();

        String printout = this.printBuffer.toString();
        String expected = "invalid";
        assertTrue(printout.contains(expected), "Message about invalid identifier not printed correctly.");
    }

    @Test
    void testRunExampleSessionEndSalePrintOut() {
        view.runExampleSession();

        String printout = this.printBuffer.toString();
        String expected = "TOTAL";
        assertTrue(printout.contains(expected), "Message about ended sale not printed correctly.");
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