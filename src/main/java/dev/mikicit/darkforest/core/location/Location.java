package dev.mikicit.darkforest.core.location;

import dev.mikicit.darkforest.core.factory.ItemFactory;
import dev.mikicit.darkforest.core.factory.MonsterFactory;
import dev.mikicit.darkforest.core.factory.PortalFactory;
import dev.mikicit.darkforest.core.sprite.SpriteManager;
import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.core.tile.TileMapManager;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Monster;
import dev.mikicit.darkforest.model.entity.Player;
import dev.mikicit.darkforest.model.entity.Portal;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * The type Location.
 * <p>
 * A class representing a specific location.
 */
public class Location {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    // Data
    private JSONObject config;
    private final int locationId;
    private final String name;

    // Links
    private Player player;
    private ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<AItem> items = new ArrayList<>();
    private ArrayList<Portal> portals = new ArrayList<>();
    private TileMap tileMap;
    private SpriteManager spriteManager;

    // State
    private boolean wasInitialized;

    /**
     * Instantiates a new Location.
     *
     * @param locationId the location id
     */
    public Location(int locationId) {
        this.locationId = locationId;

        try {
            File file = new File("src/main/resources/location/" + locationId + "/config.json");
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            config = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.name = config.getString("name");

        log.info("Location \"" + getName() + "\" was created.");
    }

    /**
     * Init.
     * <p>
     * Location initialization.
     */
    public void init() {
        if (wasInitialized) return;

        // Sprite Manager Init
        spriteManager = new SpriteManager();

        // Tile Map Init
        tileMap = TileMapManager.createTileMap("src/main/resources/location/" + locationId + "/map.txt", 64);

        // Monsters Init
        for (Object monsterConfig : config.getJSONArray("monsters")) {
            Monster monster = MonsterFactory.getMonster(((JSONObject) monsterConfig).getInt("id"));
            monster.setPosition(
                    TileMap.convertTileToPixel(((JSONObject) monsterConfig).getInt("positionX")),
                    TileMap.convertTileToPixel(((JSONObject) monsterConfig).getInt("positionY")));

            monsters.add(monster);
            spriteManager.addSprite(monster);
        }

        // Items Init
        for (Object itemConfig : config.getJSONArray("items")) {
            AItem item = ItemFactory.getItem(((JSONObject) itemConfig).getInt("id"));
            item.setPosition(
                    TileMap.convertTileToPixel(((JSONObject) itemConfig).getInt("positionX")),
                    TileMap.convertTileToPixel(((JSONObject) itemConfig).getInt("positionY")));

            items.add(item);
            spriteManager.addSprite(item);
        }

        // Portals Init
        for (Object portalConfig : config.getJSONArray("portals")) {
            Portal portal = PortalFactory.getPortal(((JSONObject) portalConfig).getInt("id"));

            portal.setPosition(
                    TileMap.convertTileToPixel(((JSONObject) portalConfig).getInt("positionX")),
                    TileMap.convertTileToPixel(((JSONObject) portalConfig).getInt("positionY")));

            portals.add(portal);
            spriteManager.addSprite(portal);
        }

        wasInitialized = true;

        log.info("Location \"" + getName() + "\" was initialized.");
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.player = player;
        spriteManager.addSprite(player);
    }

    /**
     * Unset player.
     */
    public void unsetPlayer() {
        spriteManager.removeSprite(player);
        this.player = null;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return locationId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
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
     * Gets monsters.
     *
     * @return the monsters
     */
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public ArrayList<AItem> getItems() {
        return items;
    }

    /**
     * Gets portals.
     *
     * @return the portals
     */
    public ArrayList<Portal> getPortals() {
        return portals;
    }

    /**
     * Gets tile map.
     *
     * @return the tile map
     */
    public TileMap getTileMap() {
        return tileMap;
    }

    /**
     * Gets sprite manager.
     *
     * @return the sprite manager
     */
    public SpriteManager getSpriteManager() {
        return spriteManager;
    }
}
