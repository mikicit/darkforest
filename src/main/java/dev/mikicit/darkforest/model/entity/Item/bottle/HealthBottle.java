package dev.mikicit.darkforest.model.entity.Item.bottle;

import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Player;

import java.util.logging.Logger;

public class HealthBottle extends AItem {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    private final double health;

    public HealthBottle(int id, String name, double health) {
        super(id, name);
        this.health = health;
    }

    public void use(Player player) {
        player.inHealth(this);
    }

    public double getHealth() {
        return health;
    }
}
