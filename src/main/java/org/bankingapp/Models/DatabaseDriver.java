package org.bankingapp.Models;
import java.sql.*;

public class DatabaseDriver {
    private Connection connection;

    public DatabaseDriver() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:DATABASENAME.db");
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
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE PayeeAddress='" + payeeAddress + "' AND Password='" + password + "';");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
    //admin
    //common methods
}
