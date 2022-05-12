package dev.mikicit.darkforest.model.entity;

import dev.mikicit.darkforest.core.sprite.ASprite;
import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.model.GameModel;

import java.util.logging.Logger;

/**
 * The type Portal.
 * <p>
 * A class that represents a specific portal and methods for managing it.
 */
public class Portal extends ASprite {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    private final int portalId;
    private final int locationId;
    private final int playerX;
    private final int playerY;

    /**
     * Instantiates a new Portal.
     *
     * @param portalId   the portal id
     * @param locationId the location id
     * @param playerX    the player x
     * @param playerY    the player y
     */
    public Portal(int portalId, int locationId, int playerX, int playerY) {
        this.portalId = portalId;
        this.locationId = locationId;
        this.playerX = playerX;
        this.playerY = playerY;
    }

    /**
     * Activate.
     * <p>
     * The method that activates the portal. Changes location.
     */
    public void activate() {
        GameModel gameModel = GameModel.getInstance();
        Player player = gameModel.getPlayer();
        gameModel.setLocation(locationId);
        player.setPosition(
                TileMap.convertTileToPixel(playerX),
                TileMap.convertTileToPixel(playerY)
        );
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return portalId;
    }
}
