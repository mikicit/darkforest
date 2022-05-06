package dev.mikicit.darkforest.core.sprite;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Comparator;

public class SpriteManager {
    private final ArrayList<ASprite> ASprites;

    public SpriteManager() {
        ASprites = new ArrayList<>();
    }

    public void update(double delta) {
        ASprites.sort(Comparator.comparing(ASprite::getY));

        for (ASprite ASprite : ASprites) {
            ASprite.update(delta);
        }
    }

    public void render(GraphicsContext gc) {
        for (ASprite ASprite : ASprites) {
            ASprite.render(gc);
        }
    }

    public void addSprite(ASprite ASprite) {
        ASprites.add(ASprite);
    }

    public void removeSprite(ASprite ASprite) {
        ASprites.remove(ASprite);
    }
}
