package dev.mikicit.darkforest.model;

import dev.mikicit.darkforest.core.PlayerConfig;
import dev.mikicit.darkforest.core.factory.ItemFactory;
import dev.mikicit.darkforest.core.location.Location;
import dev.mikicit.darkforest.core.location.LocationManager;
import dev.mikicit.darkforest.core.sprite.SpriteManager;
import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Item.equipment.AEquipment;
import dev.mikicit.darkforest.model.entity.Monster;
import dev.mikicit.darkforest.model.entity.Player;
import dev.mikicit.darkforest.model.entity.Portal;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * The type Game model.
 * <p>
 * The main class responsible for the state of the game (game objects).
 */
public class GameModel {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    private static GameModel instance;
    private Player player;
    private Location currentLocation;
    private LocationManager locationManager;

    /**
     * Init.
     * <p>
     * Initialization of the game world. Loading locations, game objects, etc.
     *
     * @param fromSave the from save
     */
    public void init(boolean fromSave) {
        // Loading Player Config
        JSONObject playerConfig = PlayerConfig.getPlayerConfig(fromSave);
        locationManager = new LocationManager();

        // Setting characteristics
        player = new Player(
                playerConfig.getString("name"),
                playerConfig.getDouble("health"),
                playerConfig.getDouble("damage"),
                playerConfig.getDouble("armor"),
                playerConfig.getDouble("damageRadius"));

        player.getHP().setInitialHealthHealth(playerConfig.getDouble("initialHealth"));

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

        player.setPosition(
                TileMap.convertTileToPixel(playerConfig.getInt("positionX")),
                TileMap.convertTileToPixel(playerConfig.getInt("positionY")));

        // Setting Up Location
        setLocation(playerConfig.getInt("locationId"));
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    /**
     * Sets location.
     * <p>
     * A method that allows you to change the location.
     *
     * @param locationId the location id
     */
    public void setLocation(int locationId) {
        if (currentLocation != null) {
            currentLocation.unsetPlayer();
        }

        currentLocation = locationManager.getLocation(locationId);
        currentLocation.init();
        currentLocation.setPlayer(player);

        log.info("The location \"" + currentLocation.getName() + "\" was set.");
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets current location.
     *
     * @return the current location
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Gets tile map.
     *
     * @return the tile map
     */
    public TileMap getTileMap() {
        return currentLocation.getTileMap();
    }

    /**
     * Gets monsters.
     *
     * @return the monsters
     */
    public ArrayList<Monster> getMonsters() {
        return currentLocation.getMonsters();
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public ArrayList<AItem> getItems() {
        return currentLocation.getItems();
    }

    /**
     * Gets portals.
     *
     * @return the portals
     */
    public ArrayList<Portal> getPortals() {
        return currentLocation.getPortals();
    }

    /**
     * Gets sprite manager.
     *
     * @return the sprite manager
     */
    public SpriteManager getSpriteManager() {
        return currentLocation.getSpriteManager();
    }
}
