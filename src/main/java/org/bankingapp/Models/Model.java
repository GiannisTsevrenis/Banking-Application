package org.bankingapp.Models;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import org.bankingapp.Views.AccountType;
import org.bankingapp.Views.ViewFactory;

import java.lang.ref.Cleaner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Model {
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    //client data section
    private final Client client;
    private boolean clientLoginSuccessFlag;
    private final ObservableList<Transaction> latestTransactions;
    private final ObservableList<Transaction> allTransactions;
    //admin data section
    private boolean adminLoginSuccessFlag;
    private final ObservableList<Client> clients;

    private static final class ModelHolder {
        private static final Model model = new Model();
    }

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        //Client
        this.clientLoginSuccessFlag = false;
        this.client = new Client("", "", "", null, null, null);
        this.latestTransactions = FXCollections.observableArrayList();
        this.allTransactions = FXCollections.observableArrayList();
        //Admin
        this.adminLoginSuccessFlag = false;
        this.clients = FXCollections.observableArrayList();
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
                checkingAccount = getCheckingAccount(payeeAddress);
                savingsAccount = getSavingsAccount(payeeAddress);
                this.client.getCheckingAccount().set(checkingAccount);
                this.client.getSavingsAccount().set(savingsAccount);
                setClientLoginSuccessFlag(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareTransactions(ObservableList<Transaction> transactions, int limit) {
        ResultSet resultSet = databaseDriver.getTransactions(this.client.getPayeeAddress().get(), limit);
        try {
            while (resultSet.next()) {
                String sender = resultSet.getString("Sender");
                String receiver = resultSet.getString("Receiver");
                double amount = resultSet.getDouble("Amount");
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                String message = resultSet.getString("Message");
                transactions.add(new Transaction(sender, receiver, amount, date, message));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLatestTransactions() {
        prepareTransactions(this.latestTransactions, 4);
    }

    public ObservableList<Transaction> getLatestTransactions() {
        return latestTransactions;
    }

    public void setAllTransactions() { //TODO: implement pagination
        prepareTransactions(this.allTransactions, -1);
    }

    public ObservableList<Transaction> getAllTransactions() {
        return allTransactions;
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

    public ObservableList<Client> getClients() {
        return clients;
    }

    public void setClients() {
        CheckingAccount checkingAccount;
        SavingsAccount savingsAccount;
        ResultSet resultSet = databaseDriver.getAllClientsData();
        try {
            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String payeeAddress = resultSet.getString("PayeeAddress");
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                checkingAccount = getCheckingAccount(payeeAddress);
                savingsAccount = getSavingsAccount(payeeAddress);
                clients.add(new Client(firstName, lastName, payeeAddress, checkingAccount, savingsAccount, date));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Client> searchClient(String payeeAddress) {
        ObservableList<Client> searchResults = FXCollections.observableArrayList();
        ResultSet resultSet;
        try {
            resultSet = databaseDriver.searchClient(payeeAddress);
            CheckingAccount checkingAccount = getCheckingAccount(payeeAddress);
            SavingsAccount savingsAccount = getSavingsAccount(payeeAddress);
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String[] dateParts = resultSet.getString("Date").split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
            searchResults.add(new Client(firstName, lastName, payeeAddress, checkingAccount, savingsAccount, date));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchResults;
    }

    // common
    public CheckingAccount getCheckingAccount(String payeeAddress) {
        CheckingAccount checkingAccount = null;
        ResultSet resultSet = databaseDriver.getCheckingAccountData(payeeAddress);
        try {
            String accNumber = resultSet.getString("AccountNumber");
            double balance = resultSet.getDouble("Balance");
            int transactionLimit = (int) resultSet.getDouble("TransactionLimit");
            checkingAccount = new CheckingAccount(payeeAddress, accNumber, balance, transactionLimit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return checkingAccount;
    }

    public SavingsAccount getSavingsAccount(String payeeAddress) {
        SavingsAccount savingsAccount = null;
        ResultSet resultSet = databaseDriver.getSavingsAccountData(payeeAddress);
        try {
            String accNumber = resultSet.getString("AccountNumber");
            double balance = resultSet.getDouble("Balance");
            int withdrawalLimit = (int) resultSet.getDouble("WithdrawalLimit");
            savingsAccount = new SavingsAccount(payeeAddress, accNumber, balance, withdrawalLimit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return savingsAccount;
    }
}
