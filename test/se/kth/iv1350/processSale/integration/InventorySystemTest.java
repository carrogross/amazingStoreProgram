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
/*
    @Test
    void getItemDetailsThrowsInvalidIdentifierException() throws InvalidIdentifierException, FailureDBReachException {
        int itemIdentifier = 111111;

        this.itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        assertThrows(InvalidIdentifierException );

    }

    @Test
    void getItemDetailsThrowsFailureDBReachException() throws InvalidIdentifierException, FailureDBReachException {
        int itemIdentifier = 666666;
        try {
            this.itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        } catch(FailureDBReachException e){

        }
        fail("FailureDBReachException was not thrown. ");
    }

 */

}