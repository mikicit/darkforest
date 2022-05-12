package dev.mikicit.darkforest.core;

import dev.mikicit.darkforest.controller.*;
import dev.mikicit.darkforest.model.GameModel;
import dev.mikicit.darkforest.view.GameView;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.util.HashMap;

/**
 * The type State manager.
 */
public class StateManager {
    private static HashMap<String, AController> states = new HashMap<>();
    private static AController currentController;
    private static Stage stage;
    private static GameLoop gameLoop;

    public static void init(Stage stage) {
        StateManager.stage = stage;

        // Init States
        states.put("MENU", new MainMenuController());
        states.put("GAME_MENU", new GameMenuController());
        states.put("GAME", new GameController());
        states.put("INVENTORY", new InventoryController());
        states.put("CHARACTER_INFO", new InventoryController());

        // Set up current state
        currentController = states.get("MENU");

        // Init Game Loop
        StateManager.gameLoop = new GameLoop();

        // Init main Scene
        goToMainMenu();

        // Open and Start game
        stage.show();
        startLoop();
    }

    public static void startGame(boolean fromSave) {
        currentController = states.get("GAME");

        // Reset All Controllers and Views (in case we start a new game after the game has already been played)
        states.forEach((key, value) -> {
            value.reset();
        });

        // Init Game Model
        GameModel gameModel = GameModel.getInstance();
        gameModel.init(fromSave);

        // Init Controller
        currentController.init();

        // Setting Scene
        stage.setScene(currentController.getView().getScene());
    }

    public static void continueGame() {
        currentController = states.get("GAME");
        currentController.init();
        stage.setScene(currentController.getView().getScene());
    }

    public static void goToMainMenu() {
        currentController = states.get("MENU");
        currentController.init();
        stage.setScene(currentController.getView().getScene());
    }

    public static void goToGameMenu() {
        currentController = states.get("GAME_MENU");
        currentController.init();
        stage.setScene(currentController.getView().getScene());
    }

    public static void goToCharacterInfo() {
        currentController = states.get("CHARACTER_INFO");
        currentController.init();
        stage.setScene(currentController.getView().getScene());
    }

    public static void goToInventory() {
        currentController = states.get("INVENTORY");
        currentController.init();
        stage.setScene(currentController.getView().getScene());
    }

    public static void resetScene() {
        stage.setScene(currentController.getView().getScene());
    }

    public static void exitGame() {
        Platform.exit();
        System.exit(0);
    }

    public static void startLoop() {
        gameLoop.start();
    }

    public static void stopLoop() {
        gameLoop.stop();
    }

    public static AController getCurrentState() {
        return currentController;
    }
}

// Game loop
class GameLoop extends AnimationTimer {
    private long lastNanoTime = System.nanoTime();

    @Override
    public void handle(long now) {
        double delta = (now - lastNanoTime) / 1000000000.0;

        // Frame rate cap
        if (delta > 1/60.00) {
            lastNanoTime = now;
            StateManager.getCurrentState().tick(delta);
        }
    }
}
