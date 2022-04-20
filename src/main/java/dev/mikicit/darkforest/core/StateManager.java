package dev.mikicit.darkforest.core;

import dev.mikicit.darkforest.controller.AController;
import dev.mikicit.darkforest.controller.GameController;
import dev.mikicit.darkforest.controller.MainMenuController;
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
        states.put("GAME", new GameController());
        states.put("MENU", new MainMenuController());

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
        System.out.println(currentController.getView().getScene());
        stage.setScene(currentController.getView().getScene());
    }

    public static void goToMainMenu() {
        currentController = states.get("MENU");
        System.out.println(currentController.getView().getScene());
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
