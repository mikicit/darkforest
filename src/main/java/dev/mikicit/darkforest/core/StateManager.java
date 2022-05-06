package dev.mikicit.darkforest.core;

import dev.mikicit.darkforest.controller.*;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import java.util.HashMap;

public class StateManager {
    private static final HashMap<String, AController> states = new HashMap<>();
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

        // Set up current state
        currentController = states.get("MENU");

        // Init Game Loop
        StateManager.gameLoop = new GameLoop();

        // Init main Scene
        stage.setScene(currentController.getView().getScene());

        // Open and Start game
        stage.show();
        startLoop();
    }

    public static void goToGame() {
        currentController = states.get("GAME");
        stage.setScene(currentController.getView().getScene());
    }

    public static void goToMainMenu() {
        currentController = states.get("MENU");
        stage.setScene(currentController.getView().getScene());
    }

    public static void goToGameMenu() {
        currentController = states.get("GAME_MENU");
        stage.setScene(currentController.getView().getScene());
    }

    public static void goToInventory() {
        currentController = states.get("INVENTORY");
        stage.setScene(currentController.getView().getScene());
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
        lastNanoTime = now;

        StateManager.getCurrentState().tick(delta);
    }
}
