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

        for (ASprite ASprite : sprites) {
            ASprite.update(delta);
        }
    }

    public void render(GraphicsContext gc) {
        for (ASprite ASprite : sprites) {
            ASprite.render(gc);
        }
    }

    public void addSprite(ASprite ASprite) {
        sprites.add(ASprite);
    }

    public void removeSprite(ASprite ASprite) {
        sprites.remove(ASprite);
    }
}
