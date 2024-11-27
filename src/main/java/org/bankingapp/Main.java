package org.bankingapp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        FontIcon icon = new FontIcon("fa-home");
        icon.setIconSize(24);
        // Set up the UI
        Label label = new Label("Hello, World!");
        StackPane root = new StackPane();
        root.getChildren().add(icon);

        // Set up the scene
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("JavaFX Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}