package dev.mikicit.darkforest;

import dev.mikicit.darkforest.core.Config;
import dev.mikicit.darkforest.core.StateManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.LogManager;

/**
 * The type Game.
 * <p>
 * Start point of the game.
 */
public class Game extends Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
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
        // loading main config
        Config.init("config.json");

        // setting up stage
        stage.setTitle(Config.getWindowName());
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("icon/icon_32.png"));

        // init state manager
        StateManager.init(stage);
    }
}
