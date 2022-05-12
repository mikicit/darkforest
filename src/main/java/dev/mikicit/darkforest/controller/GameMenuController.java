package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.PlayerConfig;
import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.view.GameMenuView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * The type Game menu controller.
 */
public class GameMenuController extends AController {
    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
        view = new GameMenuView(this);
        view.init();
    }

    /**
     * Key pressed handler.
     *
     * @param e the e
     */
    public void keyPressedHandler(KeyEvent e) {
        String code = e.getCode().toString();

        if (code.equals("ESCAPE")) {
            StateManager.continueGame();
        }
    }

    /**
     * Game continue button click handler.
     *
     * @param e the e
     */
    public void gameContinueButtonClickHandler(MouseEvent e) {
        StateManager.continueGame();
    }

    /**
     * Game save button click handler.
     *
     * @param e the e
     */
    public void gameSaveButtonClickHandler(MouseEvent e) {
        PlayerConfig.savePlayerConfig();
    }

    /**
     * To main menu button click handler.
     *
     * @param e the e
     */
    public void toMainMenuButtonClickHandler(MouseEvent e) {
        StateManager.goToMainMenu();
    }

    /**
     * Exit game button click handler.
     *
     * @param e the e
     */
    public void exitGameButtonClickHandler(MouseEvent e) {
        StateManager.exitGame();
    }

    @Override
    public void tick(double delta) {

    }
}
