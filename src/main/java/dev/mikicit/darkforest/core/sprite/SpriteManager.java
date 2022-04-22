package dev.mikicit.darkforest.core.sprite;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Comparator;

public class SpriteManager {
    private final ArrayList<Sprite> sprites;

    public SpriteManager() {
        sprites = new ArrayList<>();
    }

    public void update(double delta) {
        sprites.sort(Comparator.comparing(Sprite::getY));

        for (Sprite sprite : sprites) {
            sprite.update(delta);
        }
    }

    public void render(GraphicsContext gc) {
        for (Sprite sprite : sprites) {
            sprite.render(gc);
        }
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }
}
