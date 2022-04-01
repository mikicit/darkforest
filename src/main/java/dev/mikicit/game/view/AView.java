package dev.mikicit.game.view;

import dev.mikicit.game.controller.AController;
import javafx.scene.Scene;

public abstract class AView {
    protected Scene scene;
    protected AController controller;

    public Scene getScene() {
        return scene;
    }

    abstract public void update(double delta);
    abstract public void render();
}
