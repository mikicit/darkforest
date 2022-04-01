package dev.mikicit.game.controller;

import dev.mikicit.game.view.AView;
import java.util.ArrayList;

public abstract class AController {
    protected AView view;
    protected ArrayList<String> input = new ArrayList<>();

    public AView getView() {
        return view;
    }
}
