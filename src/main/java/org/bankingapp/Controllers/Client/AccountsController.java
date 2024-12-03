package org.bankingapp.Controllers.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {
    public Label checking_acc_num;
    public Label transaction_limit;
    public Label checking_acc_date;
    public Label checking_acc_balance;
    public Label savings_acc_num;
    public Label withdrawal_limit;
    public Label savings_acc_date;
    public Label savings_acc_balance;
    public TextField amount_to_savings;
    public Button transfer_to_savings_btn;
    public TextField amount_to_checking;
    public Button transfer_to_checking_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
