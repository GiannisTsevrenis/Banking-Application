package org.bankingapp.Models;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SavingsAccount extends Account {
    private final DoubleProperty withdrawLimit;

    public SavingsAccount(String owner, String accountNumber, double balance, double withdrawLimit) {
        super(owner, accountNumber, balance);
        this.withdrawLimit = new SimpleDoubleProperty(this, "Transaction Limit", withdrawLimit);
    }

    public DoubleProperty getWithdrawLimit() {
        return withdrawLimit;
    }

    @Override
    public String toString() {
        return getAccountNumber().get();
    }
}
