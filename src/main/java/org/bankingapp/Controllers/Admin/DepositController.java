package org.bankingapp.Controllers.Admin;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.bankingapp.Models.Client;
import org.bankingapp.Models.Model;
import org.bankingapp.Views.ClientCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    public TextField payeeAddressField;
    public Button searchButton;
    public ListView<Client> resultListView;
    public TextField amountField;
    public Button depositButton;
    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchButton.setOnAction(event -> onClientSearch());
        depositButton.setOnAction(event -> onDeposit());
    }

    private void onClientSearch() {
        ObservableList<Client> searchResults = Model.getInstance().searchClient(payeeAddressField.getText());
        resultListView.setItems(searchResults);
        resultListView.setCellFactory(e -> new ClientCellFactory());
        client = searchResults.get(0);
    }

    private void onDeposit() {
        double amount = Double.parseDouble(amountField.getText());
        System.out.println("to be added " + amount);
        if (amountField.getText() != null) {
            double newBalance = amount + client.getSavingsAccount().get().getBalance().get();//huh??? this no good maan TODO
            System.out.println("currently in the bank: "+client.getSavingsAccount().get().getBalance().get());
            Model.getInstance().getDatabaseDriver().depositSavings(client.getPayeeAddress().get(), newBalance);
        }

        emptyFields();
    }

    private void emptyFields() {
        this.payeeAddressField.setText("");
        this.amountField.setText("");
    }
}
