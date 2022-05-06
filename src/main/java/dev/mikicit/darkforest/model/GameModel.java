package dev.mikicit.darkforest.model;

import dev.mikicit.darkforest.core.sprite.SpriteManager;
import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.core.tile.TileMapCreator;
import dev.mikicit.darkforest.core.Config;
import dev.mikicit.darkforest.model.entity.Item.bottle.HealthBottle;
import dev.mikicit.darkforest.model.entity.Monster;
import dev.mikicit.darkforest.model.entity.Player;

import java.util.ArrayList;

public class GameModel {
    private static final GameModel instance;
    private final Player player;
    private final ArrayList<Monster> monsters = new ArrayList<>();
    private final ArrayList<HealthBottle> healthBottles = new ArrayList<>();
    private final TileMap tileMap;
    private final SpriteManager spriteManager;

    private GameModel() {
        // Init Game World
        player = new Player("Mikita", 500, 50, 500, 50);
        tileMap = TileMapCreator.createTileMap("src/main/resources/map/map.txt", Config.getTileSize());
        monsters.add(new Monster("Skeleton", 200, 50, 20, 30, 500));
        healthBottles.add(new HealthBottle("Health Bottle", 100));
        healthBottles.add(new HealthBottle("Health Bottle 1", 150));
        healthBottles.add(new HealthBottle("Health Bottle 2", 50));

        // Position
        player.setPosition(tileMap.convertTileToPixel(10), tileMap.convertTileToPixel(10));
        monsters.add(new Monster("Skeleton", 200, 50, 20, 30, 500));
        monsters.get(0).setPosition(tileMap.convertTileToPixel(15), tileMap.convertTileToPixel(10));

        healthBottles.get(0).setPosition(tileMap.convertTileToPixel(11), tileMap.convertTileToPixel(10));
        healthBottles.get(1).setPosition(tileMap.convertTileToPixel(13), tileMap.convertTileToPixel(9));
        healthBottles.get(2).setPosition(tileMap.convertTileToPixel(10), tileMap.convertTileToPixel(12));

        spriteManager = new SpriteManager();
        spriteManager.addSprite(player);

        for (Monster monster : monsters) {
            spriteManager.addSprite(monster);
        }

        for (HealthBottle healthBottle : healthBottles) {
            spriteManager.addSprite(healthBottle);
        }
    }

    static {
        instance = new GameModel();
    }

    public static GameModel getInstance() {
        return instance;
    }

    // Getters
    public Player getPlayer() {
        return player;
    }

    public TileMap getTileMap() {
        return tileMap;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public ArrayList<HealthBottle> getHealthBottles() {
        return healthBottles;
    }

    public SpriteManager getSpriteManager() {
        return spriteManager;
    }
}
