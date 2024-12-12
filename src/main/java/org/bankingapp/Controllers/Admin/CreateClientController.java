package org.bankingapp.Controllers.Admin;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.bankingapp.Models.Model;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
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
    private String payeeAddress;
    private boolean createCheckingAccountFlag = false;
    private boolean createSavingsAccountFlag = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createClientButton.setOnAction(event -> createClient());
        payeeAddressCheckBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue) {
                payeeAddress = createPayeeAddress();
                onCreatePayeeAddress();
            }
        });
        checkingAccountCheckBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if ((newValue)) {
                createCheckingAccountFlag = true;
            }
        });
        savingsAccountCheckBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if ((newValue)) {
                createSavingsAccountFlag = true;
            }
        });
    }

    private void createClient() {
        if (createCheckingAccountFlag) {
            createAccount("Checking");
        }
        if (createSavingsAccountFlag) {
            createAccount("Savings");
        }
        Model.getInstance().getDatabaseDriver().createClient(firstNameField.getText(), lastNameField.getText(), payeeAddress, passwordField.getText(), LocalDate.now());
        errorLabel.setText("Client created successfully.");
        emptyFields();
    }

    private void createAccount(String accountType) {
        double balance = Double.parseDouble(checkingAccountBalanceField.getText());
        String firstSection = "4321";
        String lastSection = Integer.toString((new Random()).nextInt(9000) + 1000);
        String accountNumber = firstSection + " " + lastSection;
        if (accountType.equals("Checking")) {
            Model.getInstance().getDatabaseDriver().createCheckingAccount(payeeAddress, accountNumber, 2500, balance);
        } else {
            Model.getInstance().getDatabaseDriver().createSavingsAccount(payeeAddress, accountNumber, 500, balance);
        }
    }

    private void onCreatePayeeAddress() {
        if (firstNameField.getText() != null & lastNameField.getText() != null) {
            payeeAddressLabel.setText(payeeAddress);
        }
    }

    private String createPayeeAddress() {
        int currentIncrement = Model.getInstance().getDatabaseDriver().getLastId() + 1;
        char firstCharacter = Character.toLowerCase(firstNameField.getText().charAt(0));
        return "@" + firstCharacter + lastNameField.getText() + currentIncrement;
    }

    private void emptyFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        passwordField.setText("");
        payeeAddressCheckBox.setSelected(false);
        payeeAddressLabel.setText("");
        checkingAccountCheckBox.setSelected(false);
        checkingAccountBalanceField.setText("");
        savingsAccountCheckBox.setSelected(false);
        savingsAccountBalanceField.setText("");
    }
}
