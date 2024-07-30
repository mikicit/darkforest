package dev.mikita.darkforest.model.entity;

import dev.mikita.darkforest.core.sprite.ASprite;
import dev.mikita.darkforest.core.tile.TileMap;
import dev.mikita.darkforest.model.GameModel;
import lombok.Getter;

/**
 * The type Portal.
 * <p>
 * A class that represents a specific portal and methods for managing it.
 */
public class Portal extends ASprite {
    /**
     * The Portal id.
     * -- GETTER --
     * Gets portal id.
     *
     * @return The portal id.
     */
    @Getter private final int portalId;

    /**
     * The Location id.
     * -- GETTER --
     * Gets location id.
     *
     * @return The location id.
     */
    @Getter  private final int locationId;

    /**
     * The Player x.
     */
    private final int playerX;

    /**
     * The Player y.
     */
    private final int playerY;

    /**
     * Instantiates a new Portal.
     *
     * @param portalId   The portal id.
     * @param locationId The location id.
     * @param playerX    The player x.
     * @param playerY    The player y.
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
}
