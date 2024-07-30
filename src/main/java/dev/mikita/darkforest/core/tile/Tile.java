package dev.mikita.darkforest.core.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lombok.Getter;

/**
 * The type Tile.
 * <p>
 * A class representing a specific tile.
 */
public class Tile {
    /**
     * The tile image.
     * -- GETTER --
     * Gets the image.
     *
     * @return The image.
     */
    @Getter private final Image image;

    /**
     * The passable.
     */
    private final boolean passable;

    /**
     * Instantiates a new tile.
     *
     * @param imagePath The image path.
     * @param passable  The passable.
     */
    public Tile(String imagePath, boolean passable) {
        this.passable = passable;
        this.image = new Image(imagePath);
    }

    /**
     * Render.
     * <p>
     * Renders the tile.
     *
     * @param gc The graphics context.
     * @param x  The x.
     * @param y  The y.
     */
    public void render(GraphicsContext gc, double x, double y) {
        gc.drawImage(image, x, y);
    }

    /**
     * Is passable boolean.
     *
     * @return The boolean. Whether the tile is passable.
     */
    public boolean isPassable() {
        return passable;
    }
}

