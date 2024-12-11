package org.bankingapp.Views;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import org.bankingapp.Controllers.Admin.ClientCellController;
import org.bankingapp.Models.Client;

public class ClientCellFactory extends ListCell<Client> {
    @Override
    protected void updateItem(Client client, boolean b) {
        super.updateItem(client, b);
        if (b) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Client/TransactionCell.fxml"));
            ClientCellController controller = new ClientCellController(client);
            fxmlLoader.setController(controller);
            setText(null);
            try {
                setGraphic(fxmlLoader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
