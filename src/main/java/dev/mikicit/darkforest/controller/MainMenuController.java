package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.view.MainMenuView;
import javafx.scene.input.MouseEvent;

public class MainMenuController extends AController {
    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
        view = new MainMenuView(this);
        view.init();
    }

    public void gameLoadButtonClickHandler(MouseEvent e) {
        StateManager.startGame(true);
    }

    public void gameStartButtonClickHandler(MouseEvent e) {
        StateManager.startGame(false);
    }

    public void exitGameButtonClickHandler(MouseEvent e) {
        StateManager.exitGame();
    }

    @Override
    public void tick(double delta) {
        view.render();
    }
}
