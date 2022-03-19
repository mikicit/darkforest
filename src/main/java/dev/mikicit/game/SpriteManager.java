package dev.mikicit.game;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

public class SpriteManager {
    private ArrayList<Sprite> sprites;
    private Sprite player;

    public void addPlayer(Sprite sprite) {
        player = sprite;
    }

    public Sprite getPlayer() {
        return player;
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }

    public void update(double time) {
        if (sprites != null) {
            for (Sprite sprite : sprites) {
                sprite.update(time);
            }
        }

        player.update(time);
    }

    public void render(GraphicsContext gc) {
        if (sprites != null) {
            for (Sprite sprite : sprites) {
                sprite.render(gc);
            }
        }

        player.render(gc);
    }
}
