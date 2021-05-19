package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the composite design pattern and is responsible for adding and performing tasks, in this case
 * updating external systems.
 */
public class Composite implements UpdateExternalSystems{
    private List<UpdateExternalSystems> updateExternalSystemsList;

    /**
     * Creates a new instance of Composite and creates the list that is <code>updateExternalSystemsList</code>.
     */
    public Composite(){
        this.updateExternalSystemsList = new ArrayList<>();
    }

    /**
     * Performs the tasks collected in the <code>updateExternalSystemsList</code>.
     * @param receiptDTO The object holding all information about the current sale.
     */
    @Override
    public void updateSystems(ReceiptDTO receiptDTO) {
        for(UpdateExternalSystems updateExternalSystems : updateExternalSystemsList)
            updateExternalSystems.updateSystems(receiptDTO);
    }

    void addTask(UpdateInventorySystem UIS, UpdateAccountingSystem UAS){
        updateExternalSystemsList.add(UIS);
        updateExternalSystemsList.add(UAS);
    }
}
