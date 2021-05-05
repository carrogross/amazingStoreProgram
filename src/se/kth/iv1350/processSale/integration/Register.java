package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.model.Sale;

/**
 * Contains all communication with the cash register.
 */
public class Register {
    private double amountInRegister;

    Register(){
        this.amountInRegister = 100;
    }

    private void setAmountInRegister(double amountInRegister){
        this.amountInRegister = amountInRegister;
    }

    private double getAmountInRegister(){
        return amountInRegister;
    }
    /**
     * Updates the value of cash amount in register by adding the value of the total cost for the current sale and
     * calculates the amount of change that should be given back to the customer.
     * @param amountPaid The value of cash entered as payment for current sale.
     * @param saleDetails The object holding all information about <code>ItemDTO</code>s in current sale.
     * @return The value of cash that should be given to customer as change.
     */
    public double updateAmountInRegister(double amountPaid, Sale saleDetails){

        setAmountInRegister(amountInRegisterAfterPayment(saleDetails));
        return amountChange(amountPaid, saleDetails);
    }

    private double amountInRegisterAfterPayment(Sale saleDetails) {
        return getAmountInRegister() + saleDetails.getTotalPriceInclVat();
    }

    private double amountChange(double amountPaid, Sale saleDetails) {
        return amountPaid - saleDetails.getTotalPriceInclVat();
    }
}

