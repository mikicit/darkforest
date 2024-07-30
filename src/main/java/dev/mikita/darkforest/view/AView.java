package dev.mikita.darkforest.view;

import dev.mikita.darkforest.controller.AController;
import javafx.scene.Scene;
import lombok.Getter;

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
     * -- GETTER --
     * Gets scene.
     *
     * @return The scene.
     */
    @Getter protected Scene scene;

    /**
     * Initializes the view.
     */
    abstract public void init();

    /**
     * Render.
     */
    abstract public void render();
}
