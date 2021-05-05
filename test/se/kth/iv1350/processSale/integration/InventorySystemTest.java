package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.model.Sale;

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
    void getItemDetailsCreatesItemDTOCorrectly() {
        int itemIdentifier = 123456;

        this.itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        String expectedItemName = "Gurka";

        assertEquals(expectedItemName, inventorySystem.itemDTO.getItemName(), "ItemDTO is not created correctly. ");

    }

}