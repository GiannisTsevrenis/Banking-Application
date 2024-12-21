package org.bankingapp.Models;
import java.sql.*;
import java.time.LocalDate;

public class DatabaseDriver {
    private Connection connection;

    public DatabaseDriver() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:bankDB.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //client
    public ResultSet getClientData(String payeeAddress, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connection.createStatement();
            //TODO prone to sql injection fix later
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE PayeeAddress='" + payeeAddress + "' AND Password='" + password + "';");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet getTransactions(String payeeAddress, int limit) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM Transactions WHERE Sender = ? OR Receiver = ? LIMIT ?";
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, payeeAddress);
            preparedStatement.setString(2, payeeAddress);
            preparedStatement.setString(3, String.valueOf(limit));
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public double getSavingsAccountBalance(String payeeAddress) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        double balance = 0;
        String query = "SELECT * FROM SavingsAccounts WHERE Owner = ?";
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, payeeAddress);
            resultSet = preparedStatement.executeQuery();
            balance = resultSet.getDouble("Balance");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return balance;
    }

    public void updateBalance(String payeeAddress, double amount, String operation) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM SavingsAccounts WHERE Owner = ?";
        String updateQuery = "UPDATE SavingsAccounts SET Balance = ? WHERE Owner = ?";
        try {
            // Retrieve the current balance
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, payeeAddress);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double balance = resultSet.getDouble("Balance");
                double newBalance;
                if (operation.equals("ADD")) {
                    newBalance = balance + amount;
                } else {
                    if (balance >= amount) {
                        newBalance = balance - amount;
                    } else {
                        return;
                    }
                }
                preparedStatement = this.connection.prepareStatement(updateQuery);
                preparedStatement.setDouble(1, newBalance);
                preparedStatement.setString(2, payeeAddress);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void newTransaction(String sender, String receiver, double amount, String message) {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO Transactions (Sender, Receiver, Amount, Date, Message) VALUES (?, ?, ?, ?, ?)";
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, sender);
            preparedStatement.setString(2, receiver);
            preparedStatement.setString(3, String.valueOf(amount));
            preparedStatement.setString(4, String.valueOf(LocalDate.now()));
            preparedStatement.setString(5, message);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //admin
    public ResultSet getAdminData(String username, String password) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM Admins WHERE Username = ? AND Password = ?";
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public void createClient(String firstName, String lastName, String address, String password, LocalDate date) {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO Clients (FirstName, LastName, PayeeAddress, Password, Date) VALUES (?, ?, ?, ?, ?)";
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, String.valueOf(date));
            preparedStatement.executeUpdate();
//            int rowsInserted = preparedStatement.executeUpdate();
//            if (rowsInserted > 0) {
//                System.out.println("success");
//
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createCheckingAccount(String owner, String number, double transactionLimit, double balance) {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO CheckingAccounts (Owner, AccountNumber, TransactionLimit, Balance) VALUES (?, ?, ?, ?)";
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, owner);
            preparedStatement.setString(2, number);
            preparedStatement.setString(3, String.valueOf(transactionLimit));
            preparedStatement.setString(4, String.valueOf(balance));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createSavingsAccount(String owner, String number, double withdrawalLimit, double balance) {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO SavingsAccounts (Owner, AccountNumber, WithdrawalLimit, Balance) VALUES (?, ?, ?, ?)";
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, owner);
            preparedStatement.setString(2, number);
            preparedStatement.setString(3, String.valueOf(withdrawalLimit));
            preparedStatement.setString(4, String.valueOf(balance));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getAllClientsData() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM Clients";
        try {
            preparedStatement = this.connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public void depositSavings(String payeeAddress, double amount) {
        PreparedStatement preparedStatement;
        String query = "UPDATE SavingsAccounts SET Balance = ? WHERE Owner = ?";
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(amount));
            preparedStatement.setString(2, payeeAddress);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //common methods
    public ResultSet searchClient(String payeeAddress) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM Clients WHERE PayeeAddress = ?";
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, payeeAddress);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public int getLastId() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM sqlite_sequence WHERE name = 'Clients'";
        int id = 0;
        try {
            preparedStatement = this.connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            // if resultSet.next() ?
            id = resultSet.getInt("seq");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public ResultSet getCheckingAccountData(String payeeAddress) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM CheckingAccounts WHERE Owner = ?";
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, payeeAddress);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet getSavingsAccountData(String payeeAddress) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM SavingsAccounts WHERE Owner = ?";
        try {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, payeeAddress);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}
