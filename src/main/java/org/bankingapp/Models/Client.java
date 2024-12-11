package org.bankingapp.Models;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Client {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty payeeAddress;
    private final ObjectProperty<Account> checkingAccount;
    private final ObjectProperty<Account> savingsAccount;
    private final ObjectProperty<LocalDate> dateCreated;

    public Client(String firstName, String lastName, String payeeAddress, Account checkingAccount, Account savingsAccount, LocalDate dateCreated) {
        this.firstName = new SimpleStringProperty(this, "First Name", firstName);
        this.lastName = new SimpleStringProperty(this, "Last Name", lastName);
        this.payeeAddress = new SimpleStringProperty(this, "Payee Address", payeeAddress);
        this.checkingAccount = new SimpleObjectProperty<>(this, "Checking Account", checkingAccount);
        this.savingsAccount = new SimpleObjectProperty<>(this, "Savings Account", savingsAccount);
        this.dateCreated = new SimpleObjectProperty<>(this, "Date", dateCreated);
    }

    public StringProperty getFirstName() {
        return firstName;
    }

    public StringProperty getLastName() {
        return lastName;
    }

    public StringProperty getPayeeAddress() {
        return payeeAddress;
    }

    public ObjectProperty<Account> getCheckingAccount() {
        return checkingAccount;
    }

    public ObjectProperty<Account> getSavingsAccount() {
        return savingsAccount;
    }

    public ObjectProperty<LocalDate> getDateCreated() {
        return dateCreated;
    }
}
