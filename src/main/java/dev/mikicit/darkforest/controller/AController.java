package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.view.AView;
import javafx.scene.input.KeyEvent;

public abstract class AController {
    protected AView view;
    protected boolean wasInitialized;

    public AView getView() {
        return view;
    }

    public void reset() {
        wasInitialized = false;
    }

    public abstract void init();
    public abstract void tick(double delta);
}