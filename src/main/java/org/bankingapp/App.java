package org.bankingapp;
import javafx.application.Application;
import javafx.stage.Stage;
import org.bankingapp.Models.Model;


public class App extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}
