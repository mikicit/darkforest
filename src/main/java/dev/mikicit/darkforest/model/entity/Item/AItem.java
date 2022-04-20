package dev.mikicit.darkforest.model.entity.Item;

import dev.mikicit.darkforest.model.entity.Player;

public abstract class AItem {
    protected final String name;

    public AItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void take(Player player) {
        player.getInventory().addItem(this);
    }

    public void drop(Player player) {
        player.getInventory().removeItem(this);
    }
}
