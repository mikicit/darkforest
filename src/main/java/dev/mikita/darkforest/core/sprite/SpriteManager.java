package dev.mikita.darkforest.core.sprite;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * The type Sprite manager.
 * <p>
 * Class for managing all sprites in the location.
 */
public class SpriteManager {
    /**
     * The sprites.
     */
    private final ArrayList<ASprite> sprites;

    /**
     * Instantiates a new Sprite manager.
     */
    public SpriteManager() {
        sprites = new ArrayList<>();
    }

    /**
     * Update.
     * <p>
     * Calling the update method on all sprites.
     *
     * @param delta The time between the last frame and the current one.
     */
    public void update(double delta) {
        // Sorting to display some sprites on top of others depending on the coordinates.
        sprites.sort(Comparator.comparing(ASprite::getPositionY));

        for (ASprite sprite : sprites) {
            sprite.update(delta);
        }
    }

    /**
     * Render.
     *
     * @param gc The graphics context.
     */
    public void render(GraphicsContext gc) {
        for (ASprite sprite : sprites) {
            sprite.render(gc);
        }
    }

    /**
     * Add sprite.
     *
     * @param sprite The sprite.
     */
    public void addSprite(ASprite sprite) {
        sprites.add(sprite);
    }

    /**
     * Remove sprite.
     *
     * @param sprite The sprite.
     */
    public void removeSprite(ASprite sprite) {
        sprites.remove(sprite);
    }
}
