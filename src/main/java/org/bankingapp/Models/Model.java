package org.bankingapp.Models;
import javafx.scene.chart.PieChart;
import org.bankingapp.Views.AccountType;
import org.bankingapp.Views.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Model {
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    //client data section
    private final Client client;
    private boolean clientLoginSuccessFlag;
    //admin data section
    private boolean adminLoginSuccessFlag;

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
        this.adminLoginSuccessFlag = false;
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
                setClientLoginSuccessFlag(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //admin
    public boolean getAdminLoginSuccessFlag() {
        return this.adminLoginSuccessFlag;
    }

    public void setAdminLoginSuccessFlag(boolean flag) {
        this.adminLoginSuccessFlag = flag;
    }

    public void evaluateAdminCredentials(String username, String password) {
        ResultSet resultSet = databaseDriver.getAdminData(username, password);
        try {
            if (resultSet.isBeforeFirst()) {
                setAdminLoginSuccessFlag(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
