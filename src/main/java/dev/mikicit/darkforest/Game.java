package dev.mikicit.darkforest;

import dev.mikicit.darkforest.core.Config;
import dev.mikicit.darkforest.core.PlayerConfig;
import dev.mikicit.darkforest.core.StateManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.LogManager;

public class Game extends Application {
    public static void main(String[] args) {
        // Logging Config
        try {
            LogManager.getLogManager().readConfiguration(
                Game.class.getResourceAsStream("/logger/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e);
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Config.init("config.json");

        stage.setTitle(Config.getWindowName());
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("icon/icon_32.png"));

        StateManager.init(stage);
    }
}
