package dev.mikita.darkforest.model.entity.Item.bottle;

import dev.mikita.darkforest.model.entity.Item.AItem;
import dev.mikita.darkforest.model.entity.Player;
import lombok.Getter;

/**
 * The type Health bottle.
 * <p>
 * A class representing health bottles.
 */
@Getter
public class HealthBottle extends AItem {
    /**
     * The health.
     *
     * @return The amount of health that the bottle restores.
     */
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
     * @param player The player who uses the bottle.
     */
    public void use(Player player) {
        player.inHealth(this);
    }
}
