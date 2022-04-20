package dev.mikicit.darkforest;

import dev.mikicit.darkforest.core.Config;
import dev.mikicit.darkforest.core.StateManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Config.init("config.json");

        stage.setTitle(Config.getWindowName());
        stage.setResizable(false);
        stage.centerOnScreen();

        StateManager.init(stage);
    }
}
