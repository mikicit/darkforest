package dev.mikita.darkforest.controller;

import dev.mikita.darkforest.core.StateManager;
import dev.mikita.darkforest.view.MainMenuView;
import javafx.scene.input.MouseEvent;

/**
 * The type Main menu controller.
 */
public class MainMenuController extends AController {
    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
        view = new MainMenuView(this);
        view.init();
    }

    /**
     * Game load button click handler.
     *
     * @param e The event.
     */
    public void gameLoadButtonClickHandler(MouseEvent e) {
        StateManager.startGame(true);
    }

    /**
     * Game start button click handler.
     *
     * @param e The event.
     */
    public void gameStartButtonClickHandler(MouseEvent e) {
        StateManager.startGame(false);
    }

    /**
     * Exit game button click handler.
     *
     * @param e The event.
     */
    public void exitGameButtonClickHandler(MouseEvent e) {
        StateManager.exitGame();
    }

    @Override
    public void tick(double delta) {
        view.render();
    }
}
