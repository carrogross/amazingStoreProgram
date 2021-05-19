package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventorySystemTest {
    SystemStartup systemStartup;
    InventorySystem inventorySystem;
    ItemDTO itemDTO;

    @BeforeEach
    void setUp() {
        this.systemStartup = new SystemStartup();
        this.inventorySystem = systemStartup.getInventorySystem();

    }

    @AfterEach
    void tearDown() {
        this.systemStartup = null;
        this.inventorySystem = null;
    }

    @Test
    void getItemDetailsCreatesItemDTOCorrectly() throws InvalidIdentifierException, FailureDBReachException {
        int itemIdentifier = 123456;

        this.itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        String expectedItemName = "Gurka";

        assertEquals(expectedItemName, inventorySystem.itemDTO.getItemName(), "ItemDTO is not created correctly. ");

    }

    @Test
    void getItemDetailsThrowsInvalidIdentifierException() {
        int itemIdentifier = 111111;
        InvalidIdentifierException invalidIdentifierException = assertThrows(
                InvalidIdentifierException.class, () -> inventorySystem.getItemDetails(itemIdentifier), "Did not throw expected exception"
        );

        assertTrue(invalidIdentifierException.getMessage().contains("Invalid"), "Exception did not contain expected message. ");
    }

    @Test
    void getItemDetailsThrowsFailureDBReachException() {
        int itemIdentifier = 666666;
        FailureDBReachException failureDBReachException = assertThrows(
                FailureDBReachException.class, () -> inventorySystem.getItemDetails(itemIdentifier), "Did not throw expected exception"
        );

        assertTrue(failureDBReachException.getMessage().contains("error"), "Exception did not contain expected message. ");
    }

}