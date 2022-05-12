package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.view.GameOverView;
import javafx.scene.input.MouseEvent;

public class GameOverController extends AController {
    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
        view = new GameOverView(this);
        view.init();
    }

    public void goToMainMenuButtonClickHandler(MouseEvent e) {
        StateManager.goToMainMenu();
    }

    @Override
    public void tick(double delta) {
        view.render();
    }
}
