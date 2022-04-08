package dev.mikicit.game.view;

import dev.mikicit.game.controller.GameController;
import dev.mikicit.game.core.Config;
import dev.mikicit.game.core.tileEngine.TileMap;
import dev.mikicit.game.core.tileEngine.TileMapReader;
import dev.mikicit.game.entity.Player;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameView extends AView {
    private final GraphicsContext gc;
    private final TileMap tileMap;
    private final GameController controller;
    private final Pane pane;

    public GameView(GameController controller) {
        this.controller = controller;

        // Sprites Init
        Player player = controller.getGameModel().getPlayer();
        double playerStartX = (double) (Config.getWindowWidth() / 2) - player.getWidth() / 2;
        double playerStartY = (double) (Config.getWindowWidth() / 2) - player.getHeight() / 2;
        player.setPosition(playerStartX, playerStartY);

        // TileMap Init
        TileMapReader tileMapReader = new TileMapReader();
        tileMap = tileMapReader.createTileMap("src/main/resources/map/map.txt", Config.getTileSize());

        // JavaFX init
        Pane root = new Pane();
        pane = new Pane();
        Canvas canvas = new Canvas(tileMap.getMapWidth(), tileMap.getMapHeight());

        pane.getChildren().add(canvas);
        root.getChildren().add(pane);

        gc = canvas.getGraphicsContext2D();
        scene = new Scene(root, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);
    }

    public void update(double delta) {
        gc.clearRect(0, 0, tileMap.getMapWidth(), tileMap.getMapHeight());

        Player player = controller.getGameModel().getPlayer();
        ArrayList<String> input = controller.getInput();

        System.out.println(input);

        player.setVelocity(0, 0);

        int convertedX = tileMap.convertPixelToTile(player.getX());
        int convertedY = tileMap.convertPixelToTile(player.getY());

//        System.out.println("X: " + convertedX + ", Y: " + convertedY + ", TileID: " + tileMap.getTile(convertedX, convertedY).getId());

        if (input.contains("LEFT") && player.getX() - 1 > 0) {
            player.moveLeft();
        }
        if (input.contains("RIGHT") && player.getX() + player.getWidth() + 1 < tileMap.getMapWidth()) {
            player.moveRight();
        }
        if (input.contains("UP") && player.getY() - 1 > 0) {
            player.moveUp();
        }
        if (input.contains("DOWN") && player.getY() + player.getHeight() + 1 < tileMap.getMapHeight()) {
            player.moveDown();
        }

        // Camera
        double cameraX = ((player.getX() - (double) (Config.getWindowWidth() / 2)) + player.getWidth() / 2);
        double cameraY = ((player.getY() - (double) (Config.getWindowHeight() / 2)) + player.getHeight() / 2);

        if ((player.getY() + ((double) (Config.getWindowHeight() / 2) + player.getHeight() / 2) < tileMap.getMapHeight() &&
            (player.getY() - ((double) (Config.getWindowHeight() / 2) + player.getHeight() / 2) > 0
        ))) {
            pane.setTranslateY(cameraY * -1);
        }

        if ((player.getX() + ( (double) (Config.getWindowWidth() / 2) + player.getWidth() / 2) < player.getWidth() &&
            (player.getX() - ( (double) (Config.getWindowWidth() / 2) + player.getWidth() / 2) > 0
        ))) {
            pane.setTranslateX(cameraX * -1);
        }

        player.update(delta);
    }

    public void render() {
        Player player = controller.getGameModel().getPlayer();
        tileMap.render(gc);
        player.render(gc);
    }
}
