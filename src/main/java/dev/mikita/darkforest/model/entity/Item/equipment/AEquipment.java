package dev.mikita.darkforest.model.entity.Item.equipment;

import dev.mikita.darkforest.model.entity.Item.AItem;
import dev.mikita.darkforest.model.entity.Player;

/**
 * The type AEquipment.
 * <p>
 * An abstract class representing equipment and methods for interacting with it.
 */
public abstract class AEquipment extends AItem {
    /**
     * Instantiates a new AEquipment.
     *
     * @param id   The equipment id.
     * @param name The equipment name.
     */
    public AEquipment(int id, String name) {
        super(id, name);
    }

    /**
     * Equip.
     * <p>
     * A method that allows you to equip an item.
     *
     * @param player The player who will equip the item.
     */
    public void equip(Player player) {
        player.setEquipment(this);
    }

    /**
     * Un equip.
     * <p>
     * A method that allows you to unequip an item.
     *
     * @param player The player who will unequip the item.
     */
    public void unEquip(Player player) {
        player.unsetEquipment(this);
    }
}
