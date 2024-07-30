package dev.mikita.darkforest.core.tile;

import dev.mikita.darkforest.core.Config;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;

/**
 * The type Tile map.
 * <p>
 * A class representing a specific map (tilemap) as well as auxiliary methods for working with it.
 */
public class TileMap {
    /**
     * The tiles.
     */
    private final Tile[][] tiles;

    /**
     * The tile size.
     */
    private final int tileSize;

    /**
     * The map width.
     * -- GETTER --
     * Gets the map width.
     *
     * @return The map width.
     */
    @Getter private final int mapWidth;

    /**
     * The map height.
     * -- GETTER --
     * Gets the map height.
     *
     * @return The map height.
     */
    @Getter  private final int mapHeight;

    /**
     * Instantiates a new Tile map.
     *
     * @param width    The width.
     * @param height   The height.
     * @param tileSize The tile size.
     */
    public TileMap(int width, int height, int tileSize) {
        this.tiles = new Tile[width][height];
        this.tileSize = tileSize;
        this.mapWidth = getWidth() * tileSize;
        this.mapHeight = getHeight() * tileSize;
    }

    /**
     * Gets width.
     *
     * @return The width.
     */
    public int getWidth() {
        return tiles.length;
    }

    /**
     * Gets height.
     *
     * @return The height.
     */
    public int getHeight() {
        return tiles[0].length;
    }

    /**
     * Gets tile.
     * <p>
     * Returns a specific tile by coordinates.
     *
     * @param x The x.
     * @param y The y.
     * @return The tile.
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || x >= getWidth() ||
                y < 0 || y >= getHeight())
        {
            return null;
        } else {
            return tiles[x][y];
        }
    }

    /**
     * Sets tile.
     * <p>
     * Sets the tile by coordinates.
     *
     * @param x    The x.
     * @param y    The y.
     * @param tile The tile.
     */
    public void setTile(int x, int y, Tile tile) {
        tiles[x][y] = tile;
    }

    /**
     * Render.
     *
     * @param gc The graphics context.
     */
    public void render(GraphicsContext gc) {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Tile tile = getTile(j, i);
                if (tile != null) {
                    tile.render(gc, convertTileToPixel(j), convertTileToPixel(i));
                }
            }
        }
    }

    /**
     * Convert tile to pixel int.
     * <p>
     * Convert tile coordinates to pixel coordinates.
     *
     * @param coord The coordinates.
     * @return The int.
     */
    public static int convertTileToPixel(int coord) {
        return coord * Config.getTileSize();
    }

    /**
     * Convert pixel to tile int.
     * <p>
     * Convert pixel coordinates to tile coordinates.
     *
     * @param coord The coordinates.
     * @return The int.
     */
    public static int convertPixelToTile(double coord) {
        return (int) (coord / Config.getTileSize());
    }
}
