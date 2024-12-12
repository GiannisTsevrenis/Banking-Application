package org.bankingapp.Controllers.Admin;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.bankingapp.Models.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label firstNameLabel;
    public Label lastNameLabel;
    public Label payeeAddressLabel;
    public Label checkingAccountLabel;
    public Label savingsAccountLabel;
    public Label dateLabel;
    public Button deleteButton;
    private final Client client;

    public ClientCellController(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameLabel.textProperty().bind(client.getFirstName());
        lastNameLabel.textProperty().bind(client.getLastName());
        payeeAddressLabel.textProperty().bind(client.getPayeeAddress());
        checkingAccountLabel.textProperty().bind(client.getCheckingAccount().asString());
        savingsAccountLabel.textProperty().bind(client.getSavingsAccount().asString());
        dateLabel.textProperty().bind(client.getDateCreated().asString());

    }
}
