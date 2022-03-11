package dev.mikicit.game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Start extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // Canvas
        Canvas canvas = new Canvas(2000, 2000);
        root.getChildren().add(canvas);

        // Config
        primaryStage.setTitle("RPG Game");
        primaryStage.show();
        primaryStage.setMaxWidth(500);
        primaryStage.setMaxHeight(500);
    }
}
