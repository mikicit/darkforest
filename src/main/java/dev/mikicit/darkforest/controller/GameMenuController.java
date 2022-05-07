package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.view.GameMenuView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GameMenuController extends AController {
    private boolean wasInitialized = false;

    public GameMenuController() {}

    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
        view = new GameMenuView(this);
    }

    // Event Handlers
    public void keyPressedHandler(KeyEvent e) {
        String code = e.getCode().toString();

        if (code.equals("ESCAPE")) {
            StateManager.goToGame();
        }
    }

    public void gameContinueButtonClickHandler(MouseEvent e) {
        StateManager.goToGame();
    }

    public void toMainMenuButtonClickHandler(MouseEvent e) {
        StateManager.goToMainMenu();
    }

    public void exitGameButtonClickHandler(MouseEvent e) {
        StateManager.exitGame();
    }

    @Override
    public void tick(double delta) {

    }
}
