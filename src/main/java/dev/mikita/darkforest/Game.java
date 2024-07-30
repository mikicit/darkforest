package dev.mikita.darkforest;

import dev.mikita.darkforest.core.Config;
import dev.mikita.darkforest.core.StateManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.nio.file.Path;

/**
 * The type Game.
 * <p>
 * JavaFX's application class.
 */
public class Game extends Application {
    @Override
    public void start(Stage stage) {
        // loading main config
        Config.init("config/config.json");

        // setting up stage
        stage.setTitle(Config.getWindowName());
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(Path.of("config/icon/icon_32.png").toUri().toString()));

        // init state manager
        StateManager.init(stage);
    }
}
