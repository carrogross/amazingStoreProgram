package se.kth.iv1350.processSale.controller;

import org.junit.jupiter.api.*;
import se.kth.iv1350.processSale.integration.Register;
import se.kth.iv1350.processSale.integration.SystemStartup;
import se.kth.iv1350.processSale.model.Sale;


class ControllerTest {
    private Controller controller;
    private Sale saleDetails;


    @BeforeEach
    void setUp() {
        this.controller = new Controller();
    }

    @AfterEach
    void tearDown() {
        controller = null;
    }

    @Test
    void testCreateController(){
        Assertions.assertTrue(controller instanceof Controller, "A new Controller object is not created. ");
    }

    @Test
    void testInitializeSaleCreatesNewSale() {
        controller.initializeSale();
        Assertions.assertTrue(controller.saleDetails instanceof Sale, "A new Sale object is not created. ");
    }

    @Test
    void testScanItemAddsNewItemToSale(){
        controller.initializeSale();
        int quantity = 2;
        int itemIdentifier = 123456;

        saleDetails = controller.scanItem(quantity, itemIdentifier);
        int expectedQuantity = 1;
        Assertions.assertEquals(expectedQuantity, saleDetails.getItemListInSale().size(), "Item is not added to Sale object. ");
    }

    @Test
    void testScanItemZeroQuantityEntered(){
        controller.initializeSale();
        int quantity = 0;
        int itemIdentifier = 123456;

        saleDetails = controller.scanItem(quantity, itemIdentifier);
        int expectedQuantity = 1;
        Assertions.assertEquals(expectedQuantity, saleDetails.getItemListInSale().size(), "Zero quantity not registered as 1. ");

    }

    @Test
    void testScanItemAddsItemQuantityToSale() {
        controller.initializeSale();
        int quantity = 3;
        int itemIdentifier = 123456;

        saleDetails = controller.scanItem(quantity, itemIdentifier);
        int expectedQuantity = quantity;
        Assertions.assertEquals(expectedQuantity, saleDetails.getTotalItemQuantityInSale(), "Item quantity is not added to Sale object. ");
    }

    @Test
    void testScanItemAddsCorrectItem() {
        controller.initializeSale();
        int quantity = 3;
        int itemIdentifier = 123456;

        saleDetails = controller.scanItem(quantity, itemIdentifier);
        String expectedItemName = "Gurka";

        Assertions.assertEquals(expectedItemName, saleDetails.getItemListInSale().get(saleDetails.getItemListInSale().size() - 1).getItemName(), "ItemDTO is not correctly added to Sale object. ");
    }

    @Test
    void TestEndSaleCalculatesTotalPriceInclVat() {
        Sale saleDetails = new Sale();
        double testAmountTotalPrice = 10;
        double testAmountVatPrice = 3;
        saleDetails.setTotalPriceForSale(testAmountTotalPrice);
        saleDetails.setTotalVatPrice(testAmountVatPrice);

        double testAmountTotalPriceInclVat = testAmountTotalPrice + testAmountVatPrice;
        Assertions.assertEquals(saleDetails.getTotalPriceInclVat(), testAmountTotalPriceInclVat, "Total price incl VAT is not calculated correctly. ");
    }

    @Test
    void testCalculateChangeTooLowAmountPaidEntered(){

    }

    @Test
    void testCalculateChangeLogsSale(){
        controller.initializeSale();
        double amountPaid = 100;
        controller.calculateChange(amountPaid);
        int expectedLoggedSales = 1;
        int amountLoggedSales = controller.salesLog.getSalesLog().size();
        Assertions.assertEquals(expectedLoggedSales, amountLoggedSales, "The sale is not logged correctly. ");

    }

    @Test
    void testCalculateChangeCalculatesChangeCorrectly(){
        SystemStartup systemStartup = new SystemStartup();
        Register register = systemStartup.getRegister();
        double testAmountPaid = 100;
        Sale saleDetails = new Sale();
        double totalPriceForSale = 70;
        saleDetails.setTotalPriceForSale(totalPriceForSale);
        double totalVatPrice = 10;
        saleDetails.setTotalVatPrice(totalVatPrice);
        double testAmountChange = register.updateAmountInRegister(testAmountPaid, saleDetails);
        double expectedChange = testAmountPaid - (totalPriceForSale + totalVatPrice);
        Assertions.assertEquals(expectedChange, testAmountChange, "Change is not calculated correctly. ");
    }

    @Test
    void testCalculateChangeReturnsChange(){
        controller.initializeSale();
        int quantity = 3;
        int itemIdentifier = 123456;
        saleDetails = controller.scanItem(quantity, itemIdentifier);

        double totalPriceForSale = 50;
        saleDetails.setTotalPriceForSale(totalPriceForSale);
        double totalVatPrice = 10;
        saleDetails.setTotalVatPrice(totalVatPrice);
        double amountPaid = 100;
        double calculatedChange = controller.calculateChange(amountPaid);
        double expectedChange = amountPaid - (totalPriceForSale + totalVatPrice);
        Assertions.assertEquals(expectedChange, calculatedChange, "Change is not returned correctly. ");
    }

    @Test
    void requestDiscount() {
    }
}