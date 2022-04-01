package dev.mikicit.game.controller;

import dev.mikicit.game.view.AView;
import dev.mikicit.game.view.MainMenuView;

public class MainMenuController extends AController {
    public MainMenuController() {
        view = new MainMenuView(this);
    }

    public AView getView() {
        return view;
    }
}
