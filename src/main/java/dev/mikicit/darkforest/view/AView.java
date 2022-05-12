package dev.mikicit.darkforest.view;

import dev.mikicit.darkforest.controller.AController;
import javafx.scene.Scene;

/**
 * The type AView.
 * <p>
 * An abstract view class.
 */
public abstract class AView {
    /**
     * The Controller.
     */
    protected AController controller;
    /**
     * The Scene.
     */
    protected Scene scene;

    /**
     * Gets scene.
     * <p>
     * Returns the scene.
     *
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Init.
     * <p>
     * View initialization.
     */
    abstract public void init();

    /**
     * Render.
     */
    abstract public void render();
}
