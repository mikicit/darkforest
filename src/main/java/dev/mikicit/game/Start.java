package dev.mikicit.game;

import dev.mikicit.game.controller.GameController;
import dev.mikicit.game.core.Config;
import dev.mikicit.game.core.StateManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(Config.getWindowName());
        stage.setResizable(false);
        stage.centerOnScreen();

        StateManager.init(stage);
    }
}
