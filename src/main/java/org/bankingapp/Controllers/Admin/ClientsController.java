package org.bankingapp.Controllers.Admin;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.bankingapp.Models.Client;
import org.bankingapp.Models.Model;
import org.bankingapp.Views.ClientCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    public ListView<Client> clientsListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClientsList();
        clientsListView.setItems(Model.getInstance().getClients());
        clientsListView.setCellFactory(e -> new ClientCellFactory());
    }

    public void initClientsList() {
        if (Model.getInstance().getClients().isEmpty()) {
            Model.getInstance().setClients();
        }
    }
}
