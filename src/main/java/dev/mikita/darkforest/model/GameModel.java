package dev.mikita.darkforest.model;

import dev.mikita.darkforest.core.PlayerConfig;
import dev.mikita.darkforest.core.factory.ItemFactory;
import dev.mikita.darkforest.core.location.Location;
import dev.mikita.darkforest.core.location.LocationManager;
import dev.mikita.darkforest.core.sprite.SpriteManager;
import dev.mikita.darkforest.core.tile.TileMap;
import dev.mikita.darkforest.model.entity.Item.AItem;
import dev.mikita.darkforest.model.entity.Item.equipment.AEquipment;
import dev.mikita.darkforest.model.entity.Monster;
import dev.mikita.darkforest.model.entity.Player;
import dev.mikita.darkforest.model.entity.Portal;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * The type Game model.
 * <p>
 * The main class responsible for the state of the game (game objects).
 */
@Slf4j
public class GameModel {
    /**
     * The GameModel instance.
     */
    private static GameModel instance;

    /**
     * The Player.
     * -- GETTER --
     * Gets player.
     *
     * @return The player.
     */
    @Getter private Player player;

    /**
     * The Current location.
     * -- GETTER --
     * Gets current location.
     *
     * @return The current location.
     */
    @Getter private Location currentLocation;

    /**
     * The Location manager.
     */
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

        player.getHP().setInitialHealth(playerConfig.getDouble("initialHealth"));

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

        log.info("The location \"{}\" was set.", currentLocation.getName());
    }

    /**
     * Gets tile map.
     *
     * @return The tile map.
     */
    public TileMap getTileMap() {
        return currentLocation.getTileMap();
    }

    /**
     * Gets monsters.
     *
     * @return The monsters.
     */
    public ArrayList<Monster> getMonsters() {
        return currentLocation.getMonsters();
    }

    /**
     * Gets items.
     *
     * @return The items.
     */
    public ArrayList<AItem> getItems() {
        return currentLocation.getItems();
    }

    /**
     * Gets portals.
     *
     * @return The portals.
     */
    public ArrayList<Portal> getPortals() {
        return currentLocation.getPortals();
    }

    /**
     * Gets sprite manager.
     *
     * @return The sprite manager.
     */
    public SpriteManager getSpriteManager() {
        return currentLocation.getSpriteManager();
    }
}
