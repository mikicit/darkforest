package dev.mikicit.darkforest.model.entity.Item.bottle;

import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Player;

import java.util.logging.Logger;

/**
 * The type Health bottle.
 * <p>
 * A class representing health bottles.
 */
public class HealthBottle extends AItem {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    private final double health;

    /**
     * Instantiates a new Health bottle.
     *
     * @param id     the id
     * @param name   the name
     * @param health the health
     */
    public HealthBottle(int id, String name, double health) {
        super(id, name);
        this.health = health;
    }

    /**
     * Use.
     * <p>
     * A method that allows a character to use a bottle.
     *
     * @param player the player
     */
    public void use(Player player) {
        player.inHealth(this);
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public double getHealth() {
        return health;
    }
}
