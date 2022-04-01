package dev.mikicit.game.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class GameHandler {
    Scene scene;

    public void init(Scene scene) {
        this.scene = scene;

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (event -> {
            System.out.println(event);
        }));
    }
}
