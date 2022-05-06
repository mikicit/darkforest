package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.sprite.SpriteManager;
import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.core.Config;
import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.model.GameModel;
import dev.mikicit.darkforest.model.entity.Item.bottle.HealthBottle;
import dev.mikicit.darkforest.model.entity.Monster;
import dev.mikicit.darkforest.model.entity.Player;
import dev.mikicit.darkforest.view.GameView;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class GameController extends AController {
    protected ArrayList<String> input = new ArrayList<>();
    private final GameModel gameModel;
    private final Player player;
    private final ArrayList<Monster> monsters;
    private final ArrayList<HealthBottle> healthBottles;
    private final TileMap tileMap;
    private final Pane canvasRoot;
    private final SpriteManager spriteManager;

    public GameController() {
        gameModel = GameModel.getInstance();
        view = new GameView(this);

        // Links to game entitles
        player = gameModel.getPlayer();
        monsters = gameModel.getMonsters();
        healthBottles = gameModel.getHealthBottles();

        tileMap = gameModel.getTileMap();
        canvasRoot = ((GameView) view).getCanvasRoot();
        spriteManager = gameModel.getSpriteManager();
    }

    // Event Handlers
    public void keyPressedHandler(KeyEvent e) {
        String code = e.getCode().toString();
        if (!input.contains(code)) {
            input.add(code);
        }

        // Player
        if (code.equals("J")) {
            System.out.println("Попытка атаки!");
        }

        if (code.equals("SPACE")) {
            player.getHP().setHealth(player.getHP().getHealth() - 100);
        }

        // Main Menu
        if (code.equals("ESCAPE")) {
            input = new ArrayList<>();
            StateManager.goToGameMenu();
        }

        // Go to Inventory
        if (code.equals("I")) {
            input = new ArrayList<>();
            StateManager.goToInventory();
        }
    }

    public void keyReleasedHandler(KeyEvent e) {
        String code = e.getCode().toString();
        input.remove(code);
    }

    private void updatePlayerPosition() {
        Rectangle2D moveBox = player.getMoveBox();

        if (input.contains("A") && player.getX() - 1 > 0) {
            int tileMinX = tileMap.convertPixelToTile(moveBox.getMinX() - 1);
            int tileMinY = tileMap.convertPixelToTile(moveBox.getMinY());
            int tileMaxY = tileMap.convertPixelToTile(moveBox.getMaxY());

            if (tileMap.getTile(tileMinX, tileMinY).isPassable() &&
                    tileMap.getTile(tileMinX, tileMaxY).isPassable()) {
                player.moveLeft();
            }
        }

        if (input.contains("D") && player.getX() + player.getWidth() + 1 < tileMap.getMapWidth()) {
            int tileMinY = tileMap.convertPixelToTile(moveBox.getMinY());
            int tileMaxY = tileMap.convertPixelToTile(moveBox.getMaxY());
            int tileMaxX = tileMap.convertPixelToTile(moveBox.getMaxX() + 1);

            if (tileMap.getTile(tileMaxX, tileMinY).isPassable() &&
                    tileMap.getTile(tileMaxX, tileMaxY).isPassable()) {
                player.moveRight();
            }
        }

        if (input.contains("W") && player.getY() - 1 > 0) {
            int tileMinX = tileMap.convertPixelToTile(moveBox.getMinX());
            int tileMinY = tileMap.convertPixelToTile(moveBox.getMinY() - 1);
            int tileMaxX = tileMap.convertPixelToTile(moveBox.getMaxX());

            if (tileMap.getTile(tileMinX, tileMinY).isPassable() &&
                    tileMap.getTile(tileMaxX, tileMinY).isPassable()) {
                player.moveUp();
            }
        }

        if (input.contains("S") && player.getY() + player.getHeight() + 1 < tileMap.getMapHeight()) {
            int tileMinX = tileMap.convertPixelToTile(moveBox.getMinX());
            int tileMaxY = tileMap.convertPixelToTile(moveBox.getMaxY() + 1);
            int tileMaxX = tileMap.convertPixelToTile(moveBox.getMaxX());

            if (tileMap.getTile(tileMinX, tileMaxY).isPassable() &&
                    tileMap.getTile(tileMaxX, tileMaxY).isPassable()) {
                player.moveDown();
            }
        }
    }

    private void updateCameraPosition() {
        double offsetX = ((player.getX() - (double) (Config.getWindowWidth() / 2)) + player.getWidth() / 2);
        double offsetY = ((player.getY() - (double) (Config.getWindowHeight() / 2)) + player.getHeight() / 2);

        // X camera
        if (offsetY > 0 && offsetY < tileMap.getMapHeight() - Config.getWindowHeight()) {
            canvasRoot.setTranslateY(offsetY * -1);
        }

        // Y camera
        if (offsetX > 0 && offsetX < tileMap.getMapWidth() - Config.getWindowWidth()) {
            canvasRoot.setTranslateX(offsetX * -1);
        }
    }

    private void checkIntersections() {
        // Monsters
        for (Monster monster : monsters) {
//            System.out.println(player.intersectsCollectionBox(monster));
        }

        // Bottles
        ArrayList<HealthBottle> takenHealthBottles = new ArrayList<>();

        for (HealthBottle healthBottle : healthBottles) {
            if (player.intersectsMoveBox(healthBottle)) {
                if (healthBottle.take(player)) {
                    takenHealthBottles.add(healthBottle);
                }
            }
        }

        for (HealthBottle healthBottle : takenHealthBottles) {
            spriteManager.removeSprite(healthBottle);
            healthBottles.remove(healthBottle);
        }
    }

    @Override
    public void tick(double delta) {
        // Update
        updatePlayerPosition();

        // Camera
        updateCameraPosition();

        // Check intersections
        checkIntersections();

        // Sprite Update
        spriteManager.update(delta);

        // Render
        view.render();
    }
}
