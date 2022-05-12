package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.view.AView;

/**
 * The type A controller.
 * <p>
 * An abstract controller class. Other controllers inherit this class.
 */
public abstract class AController {
    /**
     * The View.
     * <p>
     * A reference to the view associated with this controller.
     */
    protected AView view;

    /**
     * The Was initialized.
     * <p>
     * Status indicating whether the given controller has already been initialized or not.
     */
    protected boolean wasInitialized;

    /**
     * Gets view.
     *
     * @return the view
     */
    public AView getView() {
        return view;
    }

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
     * Controller initialization
     */
    public abstract void init();

    /**
     * Tick.
     * <p>
     * This method is called by the main timer every frame.
     *
     * @param delta the delta
     */
    public abstract void tick(double delta);
}