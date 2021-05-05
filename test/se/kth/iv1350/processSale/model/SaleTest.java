package se.kth.iv1350.processSale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.InventorySystem;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.Register;
import se.kth.iv1350.processSale.integration.SystemStartup;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    Controller controller;
    Sale saleDetails;
    SystemStartup systemStartup;
    InventorySystem inventorySystem;

    @BeforeEach
    void setUp() {
        this.controller = new Controller();
        this.saleDetails = new Sale();
        this.systemStartup = new SystemStartup();
        this.inventorySystem = systemStartup.getInventorySystem();

    }

    @AfterEach
    void tearDown() {
        controller = null;
        saleDetails = null;
        systemStartup = null;
        inventorySystem = null;
    }

    @Test
    void testCreateSale() {
        assertTrue(saleDetails instanceof Sale, "Sale object is not created correctly.");
    }

    @Test
    void testAddItemAddsItemQuantityToSale() {
        int quantity = 3;
        int itemIdentifier = 123456;

        ItemDTO itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity);
        assertEquals(quantity, saleDetails.getTotalItemQuantityInSale(), "Item quantity is not added to Sale object. ");
    }

    @Test
    void testAddItemAddsCorrectItem() {
        int quantity = 3;
        int itemIdentifier = 123456;

        ItemDTO itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity);
        String expectedItemName = "Gurka";

        assertEquals(expectedItemName, saleDetails.getItemListInSale().get(saleDetails.getItemListInSale().size() - 1).getItemName(), "ItemDTO is not correctly added to Sale object. ");
    }

    @Test
    void testAddItemCalculateTotalPrice() {
        int quantity = 3;
        int itemIdentifier = 123456;

        ItemDTO itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity);
        double expectedTotalPrice = itemDTO.getItemPrice() * quantity;

        assertEquals(expectedTotalPrice, saleDetails.getTotalPriceForSale(), "Item price is not calculated correctly. ");
    }

    @Test
    void testAddItemCalculateTotalVatPrice() {
        int quantity = 3;
        int itemIdentifier = 123456;

        ItemDTO itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity);
        double expectedTotalVatPrice = (12.0/100.0) * 7.0 * 3.0;

        assertEquals(expectedTotalVatPrice, saleDetails.getTotalVatPrice(), "Total VAT price is not calculated correctly. ");
    }

    @Test
    void testAddItemCalculateTotalPriceInclVat() {
        int quantity = 3;
        int itemIdentifier = 123456;

        ItemDTO itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity);
        double expectedTotalPriceInclVat = (12.0/100.0) * 7.0 * 3.0 + 7.0 * 3.0;

        assertEquals(expectedTotalPriceInclVat, saleDetails.getTotalPriceInclVat(), "Total price incl VAT is not calculated correctly. ");
    }

    @Test
    void testSetTotalPriceForSale() {
        saleDetails = new Sale();
        double testAmount = 10;
        saleDetails.setTotalPriceForSale(testAmount);
        assertEquals(saleDetails.getTotalPriceForSale(), testAmount, "Total price is not set correctly. ");
    }
}