package dev.mikicit.darkforest.core.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tile {
    private final Image image;
    private final boolean passable;

    public Tile(String imagePath, boolean passable) {
        this.passable = passable;
        this.image = new Image(imagePath);
    }

    public Image getImage() {
        return image;
    }

    public void render(GraphicsContext gc, double x, double y) {
        gc.drawImage(image, x, y);
    }

    public boolean isPassable() {
        return passable;
    }
}

