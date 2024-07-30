package dev.mikita.darkforest.core.location;

import dev.mikita.darkforest.core.factory.ItemFactory;
import dev.mikita.darkforest.core.factory.MonsterFactory;
import dev.mikita.darkforest.core.factory.PortalFactory;
import dev.mikita.darkforest.core.sprite.SpriteManager;
import dev.mikita.darkforest.core.tile.TileMap;
import dev.mikita.darkforest.core.tile.TileMapManager;
import dev.mikita.darkforest.model.entity.Item.AItem;
import dev.mikita.darkforest.model.entity.Monster;
import dev.mikita.darkforest.model.entity.Player;
import dev.mikita.darkforest.model.entity.Portal;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * The type Location.
 * <p>
 * A class representing a specific location.
 */
@Slf4j
public class Location {
    /**
     * The config.
     */
    private final JSONObject config;

    /**
     * The location id.
     *
     * @return The current location id.
     */
    @Getter private final int locationId;

    /**
     * The name.
     *
     * @return The current location name.
     */
    @Getter private final String name;

    /**
     * The player.
     *
     * @return The current player.
     */
    @Getter private Player player;

    /**
     * The monsters.
     *
     * @return The current monsters in the location.
     */
    @Getter private final ArrayList<Monster> monsters = new ArrayList<>();

    /**
     * The items.
     *
     * @return The current items in the location.
     */
    @Getter private final ArrayList<AItem> items = new ArrayList<>();

    /**
     * The portals.
     *
     * @return The current portals in the location.
     */
    @Getter private final ArrayList<Portal> portals = new ArrayList<>();

    /**
     * The tile map.
     *
     * @return The current tile map.
     */
    @Getter private TileMap tileMap;

    /**
     * The sprite manager.
     *
     * @return The current sprite manager.
     */
    @Getter private SpriteManager spriteManager;

    /**
     * The location was initialized.
     */
    private boolean wasInitialized;

    /**
     * Instantiates a new location.
     *
     * @param locationId The location id.
     */
    public Location(int locationId) {
        this.locationId = locationId;

        try {
            String content = new String(Files.readAllBytes(Path.of("config/location/" + locationId + "/config.json")));
            config = new JSONObject(content);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading location config", e);
        }

        this.name = config.getString("name");

        log.info("Location \"{}\" was created.", name);
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
        tileMap = TileMapManager.createTileMap("config/location/" + locationId + "/map.txt", 64);

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

        log.info("Location \"{}\" was initialized.", name);
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
}
