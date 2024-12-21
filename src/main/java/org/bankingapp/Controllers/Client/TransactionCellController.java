package org.bankingapp.Controllers.Client;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.bankingapp.Models.Model;
import org.bankingapp.Models.Transaction;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
    public Label transactionDateLabel;
    public Label transactionPayeeLabel;
    public Label transactionReceiverLabel;
    public Label transactionAmountLabel;
    public FontAwesomeIconView arrowLeft;
    public FontAwesomeIconView arrowRight;
    private final Transaction transaction;

    public TransactionCellController(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transactionPayeeLabel.textProperty().bind(transaction.getSender());
        transactionReceiverLabel.textProperty().bind(transaction.getReceiver());
        transactionAmountLabel.textProperty().bind(transaction.amountProperty().asString());
        transactionDateLabel.textProperty().bind(transaction.getDate().asString());
        transactionIcons();
    }

    private void transactionIcons() {
        if (transaction.getSender().get().equals(Model.getInstance().getClient().getPayeeAddress().get())) {
            arrowLeft.setFill(Color.rgb(222, 222, 222));
            arrowRight.setFill(Color.RED);
        } else {
            arrowLeft.setFill(Color.GREEN);
            arrowRight.setFill(Color.rgb(222, 222, 222));
        }
    }
}
