package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.PlayerConfig;
import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.model.entity.Player;
import dev.mikicit.darkforest.view.GameMenuView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GameMenuController extends AController {
    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
        view = new GameMenuView(this);
        view.init();
    }

    // Event Handlers
    public void keyPressedHandler(KeyEvent e) {
        String code = e.getCode().toString();

        if (code.equals("ESCAPE")) {
            StateManager.continueGame();
        }
    }

    public void gameContinueButtonClickHandler(MouseEvent e) {
        StateManager.continueGame();
    }

    public void gameSaveButtonClickHandler(MouseEvent e) {
        PlayerConfig.savePlayerConfig();
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
