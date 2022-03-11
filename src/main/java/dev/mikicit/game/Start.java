package dev.mikicit.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Start extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(500, 500);

        StackPane holder = new StackPane();
        holder.getChildren().add(canvas);
        root.getChildren().add(holder);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        holder.setStyle("-fx-background-color: green");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RPG Game");
        primaryStage.setMaxWidth(500);
        primaryStage.setMaxHeight(500);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        // Init Player
        Player player = new Player("God");

        // Events
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();

                if ("UP".equals(code)) {
                    player.moveUp();
                } else if ("DOWN".equals(code)) {
                    player.moveDown();
                }
            }
        });


        // Game Loop
//        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, 500,500);

                gc.drawImage(player.sprite.getFrame(), player.getX(), player.getY());
            }
        }.start();

        // Open Window
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
