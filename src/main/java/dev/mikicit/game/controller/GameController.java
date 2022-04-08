package dev.mikicit.game.controller;

import dev.mikicit.game.model.GameModel;
import dev.mikicit.game.view.AView;
import dev.mikicit.game.view.GameView;
import javafx.scene.Scene;
import java.util.ArrayList;

public class GameController extends AController{
    private final GameModel model = new GameModel();

    public GameController() {
        view = new GameView(this);
        initHandler();
    }

    public void initHandler() {
       Scene scene = view.getScene();

       scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            if (!input.contains(code)) {
                input.add(code);
            }
       });

       scene.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            input.remove(code);
       });
    }

    public AView getView() {
        return view;
    }

    public ArrayList<String> getInput() {
        return input;
    }

    public GameModel getGameModel() {
        return model;
    }
}