package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.sprite.SpriteManager;
import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.core.Config;
import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.model.GameModel;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Monster;
import dev.mikicit.darkforest.model.entity.Player;
import dev.mikicit.darkforest.view.GameView;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class GameController extends AController {
    protected ArrayList<String> input = new ArrayList<>();
    private GameModel gameModel;
    private Player player;
    private ArrayList<Monster> monsters;
    private ArrayList<AItem> items;
    private TileMap tileMap;
    private Pane canvasRoot;
    private SpriteManager spriteManager;
    private boolean wasInitialized = false;

    public GameController() {}

    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
        gameModel = GameModel.getInstance();
        view = new GameView(this);

        // Links to game entitles
        player = gameModel.getPlayer();
        monsters = gameModel.getMonsters();
        items = gameModel.getItems();

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
            playerAttack();
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

    // Player Attack handler
    public void playerAttack() {
        for (Monster monster : monsters) {
            if (player.intersectsAttackBox(monster)) {
                player.attack(monster);

                if (monster.isDead()) {
                    monsters.remove(monster);
                    spriteManager.removeSprite(monster);
                    break;
                }
            }
        }
    }

    // Updating Player Position with constraints
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

    // Updating Camera Position
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

    // Checking Intersections with Items
    private void checkIntersections() {
        // Items
        ArrayList<AItem> takenItems = new ArrayList<>();

        for (AItem item : items) {
            if (player.intersectsMoveBox(item)) {
                if (item.take(player)) {
                    takenItems.add(item);
                }
            }
        }

        // Remove Taken Items from game world
        for (AItem item : takenItems) {
            spriteManager.removeSprite(item);
            items.remove(item);
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
