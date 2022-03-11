package dev.mikicit.game;

import javafx.scene.image.Image;

public class Sprite {
    private Image image;
    private int x;
    private int y;
    private int width;
    private int height;
    private int priority;

    public Sprite(String url) {
        image = new Image(url);
    }

    public Image getFrame() {
        return image;
    }
}
