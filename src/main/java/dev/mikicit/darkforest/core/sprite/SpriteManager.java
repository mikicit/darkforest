package dev.mikicit.darkforest.core.sprite;

import java.util.ArrayList;

public class SpriteManager {
    private ArrayList<Sprite> sprites;

    public SpriteManager() {
        sprites = new ArrayList<>();
    }

    public void update(double delta) {


        for (Sprite sprite : sprites) {
            sprite.update(delta);
        }
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }
}
