package org.bankingapp.Controllers.Admin;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import org.bankingapp.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane adminParent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, s, t1) -> {
            switch (t1) {
                case CLIENTS -> adminParent.setCenter(Model.getInstance().getViewFactory().getClientsView());
                case DEPOSIT -> adminParent.setCenter(Model.getInstance().getViewFactory().getDepositView());
                default -> adminParent.setCenter(Model.getInstance().getViewFactory().getCreateClientView());
            }
        });
    }
}
