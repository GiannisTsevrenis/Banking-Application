package org.bankingapp.Models;
import javafx.scene.chart.PieChart;
import org.bankingapp.Views.AccountType;
import org.bankingapp.Views.ViewFactory;

import java.sql.ResultSet;
import java.time.LocalDate;

public class Model {
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private AccountType loginAccountType = AccountType.CLIENT;
    //client data section
    private Client client;
    private boolean clientLoginSuccessFlag;

    //admin data section
    private static final class ModelHolder {
        private static final Model model = new Model();
    }

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        //Client
        this.clientLoginSuccessFlag = false;
        this.client = new Client("", "", "", null, null, null);
        //Admin
    }

    public static synchronized Model getInstance() {
        return ModelHolder.model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return this.databaseDriver;
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }
    //client

    public Client getClient() {
        return client;
    }

    public boolean getClientLoginSuccessFlag() {
        return clientLoginSuccessFlag;
    }

    public void setClientLoginSuccessFlag(boolean flag) {
        this.clientLoginSuccessFlag = flag;
    }

    public void evaluateClientCredentials(String payeeAddress, String password) {
        CheckingAccount checkingAccount;
        SavingsAccount savingsAccount;
        ResultSet resultSet = databaseDriver.getClientData(payeeAddress, password);
        try {
            if (resultSet.isBeforeFirst()) {
                this.client.getFirstName().set(resultSet.getString("FirstName"));
                this.client.getLastName().set(resultSet.getString("LastName"));
                this.client.getPayeeAddress().set(resultSet.getString("PayeeAddress"));
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate localDate = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                this.client.getDateCreated().set(localDate);
                this.clientLoginSuccessFlag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
