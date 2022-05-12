package dev.mikicit.darkforest.core.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The type Tile.
 * <p>
 * A class representing a specific tile.
 */
public class Tile {
    private final Image image;
    private final boolean passable;

    /**
     * Instantiates a new Tile.
     *
     * @param imagePath the image path
     * @param passable  the passable
     */
    public Tile(String imagePath, boolean passable) {
        this.passable = passable;
        this.image = new Image(imagePath);
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Render.
     *
     * @param gc the gc
     * @param x  the x
     * @param y  the y
     */
    public void render(GraphicsContext gc, double x, double y) {
        gc.drawImage(image, x, y);
    }

    /**
     * Is passable boolean.
     *
     * @return the boolean
     */
    public boolean isPassable() {
        return passable;
    }
}

