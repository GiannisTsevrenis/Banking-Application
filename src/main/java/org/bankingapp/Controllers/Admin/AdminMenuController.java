package org.bankingapp.Controllers.Admin;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.bankingapp.Models.Model;
import org.bankingapp.Views.AdminMenuOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button create_client_btn;
    public Button clients_list_btn;
    public Button deposit_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        create_client_btn.setOnAction(event -> onCreateClient());
        clients_list_btn.setOnAction(event -> onClients());
        deposit_btn.setOnAction(event -> onDeposit());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onCreateClient() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CREATE_CLIENT);
    }

    private void onClients() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CLIENTS);
    }

    private void onDeposit() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.DEPOSIT);
    }

    private void onLogout() {
        Stage stage = (Stage) create_client_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        //on log out show log in window
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().setAdminLoginSuccessFlag(false);
    }
}
