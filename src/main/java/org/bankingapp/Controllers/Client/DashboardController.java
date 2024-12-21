package org.bankingapp.Controllers.Client;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.bankingapp.Models.Model;
import org.bankingapp.Models.Transaction;
import org.bankingapp.Views.TransactionCellFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Text user_name;
    public Label login_date;
    public Label checking_bal;
    public Label checking_acc_num;
    public Label savings_bal;
    public Label savings_acc_num;
    public Label income_lbl;
    public Label expense_lbl;
    public ListView<Transaction> transaction_listview;
    public TextField payee_fld;
    public TextField amount_fld;
    public TextArea message_fld;
    public Button send_money_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();
        initLatestTransactionsList();
        transaction_listview.setItems(Model.getInstance().getLatestTransactions());
        transaction_listview.setCellFactory(e -> new TransactionCellFactory());
        send_money_btn.setOnAction(event -> onSendMoney());
    }

    private void bindData() {
        user_name.textProperty().bind(Bindings.concat("Hello, ").concat(Model.getInstance().getClient().getFirstName()));
        login_date.setText("Today, " + LocalDate.now());
        checking_bal.textProperty().bind(Model.getInstance().getClient().getCheckingAccount().get().getBalance().asString());
        checking_acc_num.textProperty().bind(Model.getInstance().getClient().getCheckingAccount().get().getAccountNumber());
        savings_bal.textProperty().bind(Model.getInstance().getClient().getSavingsAccount().get().getBalance().asString());
        savings_acc_num.textProperty().bind(Model.getInstance().getClient().getSavingsAccount().get().getAccountNumber());
    }

    private void initLatestTransactionsList() {
        if (Model.getInstance().getLatestTransactions().isEmpty()) {
            Model.getInstance().setLatestTransactions();
        }
    }

    private void onSendMoney() {
        String receiver = payee_fld.getText();
        double amount = Double.parseDouble(amount_fld.getText());
        String message = message_fld.getText();
        String sender = Model.getInstance().getClient().getPayeeAddress().get();
        ResultSet resultSet = Model.getInstance().getDatabaseDriver().searchClient(receiver);
        try {
            if (resultSet.isBeforeFirst()) {
                Model.getInstance().getDatabaseDriver().updateBalance(receiver, amount, "ADD");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Model.getInstance().getDatabaseDriver().updateBalance(sender, amount, "SUB");
        Model.getInstance().getClient().getSavingsAccount().get().setBalance(Model.getInstance().getDatabaseDriver().getSavingsAccountBalance(sender));
        Model.getInstance().getDatabaseDriver().newTransaction(sender, receiver, amount, message);
        payee_fld.setText("");
        amount_fld.setText("");
        message_fld.setText("");
    }
}
