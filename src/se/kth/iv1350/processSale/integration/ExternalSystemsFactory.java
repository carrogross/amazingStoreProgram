package se.kth.iv1350.processSale.integration;

/**
 * Class creates and bundles tasks inside a composite.
 */
public class ExternalSystemsFactory {
    Composite composite;

    /**
     * Creates a new instance of this class and bundles the composite.
     */
    public ExternalSystemsFactory(){
        this.composite = new Composite();
        composite.addTask(new UpdateInventorySystem(), new UpdateAccountingSystem());
    }

    /**
     * Returns the bundles composite.
     * @return The bundles composite.
     */
    public Composite getComposite(){
        return composite;
    }
}
