package dev.mikicit.darkforest.model.entity.Item.equipment;

import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Player;

/**
 * The type AEquipment.
 * <p>
 * An abstract class representing equipment and methods for interacting with it.
 */
public abstract class AEquipment extends AItem {
    /**
     * Instantiates a new A equipment.
     *
     * @param id   the id
     * @param name the name
     */
    public AEquipment(int id, String name) {
        super(id, name);
    }

    /**
     * Equip.
     * <p>
     * A method that allows you to equip an item.
     *
     * @param player the player
     */
    public void equip(Player player) {
        player.setEquipment(this);
    }

    /**
     * Un equip.
     * <p>
     * A method that allows you to unequip an item.
     *
     * @param player the player
     */
    public void unEquip(Player player) {
        player.unsetEquipment(this);
    }
}
