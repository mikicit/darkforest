package dev.mikicit.darkforest.core.sprite;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.Comparator;

public class SpriteManager {
    private final ArrayList<ASprite> sprites;

    public SpriteManager() {
        sprites = new ArrayList<>();
    }

    public void update(double delta) {
        sprites.sort(Comparator.comparing(ASprite::getY));

        for (ASprite sprite : sprites) {
            sprite.update(delta);
        }
    }

    public void render(GraphicsContext gc) {
        for (ASprite sprite : sprites) {
            sprite.render(gc);
        }
    }

    public void addSprite(ASprite sprite) {
        sprites.add(sprite);
    }

    public void removeSprite(ASprite sprite) {
        sprites.remove(sprite);
    }
}
