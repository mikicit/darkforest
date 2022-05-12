package dev.mikicit.darkforest.model.entity;

import dev.mikicit.darkforest.core.sprite.ASprite;
import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.model.GameModel;

import java.util.logging.Logger;

public class Portal extends ASprite {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    private final int portalId;
    private final int locationId;
    private final int playerX;
    private final int playerY;

    public Portal(int portalId, int locationId, int playerX, int playerY) {
        this.portalId = portalId;
        this.locationId = locationId;
        this.playerX = playerX;
        this.playerY = playerY;
    }

    public void activate() {
        GameModel gameModel = GameModel.getInstance();
        Player player = gameModel.getPlayer();
        gameModel.setLocation(locationId);
        player.setPosition(
                TileMap.convertTileToPixel(playerX),
                TileMap.convertTileToPixel(playerY)
        );
    }

    public int getId() {
        return portalId;
    }
}
