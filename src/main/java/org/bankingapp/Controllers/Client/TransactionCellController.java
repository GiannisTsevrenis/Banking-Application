package org.bankingapp.Controllers.Client;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    }
}
