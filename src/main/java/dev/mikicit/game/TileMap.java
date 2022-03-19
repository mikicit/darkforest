package dev.mikicit.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.LinkedList;
import java.util.Iterator;

public class TileMap {
    private Image[][] tiles;

    public TileMap(int width, int height) {
        tiles = new Image[width][height];
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

    public Image getTile(int x, int y) {
        if (x < 0 || x >= getWidth() ||
            y < 0 || y >= getHeight())
        {
            return null;
        } else {
            return tiles[x][y];
        }
    }

    public void setTile(int x, int y, Image tile) {
        tiles[x][y] = tile;
    }

    public void render(GraphicsContext gc) {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Image tile = getTile(i, j);
                if (tile != null) {
                    gc.drawImage(tile, j * 64, i * 64);
                }
            }
        }
    }
}
