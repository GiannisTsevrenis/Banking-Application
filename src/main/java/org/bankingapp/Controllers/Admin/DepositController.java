package org.bankingapp.Controllers.Admin;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    public TextField payeeAddressField;
    public Button searchButton;
    public ListView resultListView;
    public TextField amountField;
    public Button depositButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
