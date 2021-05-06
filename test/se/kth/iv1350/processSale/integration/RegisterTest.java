package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {
    Register register;
    SystemStartup systemStartup;

    @BeforeEach
    void setUp() {
        this.systemStartup = new SystemStartup();
        this.register = systemStartup.getRegister();
    }

    @AfterEach
    void tearDown() {
        this.register = null;
        this.systemStartup = null;
    }

    @Test
    void testCreateRegister(){
        assertNotNull(register, "Register is not created correctly.");
    }

    @Test
    void testUpdateAmountInRegisterCalculatesChangeCorrectly() {
        double testAmountPaid = 100;
        Sale saleDetails = new Sale();
        double totalPriceForSale = 70;
        saleDetails.setTotalPriceForSale(totalPriceForSale);
        double totalVatPrice = 10;
        saleDetails.setTotalVatPrice(totalVatPrice);
        double testAmountChange = register.updateAmountInRegister(testAmountPaid, saleDetails);
        double expectedChange = testAmountPaid - (totalPriceForSale + totalVatPrice);
        assertEquals(expectedChange, testAmountChange, "Change is not calculated correctly. ");

    }
}