package dev.mikicit.game.core;

import java.util.HashMap;
import dev.mikicit.game.controller.AController;
import dev.mikicit.game.controller.GameController;
import dev.mikicit.game.controller.MainMenuController;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class StateManager {
    private static HashMap<String, AController> states = new HashMap<>();
    private static AController currentController;
    private static Stage stage;
    private static GameLoop gameLoop;

    public static void init(Stage stage) {
        StateManager.stage = stage;

        // Init States
        states.put("GAME", new GameController());
        states.put("MENU", new MainMenuController());
        currentController = states.get("MENU");

        // Init Game Loop
        StateManager.gameLoop = new GameLoop(currentController);
        System.out.println(currentController.getView().getScene());

        stage.setScene(currentController.getView().getScene());

        // Open and Start game
        stage.show();
        startLoop();
    }

    public static void goToMainMenu() {
        currentController = states.get("MENU");
        stage.setScene(currentController.getView().getScene());
    }

    public static void goToGame() {
        currentController = states.get("GAME");
        stage.setScene(currentController.getView().getScene());
    }

    public static void startLoop() {
        gameLoop.start();
    }

    public static void stopLoop() {
        gameLoop.stop();
    }

    public static Stage getStage() {
        return stage;
    }
}


class GameLoop extends AnimationTimer {
    private long lastNanoTime = System.nanoTime();
    private AController controller;

    public GameLoop(AController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(long now) {
        double delta = (now - lastNanoTime) / 1000000000.0;
        lastNanoTime = now;

        controller.getView().update(delta);
        controller.getView().render();
    }
}