package dev.mikicit.darkforest.model;

import dev.mikicit.darkforest.core.location.Location;
import dev.mikicit.darkforest.core.sprite.SpriteManager;
import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Monster;
import dev.mikicit.darkforest.model.entity.Player;

import java.util.ArrayList;

public class GameModel {
    private static GameModel instance;
    private static boolean isRunning = false;
    private Player player;
    private Location currentLocation;

    private GameModel() {}

    public void init() {
        if (isRunning) return;
        isRunning = true;
        currentLocation = new Location(1);
        currentLocation.init();

        player = new Player("Mikita", 500, 50, 500, 50);
        currentLocation.setPlayer(player);
    }

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    public static void resetGame() {
        if (isRunning) {
            System.out.println("test");
            isRunning = false;
            instance = null;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    // Getters
    public Player getPlayer() {
        return player;
    }

    public TileMap getTileMap() {
        return currentLocation.getTileMap();
    }

    public ArrayList<Monster> getMonsters() {
        return currentLocation.getMonsters();
    }

    public ArrayList<AItem> getItems() {
        return currentLocation.getItems();
    }

    public SpriteManager getSpriteManager() {
        return currentLocation.getSpriteManager();
    }
}
