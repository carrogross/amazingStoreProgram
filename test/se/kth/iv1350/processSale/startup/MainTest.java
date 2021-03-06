package se.kth.iv1350.processSale.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private ByteArrayOutputStream printBuffer;
    private PrintStream originalPrint;

    @BeforeEach
    void setUp() {
        changeStreamOutput();
    }

    @AfterEach
    void tearDown() {
        restoreStreamOutput();
    }

    @Test
    void testMainPrintOut() throws IOException {
        String[] args = null;
        Main.main(args);
        String printout = this.printBuffer.toString();
        String expected = "started";
        assertTrue(printout.contains(expected), "Message about program started not printed correctly.");
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