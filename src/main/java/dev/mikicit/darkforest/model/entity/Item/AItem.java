package dev.mikicit.darkforest.model.entity.Item;

import dev.mikicit.darkforest.core.sprite.ASprite;
import dev.mikicit.darkforest.model.entity.Player;

public abstract class AItem extends ASprite {
    protected final int id;
    protected final String name;

    public AItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean take(Player player) {
        return player.getInventory().addItem(this);
    }

    public boolean drop(Player player) {
        return player.getInventory().removeItem(this);
    }

    public int getId() {
        return id;
    }
}
