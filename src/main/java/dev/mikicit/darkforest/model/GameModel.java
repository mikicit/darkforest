package dev.mikicit.darkforest.model;

import dev.mikicit.darkforest.core.PlayerConfig;
import dev.mikicit.darkforest.core.factory.ItemFactory;
import dev.mikicit.darkforest.core.location.Location;
import dev.mikicit.darkforest.core.sprite.SpriteManager;
import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Item.equipment.AEquipment;
import dev.mikicit.darkforest.model.entity.Monster;
import dev.mikicit.darkforest.model.entity.Player;
import org.json.JSONObject;

import java.util.ArrayList;

public class GameModel {
    private static GameModel instance;
    private static boolean isRunning = false;
    private Player player;
    private Location currentLocation;

    private GameModel() {}

    public void init(boolean fromSave) {
        if (isRunning) return;
        isRunning = true;

        // Loading Player Config
        JSONObject playerConfig = PlayerConfig.getPlayerConfig(fromSave);
        currentLocation = new Location(playerConfig.getInt("locationId"));
        currentLocation.init();

        // Setting characteristics
        player = new Player(
                playerConfig.getString("name"),
                playerConfig.getDouble("health"),
                playerConfig.getDouble("damage"),
                playerConfig.getDouble("armor"),
                playerConfig.getDouble("damageRadius"));

        player.getHP().setInitialHealthHealth(playerConfig.getDouble("initialHealth"));

        player.setPosition(
                TileMap.convertTileToPixel(playerConfig.getInt("positionX")),
                TileMap.convertTileToPixel(playerConfig.getInt("positionY")));

        // Setting Equipped Items
        int equippedWeaponId = playerConfig.getInt("equippedWeaponId");
        if (equippedWeaponId != -1) {
            player.setEquipment((AEquipment) ItemFactory.getItem(equippedWeaponId));
        }

        int equippedArmorId = playerConfig.getInt("equippedArmorId");
        if (equippedArmorId != -1) {
            player.setEquipment((AEquipment) ItemFactory.getItem(equippedArmorId));
        }

        // Setting items in the inventory
        for (Object item : playerConfig.getJSONArray("inventory")) {
            player.getInventory().addItem(ItemFactory.getItem((int) item));
        }

        currentLocation.setPlayer(player);
    }

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    public boolean isRunning() {
        return isRunning;
    }

    // Getters
    public Player getPlayer() {
        return player;
    }

    public Location getCurrentLocation() {
        return currentLocation;
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
