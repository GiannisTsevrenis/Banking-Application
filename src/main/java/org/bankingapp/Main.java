package org.bankingapp;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.FONT);
        icon.setSize("24");
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