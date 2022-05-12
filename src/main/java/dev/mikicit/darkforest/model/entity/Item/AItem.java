package dev.mikicit.darkforest.model.entity.Item;

import dev.mikicit.darkforest.core.sprite.ASprite;
import dev.mikicit.darkforest.model.entity.Player;

/**
 * The type AItem.
 * <p>
 * An abstract class representing an element and basic methods for interacting with it.
 */
public abstract class AItem extends ASprite {
    /**
     * The Id.
     */
    protected final int id;
    /**
     * The Name.
     */
    protected final String name;

    /**
     * Instantiates a new A item.
     *
     * @param id   the id
     * @param name the name
     */
    public AItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Take boolean.
     * <p>
     * A method that allows a character to take it.
     *
     * @param player the player
     * @return the boolean
     */
    public boolean take(Player player) {
        return player.getInventory().addItem(this);
    }

    /**
     * Drop boolean.
     * <p>
     * A method that allows a character to drop an item.
     *
     * @param player the player
     * @return the boolean
     */
    public boolean drop(Player player) {
        return player.getInventory().removeItem(this);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
