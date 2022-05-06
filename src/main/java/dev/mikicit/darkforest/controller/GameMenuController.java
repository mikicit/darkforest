package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.view.GameMenuView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GameMenuController extends AController {
    public GameMenuController() {
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

    @Override
    public void tick(double delta) {

    }
}
