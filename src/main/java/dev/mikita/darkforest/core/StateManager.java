package dev.mikita.darkforest.core;

import dev.mikita.darkforest.controller.*;
import dev.mikita.darkforest.model.GameModel;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;

/**
 * The type State manager.
 * <p>
 * The main class for managing the state of the game
 * (switching between screens, managing a timer, etc.).
 */
@Slf4j
public class StateManager {
    /**
     * The enum game state.
     */
    private enum GameState {
        MENU,
        GAME_MENU,
        GAME,
        INVENTORY,
        GAME_OVER
    }

    /**
     * The states.
     */
    private static final HashMap<GameState, AController> states = new HashMap<>();

    /**
     * The current controller.
     */
    private static AController currentController;

    /**
     * The stage.
     */
    private static Stage stage;

    /**
     * The game loop.
     */
    private static GameLoop gameLoop;

    /**
     * Init.
     * <p>
     * Initialization method.
     *
     * @param stage The stage.
     */
    public static void init(Stage stage) {
        StateManager.stage = stage;

        // Init States
        states.put(GameState.MENU, new MainMenuController());
        states.put(GameState.GAME_MENU, new GameMenuController());
        states.put(GameState.GAME, new GameController());
        states.put(GameState.INVENTORY, new InventoryController());
        states.put(GameState.GAME_OVER, new GameOverController());

        // Init Game Loop
        StateManager.gameLoop = new GameLoop();

        // Initial State
        goToMainMenu();

        // Open and Start game
        stage.show();
        startLoop();
    }

    /**
     * Start game.
     * <p>
     * A method that starts a new game or a game from a save.
     *
     * @param fromSave The boolean value that indicates whether to start the game from the save.
     */
    public static void startGame(boolean fromSave) {
        currentController = states.get(GameState.GAME);

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

    /**
     * Continue game.
     * <p>
     * Method to return back to the game.
     */
    public static void continueGame() {
        currentController = states.get(GameState.GAME);
        currentController.init();
        stage.setScene(currentController.getView().getScene());

        log.info("Continue game.");
    }

    /**
     * Go to main menu.
     * <p>
     * The method to go to the main menu.
     */
    public static void goToMainMenu() {
        currentController = states.get(GameState.MENU);
        currentController.init();
        stage.setScene(currentController.getView().getScene());

        log.info("Go to the main menu.");
    }

    /**
     * Go to game menu.
     * <p>
     * The method to go to the game menu.
     */
    public static void goToGameMenu() {
        currentController = states.get(GameState.GAME_MENU);
        currentController.init();
        stage.setScene(currentController.getView().getScene());

        log.info("Go to the game menu.");
    }


    /**
     * Go to inventory.
     * <p>
     * The method of going to the inventory.
     */
    public static void goToInventory() {
        currentController = states.get(GameState.INVENTORY);
        currentController.init();
        stage.setScene(currentController.getView().getScene());

        log.info("Go to the inventory.");
    }

    /**
     * Game over.
     * <p>
     * Method that calls the "GameOver" window after the death of the character.
     */
    public static void gameOver() {
        currentController = states.get(GameState.GAME_OVER);
        currentController.init();
        stage.setScene(currentController.getView().getScene());

        log.info("Game Over.");
    }

    /**
     * Reset scene.
     * <p>
     * A helper method for updating the scene
     * (required, for example, when moving between portals).
     */
    public static void resetScene() {
        stage.setScene(currentController.getView().getScene());
    }

    /**
     * Exit game.
     * <p>
     * Exit the game (complete closing of the application).
     */
    public static void exitGame() {
        log.info("Exit Game.");

        Platform.exit();
        System.exit(0);
    }

    /**
     * Start loop.
     */
    public static void startLoop() {
        gameLoop.start();
    }

    /**
     * Stop loop.
     */
    public static void stopLoop() {
        gameLoop.stop();
    }

    /**
     * Gets current state.
     *
     * @return The current controller.
     */
    public static AController getCurrentState() {
        return currentController;
    }
}

/**
 * The type Game loop.
 * <p>
 * This class represents the control of the main timer.
 */
class GameLoop extends AnimationTimer {
    /**
     * The last nano time.
     */
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
