package dev.mikicit.game.view;

import dev.mikicit.game.controller.AController;
import dev.mikicit.game.controller.MainMenuController;
import dev.mikicit.game.core.Config;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MainMenuView extends AView {
    public MainMenuView(AController controller) {
        this.controller = controller;

        // JavaFX init
        Pane root = new Pane();
        Pane pane = new Pane();

        root.getChildren().add(pane);

        scene = new Scene(root, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);
    }

    public void update(double delta) {

    }

    public void render() {

    }
}
