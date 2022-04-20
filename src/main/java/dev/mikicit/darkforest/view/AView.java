package dev.mikicit.darkforest.view;

import dev.mikicit.darkforest.controller.AController;
import javafx.scene.Scene;

public abstract class AView {
    protected AController controller;
    protected Scene scene;

    public Scene getScene() {
        return scene;
    }

    abstract public void render();
}
