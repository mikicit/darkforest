package dev.mikicit.game;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class SpriteManager {
    ArrayList<Sprite> sprites;

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }

    public void update(double time) {
        for (Sprite sprite : sprites) {
            sprite.update(time);
        }
    }

    public void render(GraphicsContext gc) {
        for (Sprite sprite : sprites) {
            sprite.render(gc);
        }
    }
}
