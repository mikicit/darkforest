package dev.mikita.darkforest.controller;

import dev.mikita.darkforest.view.AView;
import lombok.Getter;

/**
 * The type A controller.
 * <p>
 * An abstract controller class. Other controllers inherit this class.
 */
public abstract class AController {
    /**
     * The view.
     * -- GETTER --
     * A reference to the view associated with this controller.
     *
     * @return The current view.
     */
    @Getter protected AView view;

    /**
     * The controller was initialized.
     * <p>
     * Status indicating whether the given controller has already been initialized or not.
     */
    protected boolean wasInitialized;

    /**
     * Reset.
     * <p>
     * Reset controller state.
     * It is used when it is necessary to completely return the state of the game to its original state.
     */
    public void reset() {
        wasInitialized = false;
    }

    /**
     * Init.
     * <p>
     * Controller initialization.
     */
    public abstract void init();

    /**
     * Tick.
     * <p>
     * This method is called by the main timer every frame.
     *
     * @param delta The delta.
     */
    public abstract void tick(double delta);
}