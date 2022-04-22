package dev.mikicit.darkforest.view;

import dev.mikicit.darkforest.controller.GameController;
import dev.mikicit.darkforest.core.sprite.Sprite;
import dev.mikicit.darkforest.core.sprite.SpriteManager;
import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.model.GameModel;
import dev.mikicit.darkforest.core.Config;
import dev.mikicit.darkforest.model.entity.Monster;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameView extends AView {
    private final GameModel gameModel;
    private final TileMap tileMap;
    private final GraphicsContext gc;
    private final Pane canvasRoot;
    private final SpriteManager spriteManager;

    public GameView(GameController controller) {
        this.controller = controller;
        gameModel = GameModel.getInstance();
        tileMap = gameModel.getTileMap();
        spriteManager = gameModel.getSpriteManager();

        // Init main pane and canvas
        Pane root = new Pane();
        canvasRoot = new Pane();
        Canvas canvas = new Canvas(tileMap.getMapWidth(), tileMap.getMapHeight());

        canvasRoot.getChildren().add(canvas);
        root.getChildren().add(canvasRoot);

        gc = canvas.getGraphicsContext2D();
        scene = new Scene(root, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);

        // Attaching Event Listeners
        scene.setOnKeyPressed(controller::keyPressedHandler);
        scene.setOnKeyReleased(controller::keyReleasedHandler);
    }

    public Pane getCanvasRoot() {
        return canvasRoot;
    }

    @Override
    public void render() {
        gc.clearRect(0, 0, tileMap.getMapWidth(), tileMap.getMapHeight());
        gameModel.getTileMap().render(gc);

        spriteManager.render(gc);
    }
}
