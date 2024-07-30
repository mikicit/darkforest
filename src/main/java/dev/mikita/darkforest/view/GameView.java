package dev.mikita.darkforest.view;

import dev.mikita.darkforest.controller.GameController;
import dev.mikita.darkforest.core.sprite.SpriteManager;
import dev.mikita.darkforest.core.tile.TileMap;
import dev.mikita.darkforest.model.GameModel;
import dev.mikita.darkforest.core.Config;
import dev.mikita.darkforest.model.entity.Player;
import dev.mikita.darkforest.view.component.game.HPBox;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import lombok.Getter;

/**
 * The type Game view.
 * <p>
 * Game world representation class.
 */
public class GameView extends AView {
    /**
     * The tileMap.
     */
    private TileMap tileMap;

    /**
     * The graphics context.
     */
    private GraphicsContext gc;

    /**
     * The Canvas root.
     * -- Getter --
     * Gets canvas root.
     *
     * @return The canvas root.
     */
    @Getter private Pane canvasRoot;

    /**
     * The spriteManager.
     */
    private SpriteManager spriteManager;

    /**
     * Instantiates a new Game view.
     *
     * @param controller The controller.
     */
    public GameView(GameController controller) {
        this.controller = controller;
    }

    @Override
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

    @Override
    public void render() {
        gc.clearRect(0, 0, tileMap.getMapWidth(), tileMap.getMapHeight());
        tileMap.render(gc);
        spriteManager.render(gc);
    }
}