package org.bankingapp.Models;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CheckingAccount extends Account {
    private final IntegerProperty transactionLimit;

    public CheckingAccount(String owner, String accountNumber, double balance, int transactionLimit) {
        super(owner, accountNumber, balance);
        this.transactionLimit = new SimpleIntegerProperty(this, "Transaction Limit", transactionLimit);
    }

    public IntegerProperty getTransactionLimit() {
        return transactionLimit;
    }

    @Override
    public String toString() {
        return getAccountNumber().get();
    }
}
