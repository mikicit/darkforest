package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.view.MainMenuView;
import javafx.scene.input.MouseEvent;

public class MainMenuController extends AController {
    public MainMenuController() {
        view = new MainMenuView(this);
    }

    public void gameStartButtonClickHandler(MouseEvent e) {
        StateManager.goToGame();
    }

    @Override
    public void tick(double delta) {
        view.render();
    }
}
