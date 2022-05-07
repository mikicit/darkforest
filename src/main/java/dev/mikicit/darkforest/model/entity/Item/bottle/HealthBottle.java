package dev.mikicit.darkforest.model.entity.Item.bottle;

import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Player;

public class HealthBottle extends AItem {
    private final double health;

    public HealthBottle(String name, double health) {
        super(name);
        this.health = health;
    }

    public void use(Player player) {
        player.inHealth(this);
    }

    public double getHealth() {
        return health;
    }
}
