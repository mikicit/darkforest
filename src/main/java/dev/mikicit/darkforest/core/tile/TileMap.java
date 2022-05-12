package dev.mikicit.darkforest.core.tile;

import dev.mikicit.darkforest.core.Config;
import javafx.scene.canvas.GraphicsContext;

/**
 * The type Tile map.
 * <p>
 * A class representing a specific map (tilemap) as well as auxiliary methods for working with it.
 */
public class TileMap {
    private Tile[][] tiles;
    private int tileSize;
    private int mapWidth;
    private int mapHeight;

    /**
     * Instantiates a new Tile map.
     *
     * @param width    the width
     * @param height   the height
     * @param tileSize the tile size
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
     * @return the width
     */
    public int getWidth() {
        return tiles.length;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return tiles[0].length;
    }

    /**
     * Gets tile.
     * <p>
     * Returns a specific tile by coordinates.
     *
     * @param x the x
     * @param y the y
     * @return the tile
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
     * @param x    the x
     * @param y    the y
     * @param tile the tile
     */
    public void setTile(int x, int y, Tile tile) {
        tiles[x][y] = tile;
    }

    /**
     * Render.
     *
     * @param gc the gc
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
     * @param coord the coord
     * @return the int
     */
    public static int convertTileToPixel(int coord) {
        return coord * Config.getTileSize();
    }

    /**
     * Convert pixel to tile int.
     * <p>
     * Convert pixel coordinates to tile coordinates.
     *
     * @param coord the coord
     * @return the int
     */
    public static int convertPixelToTile(double coord) {
        return (int) (coord / Config.getTileSize());
    }

    /**
     * Gets map width.
     *
     * @return the map width
     */
    public int getMapWidth() {
        return mapWidth;
    }

    /**
     * Gets map height.
     *
     * @return the map height
     */
    public int getMapHeight() {
        return mapHeight;
    }
}
