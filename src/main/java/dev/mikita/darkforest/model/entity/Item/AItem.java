package dev.mikita.darkforest.model.entity.Item;

import dev.mikita.darkforest.core.sprite.ASprite;
import dev.mikita.darkforest.model.entity.Player;
import lombok.Getter;

/**
 * The type AItem.
 * <p>
 * An abstract class representing an element and basic methods for interacting with it.
 */
@Getter
public abstract class AItem extends ASprite {
    /**
     * The item id.
     * -- GETTER --
     * Gets item id.
     *
     * @return The item id.
     */
    protected final int id;

    /**
     * The item name.
     * -- GETTER --
     * Gets item name.
     *
     * @return The item name.
     */
    protected final String name;

    /**
     * Instantiates a new item.
     *
     * @param id   The item id.
     * @param name The item name.
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
     * @param player The player who takes the item.
     * @return The boolean that indicates whether the item was taken.
     */
    public boolean take(Player player) {
        return player.getInventory().addItem(this);
    }

    /**
     * Drop boolean.
     * <p>
     * A method that allows a character to drop an item.
     *
     * @param player The player who drops the item.
     * @return The boolean that indicates whether the item was dropped.
     */
    public boolean drop(Player player) {
        return player.getInventory().removeItem(this);
    }
}
