package org.bankingapp.Controllers.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.bankingapp.Models.Model;
import org.bankingapp.Models.Transaction;
import org.bankingapp.Views.TransactionCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    public ListView<Transaction> transactions_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAllTransactions();
        transactions_listview.setItems(Model.getInstance().getAllTransactions());
        transactions_listview.setCellFactory(e -> new TransactionCellFactory());
    }

    private void initAllTransactions() {
        if (Model.getInstance().getAllTransactions().isEmpty()) {
            Model.getInstance().setAllTransactions();
        }
    }
}
