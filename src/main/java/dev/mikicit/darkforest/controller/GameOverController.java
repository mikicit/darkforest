package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.view.GameOverView;
import javafx.scene.input.MouseEvent;

/**
 * The type Game over controller.
 */
public class GameOverController extends AController {
    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
        view = new GameOverView(this);
        view.init();
    }

    /**
     * Go to main menu button click handler.
     *
     * @param e the e
     */
    public void goToMainMenuButtonClickHandler(MouseEvent e) {
        StateManager.goToMainMenu();
    }

    @Override
    public void tick(double delta) {
        view.render();
    }
}
