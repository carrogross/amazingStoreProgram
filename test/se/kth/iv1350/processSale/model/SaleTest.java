package se.kth.iv1350.processSale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.*;
import se.kth.iv1350.processSale.util.FileLogger;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    Controller controller;
    Sale saleDetails;
    SystemStartup systemStartup;
    InventorySystem inventorySystem;

    @BeforeEach
    void setUp() throws IOException {
        this.controller = new Controller(new FileLogger());
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
        assertNotNull(saleDetails, "Sale object is not created correctly.");
    }

    @Test
    void testAddItemAddsItemQuantityToSale() throws InvalidIdentifierException, FailureDBReachException {
        int quantity = 3;
        int itemIdentifier = 123456;

        ItemDTO itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity);
        assertEquals(quantity, saleDetails.getTotalItemQuantityInSale(), "Item quantity is not added to Sale object. ");
    }

    @Test
    void testAddItemAddsCorrectItem() throws InvalidIdentifierException, FailureDBReachException {
        int quantity = 3;
        int itemIdentifier = 123456;

        ItemDTO itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity);
        String expectedItemName = "Gurka";

        assertEquals(expectedItemName, saleDetails.getItemListInSale().get(saleDetails.getItemListInSale().size() - 1).getItemName(), "ItemDTO is not correctly added to Sale object. ");
    }

    @Test
    void testAddItemAlreadyScanned() throws InvalidIdentifierException, FailureDBReachException {
        int quantity1 = 3;
        int itemIdentifier = 123456;

        ItemDTO itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity1);

        int quantity2 = 2;

        itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity2);

        int expectedQuantityOfDifferentItemsInSale = 1;

        assertEquals(expectedQuantityOfDifferentItemsInSale, saleDetails.getItemListInSale().size(), "Item is faultly added to Sale object. ");

    }

    @Test
    void testAddItemQuantityToTotalQuantityAlreadyScanned() throws InvalidIdentifierException, FailureDBReachException {
        int quantity1 = 3;
        int itemIdentifier = 123456;

        ItemDTO itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity1);

        int quantity2 = 2;

        itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity2);

        int expectedQuantityOfItemsInSale = quantity1 + quantity2;

        assertEquals(expectedQuantityOfItemsInSale, saleDetails.getTotalItemQuantityInSale(), "Item quantity of already scanned item is not added to total quantity variable. ");

    }

    @Test
    void testAddItemQuantityToQuantityListAlreadyScanned() throws InvalidIdentifierException, FailureDBReachException {
        int quantity1 = 3;
        int itemIdentifier = 123456;

        ItemDTO itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity1);

        int quantity2 = 2;

        itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity2);

        int expectedQuantityOfItemsInSale = quantity1 + quantity2;

        assertEquals(expectedQuantityOfItemsInSale, saleDetails.getItemQuantityListInSale().get(0), "Item quantity of already scanned item is not added to quantity list. ");

    }

    @Test
    void testAddItemCalculateTotalPrice() throws InvalidIdentifierException, FailureDBReachException {
        int quantity = 3;
        int itemIdentifier = 123456;

        ItemDTO itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity);
        double expectedTotalPrice = itemDTO.getItemPrice() * quantity;

        assertEquals(expectedTotalPrice, saleDetails.getTotalPriceForSale(), "Item price is not calculated correctly. ");
    }

    @Test
    void testAddItemCalculateTotalVatPrice() throws InvalidIdentifierException, FailureDBReachException {
        int quantity = 3;
        int itemIdentifier = 123456;

        ItemDTO itemDTO = inventorySystem.getItemDetails(itemIdentifier);
        saleDetails.addItem(itemDTO, quantity);
        double expectedTotalVatPrice = (12.0/100.0) * 7.0 * 3.0;

        assertEquals(expectedTotalVatPrice, saleDetails.getTotalVatPrice(), "Total VAT price is not calculated correctly. ");
    }

    @Test
    void testAddItemCalculateTotalPriceInclVat() throws InvalidIdentifierException, FailureDBReachException {
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