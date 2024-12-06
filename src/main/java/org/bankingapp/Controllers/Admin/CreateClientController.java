package org.bankingapp.Controllers.Admin;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField passwordField;
    public CheckBox payeeAddressCheckBox;
    public Label payeeAddressLabel;
    public CheckBox checkingAccountCheckBox;
    public TextField checkingAccountBalanceField;
    public CheckBox savingsAccountCheckBox;
    public TextField savingsAccountBalanceField;
    public Button createClientButton;
    public Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
