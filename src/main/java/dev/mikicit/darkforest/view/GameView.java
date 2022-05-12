package dev.mikicit.darkforest.view;

import dev.mikicit.darkforest.controller.GameController;
import dev.mikicit.darkforest.core.sprite.SpriteManager;
import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.model.GameModel;
import dev.mikicit.darkforest.core.Config;
import dev.mikicit.darkforest.model.entity.Player;
import dev.mikicit.darkforest.view.component.game.HPBox;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * The type Game view.
 * <p>
 * Game world representation class.
 */
public class GameView extends AView {
    private TileMap tileMap;
    private GraphicsContext gc;
    private Pane canvasRoot;
    private SpriteManager spriteManager;

    /**
     * Instantiates a new Game view.
     *
     * @param controller the controller
     */
    public GameView(GameController controller) {
        this.controller = controller;
    }

    public void init() {
        GameModel gameModel = GameModel.getInstance();
        tileMap = gameModel.getTileMap();
        spriteManager = gameModel.getSpriteManager();
        Player player = gameModel.getPlayer();

        // Main Pane
        Pane root = new Pane();

        // Canvas
        // Init main pane and canvas
        canvasRoot = new Pane();
        Canvas canvas = new Canvas(tileMap.getMapWidth(), tileMap.getMapHeight());
        canvasRoot.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvasRoot);

        // GUI
        BorderPane borderPane = new BorderPane();
        root.getChildren().add(borderPane);

        HPBox health = new HPBox(player.getHP().getHealth());
        player.getHP().addObserver(health);

        borderPane.getChildren().add(health.getText());

        // Scene Creation
        scene = new Scene(root, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);

        // Attaching Event Listeners
        scene.setOnKeyPressed(((GameController) controller)::keyPressedHandler);
        scene.setOnKeyReleased(((GameController) controller)::keyReleasedHandler);
    }

    /**
     * Gets canvas root.
     *
     * @return the canvas root
     */
// Getters
    public Pane getCanvasRoot() {
        return canvasRoot;
    }

    @Override
    public void render() {
        gc.clearRect(0, 0, tileMap.getMapWidth(), tileMap.getMapHeight());
        tileMap.render(gc);
        spriteManager.render(gc);
    }
}