package dev.mikicit.game.controller;

import dev.mikicit.game.model.GameModel;
import dev.mikicit.game.view.AView;
import dev.mikicit.game.view.GameView;

public class GameController extends AController{
    private GameModel gameModel = new GameModel();

    public GameController() {
        view = new GameView(this);
    }

    public AView getView() {
        return view;
    }

    public GameModel getGameModel() {
        return gameModel;
    }
}