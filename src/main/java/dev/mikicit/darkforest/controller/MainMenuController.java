package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.view.MainMenuView;
import javafx.scene.input.MouseEvent;

public class MainMenuController extends AController {
    private boolean wasInitialized = false;

    public MainMenuController() {}

    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
        view = new MainMenuView(this);
    }

    public void gameLoadButtonClickHandler(MouseEvent e) {
        StateManager.goToGame(true);
    }

    public void gameStartButtonClickHandler(MouseEvent e) {
        StateManager.goToGame(false);
    }

    public void exitGameButtonClickHandler(MouseEvent e) {
        StateManager.exitGame();
    }

    @Override
    public void tick(double delta) {
        view.render();
    }
}
