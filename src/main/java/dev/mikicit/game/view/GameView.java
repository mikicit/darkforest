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

public class GameView extends AView {
    private final GraphicsContext gc;
    private final TileMap tileMap;
    private final Scene scene;

    public GameView(GameController controller) {
        this.controller = controller;

        // Sprites Init
        Player player = controller.getGameModel().getPlayer();
//        SpriteManager spriteManager = new SpriteManager();
        double playerStartX = (double) (Config.getWindowWidth() / 2) - player.getWidth() / 2;
        double playerStartY = (double) (Config.getWindowWidth() / 2) - player.getHeight() / 2;
        player.setPosition(playerStartX, playerStartY);
//        spriteManager.addSprite(player);

        // TileMap Init
        TileMapReader tileMapReader = new TileMapReader();
        tileMap = tileMapReader.createTileMap("src/main/resources/map/map.txt", Config.getTileSize());

        // JavaFX init
        Pane root = new Pane();
        Pane pane = new Pane();
        Canvas canvas = new Canvas(tileMap.getMapWidth(), tileMap.getMapHeight());

        pane.getChildren().add(canvas);
        root.getChildren().add(pane);

        gc = canvas.getGraphicsContext2D();

        scene = new Scene(root, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);
    }

    public void update(double delta) {
        gc.clearRect(0, 0, tileMap.getMapWidth(), tileMap.getMapHeight());
//        playerSprite.setVelocity(0, 0);
//
//        int convertedX = tileMap.convertPixelToTile(playerSprite.getX());
//        int convertedY = tileMap.convertPixelToTile(playerSprite.getY());
//
//        System.out.println("X: " + convertedX + ", Y: " + convertedY + ", TileID: " + tileMap.getTile(convertedX, convertedY).getId());
//
//        if (input.contains("LEFT") && playerSprite.getX() - 1 > 0) {
//            playerSprite.moveLeft();
//        }
//        if (input.contains("RIGHT") && playerSprite.getX() + playerSprite.getWidth() + 1 < mapWidth) {
//            playerSprite.moveRight();
//        }
//        if (input.contains("UP") && playerSprite.getY() - 1 > 0) {
//            playerSprite.moveUp();
//        }
//        if (input.contains("DOWN") && playerSprite.getY() + playerSprite.getHeight() + 1 < mapHeight) {
//            playerSprite.moveDown();
//        }
//
//        spriteManager.update(elapsedTime);
//        tileMap.render(gc);
//        spriteManager.render(gc);
//
//        // Camera
//        double cameraX = ((playerSprite.getX() - windowWidth / 2) + playerSprite.getWidth() / 2);
//        double cameraY = ((playerSprite.getY() - windowHeight / 2) + playerSprite.getHeight() / 2);
//
//        if ((playerSprite.getY() + windowHeight / 2 + playerSprite.getHeight() / 2) < mapHeight &&
//            (playerSprite.getY() - windowHeight / 2 + playerSprite.getHeight() / 2) > 0
//        ) {
//            pane.setTranslateY(cameraY * -1);
//        }
//
//        if ((playerSprite.getX() + windowWidth / 2 + playerSprite.getWidth() / 2) < mapWidth &&
//            (playerSprite.getX() - windowWidth / 2 + playerSprite.getWidth() / 2) > 0
//        ) {
//            pane.setTranslateX(cameraX * -1);
//        }
    }

    public void render() {
        tileMap.render(gc);
    }
}
