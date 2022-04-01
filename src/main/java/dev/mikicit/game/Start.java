package dev.mikicit.game;

import dev.mikicit.game.controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameController gameController = new GameController(primaryStage);
        gameController.startGame();
    }
}
