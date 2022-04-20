package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.view.AView;

public abstract class AController {
    protected AView view;

    public AView getView() {
        return view;
    }

    public abstract void tick(double delta);
}