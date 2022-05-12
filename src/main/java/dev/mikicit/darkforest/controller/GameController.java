package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.sprite.SpriteManager;
import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.core.Config;
import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.model.GameModel;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Monster;
import dev.mikicit.darkforest.model.entity.Player;
import dev.mikicit.darkforest.model.entity.Portal;
import dev.mikicit.darkforest.view.GameView;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

/**
 * The type Game controller.
 *
 * Game world controller.
 */
public class GameController extends AController {
    // Inputs
    private ArrayList<String> input = new ArrayList<>();

    // Links
    private Player player;
    private TileMap tileMap;
    private ArrayList<Monster> monsters;
    private ArrayList<AItem> items;
    private ArrayList<Portal> portals;
    private SpriteManager spriteManager;
    private Pane canvasRoot;

    public void init() {
        if (wasInitialized) return;

        // Init View
        view = new GameView(this);
        view.init();

        // Links to game entitles
        GameModel gameModel = GameModel.getInstance();
        player = gameModel.getPlayer();
        monsters = gameModel.getMonsters();
        items = gameModel.getItems();
        portals = gameModel.getPortals();
        tileMap = gameModel.getTileMap();
        canvasRoot = ((GameView) view).getCanvasRoot();
        spriteManager = gameModel.getSpriteManager();

        // Set Initial Camera Position
        setCameraPositionOnLoad();

        // Setting Up State
        wasInitialized = true;
    }

    /**
     * Key pressed handler.
     *
     * @param e the e
     */
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

        // Main Menu
        if (code.equals("ESCAPE")) {
            input = new ArrayList<>();
            StateManager.goToGameMenu();
        }

        // Go to Inventory
        if (code.equals("I")) {
            resetInput();
            StateManager.goToInventory();
        }
    }

    /**
     * Key released handler.
     *
     * @param e the e
     */
    public void keyReleasedHandler(KeyEvent e) {
        String code = e.getCode().toString();
        input.remove(code);
    }

    /**
     * Player attack.
     */
    public void playerAttack() {
        // Monsters
        ArrayList<Monster> deadMonsters = new ArrayList<>();

        for (Monster monster : monsters) {
            if (player.intersectsAttackBox(monster)) {
                player.attack(monster);

                if (monster.isDead()) {
                    deadMonsters.add(monster);
                    break;
                }
            }
        }

        // Remove dead monsters
        for (Monster deadMonster : deadMonsters) {
            monsters.remove(deadMonster);
            spriteManager.removeSprite(deadMonster);
        }
    }

    /**
     * Update Player Position.
     * <p>
     * The method responsible for controlling the character's movement.
     * Controls whether the character can move in a certain direction or not.
     *
     * @param delta the delta
     */
    private void updatePlayerPosition(double delta) {
        Rectangle2D moveBox = player.getMoveBox();
        int path = (int) (player.getSpeed() * delta); // real distance traveled in time without reference to frame rate

        if (input.contains("A") && player.getX() - path > 0) {
            int tileMinX = TileMap.convertPixelToTile(moveBox.getMinX() - path);
            int tileMinY = TileMap.convertPixelToTile(moveBox.getMinY());
            int tileMaxY = TileMap.convertPixelToTile(moveBox.getMaxY());

            if (tileMap.getTile(tileMinX, tileMinY).isPassable() &&
                    tileMap.getTile(tileMinX, tileMaxY).isPassable()) {
                player.moveLeft(path);
            }
        }

        if (input.contains("D") && player.getX() + player.getWidth() + path < tileMap.getMapWidth()) {
            int tileMinY = TileMap.convertPixelToTile(moveBox.getMinY());
            int tileMaxY = TileMap.convertPixelToTile(moveBox.getMaxY());
            int tileMaxX = TileMap.convertPixelToTile(moveBox.getMaxX() + path);

            if (tileMap.getTile(tileMaxX, tileMinY).isPassable() &&
                    tileMap.getTile(tileMaxX, tileMaxY).isPassable()) {
                player.moveRight(path);
            }
        }

        if (input.contains("W") && player.getY() - path > 0) {
            int tileMinX = TileMap.convertPixelToTile(moveBox.getMinX());
            int tileMinY = TileMap.convertPixelToTile(moveBox.getMinY() - path);
            int tileMaxX = TileMap.convertPixelToTile(moveBox.getMaxX());

            if (tileMap.getTile(tileMinX, tileMinY).isPassable() &&
                    tileMap.getTile(tileMaxX, tileMinY).isPassable()) {
                player.moveUp(path);
            }
        }

        if (input.contains("S") && player.getY() + player.getHeight() + path < tileMap.getMapHeight()) {
            int tileMinX = TileMap.convertPixelToTile(moveBox.getMinX());
            int tileMaxY = TileMap.convertPixelToTile(moveBox.getMaxY() + path);
            int tileMaxX = TileMap.convertPixelToTile(moveBox.getMaxX());

            if (tileMap.getTile(tileMinX, tileMaxY).isPassable() &&
                    tileMap.getTile(tileMaxX, tileMaxY).isPassable()) {
                player.moveDown(path);
            }
        }
    }

    /**
     * Set Camera Position On Load.
     * <p>
     * A method that sets the camera in the right place when the game is first loaded.
     */
    private void setCameraPositionOnLoad() {
        double offsetX = ((player.getX() - (double) (Config.getWindowWidth() / 2)) + player.getWidth() / 2);
        double offsetY = ((player.getY() - (double) (Config.getWindowHeight() / 2)) + player.getHeight() / 2);

        // X camera
        if (offsetX > 0 && offsetX > tileMap.getMapWidth() - Config.getWindowWidth()) {
            canvasRoot.setTranslateX((offsetX - (offsetX - (tileMap.getMapWidth() - Config.getWindowWidth()))) * -1);
        }

        // Y camera
        if (offsetY > 0 && offsetY > tileMap.getMapHeight() - Config.getWindowHeight()) {
            canvasRoot.setTranslateY((offsetY - (offsetY - (tileMap.getMapHeight() - Config.getWindowHeight()))) * -1);
        }
    }

    /**
     * Update Camera Position.
     * <p>
     * The method responsible for setting the current position for the camera.
     */
    private void updateCameraPosition() {
        double offsetX = ((player.getX() - (double) (Config.getWindowWidth() / 2)) + player.getWidth() / 2);
        double offsetY = ((player.getY() - (double) (Config.getWindowHeight() / 2)) + player.getHeight() / 2);

        // X camera
        if (offsetX > 0 && offsetX < tileMap.getMapWidth() - Config.getWindowWidth()) {
            canvasRoot.setTranslateX(offsetX * -1);
        }

        // Y camera
        if (offsetY > 0 && offsetY < tileMap.getMapHeight() - Config.getWindowHeight()) {
            canvasRoot.setTranslateY(offsetY * -1);
        }
    }

    /**
     * Check Intersections.
     * <p>
     * General method for checking sprite intersections.
     */
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

        // Monsters View
        for (Monster monster : monsters) {
            if (monster.intersectsRadiusViewBox(player)) {
                monster.setAim(player);
            }
        }

        // Portals
        for (Portal portal : portals) {
            if (player.intersectsMoveBox(portal)) {
                portal.activate();

                for (Monster monster : monsters) {
                    monster.offCombat();
                }

                // Reload Controller
                wasInitialized = false;
                init();

                // Reset Current Game Scene
                StateManager.resetScene();
                return;
            }
        }
    }

    /**
     * Reset Input.
     */
    private void resetInput() {
        input = new ArrayList<>();
    }

    @Override
    public void tick(double delta) {
        // Update
        updatePlayerPosition(delta);

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
