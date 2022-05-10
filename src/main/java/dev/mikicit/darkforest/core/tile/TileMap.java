package dev.mikicit.darkforest.core.tile;

import dev.mikicit.darkforest.core.Config;
import javafx.scene.canvas.GraphicsContext;

public class TileMap {
    private Tile[][] tiles;
    private int tileSize;
    private int mapWidth;
    private int mapHeight;

    public TileMap(int width, int height, int tileSize) {
        this.tiles = new Tile[width][height];
        this.tileSize = tileSize;
        this.mapWidth = getWidth() * tileSize;
        this.mapHeight = getHeight() * tileSize;
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x >= getWidth() ||
                y < 0 || y >= getHeight())
        {
            return null;
        } else {
            return tiles[x][y];
        }
    }

    public void setTile(int x, int y, Tile tile) {
        tiles[x][y] = tile;
    }

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

    public static int convertTileToPixel(int coord) {
        return coord * Config.getTileSize();
    }

    public static int convertPixelToTile(double coord) {
        return (int) (coord / Config.getTileSize());
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
}
