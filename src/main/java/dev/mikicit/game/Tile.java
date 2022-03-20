package dev.mikicit.game;

import javafx.scene.image.Image;

public class Tile {
    private final Image image;
    private final int id;
    private final boolean passable;

    public Tile(Image image, int id, boolean passable) {
        this.passable = passable;
        this.id = id;
        this.image = image;
    }

    public Tile(String imagePath, int id, boolean passable) {
        this.passable = passable;
        this.id = id;
        image = new Image(imagePath);
    }

    public Image getImage() {
        return image;
    }
}
