package dev.mikicit.darkforest.model.component;

import dev.mikicit.darkforest.model.entity.Item.equipment.AEquipment;
import dev.mikicit.darkforest.model.entity.Item.equipment.Armor;
import dev.mikicit.darkforest.model.entity.Item.equipment.Weapon;
import dev.mikicit.darkforest.model.entity.Player;

import java.util.Observable;
import java.util.logging.Logger;

/**
 * The type Equipment.
 * <p>
 * A class that represents a character's equipment (equipped items) and their management.
 */
public class Equipment extends Observable {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    private Weapon weapon;
    private Armor armor;

    /**
     * Sets equipment.
     *
     * @param item      the item
     * @param inventory the inventory
     */
    public void setEquipment(AEquipment item, Inventory inventory) {
        inventory.removeItem(item);

        if (item instanceof Weapon) {
            if (weapon != null) {
                inventory.addItem(weapon);
            }

            weapon = (Weapon) item;
            log.info("Weapon \"" + item.getName() + "\" was equipped.");
        }

        if (item instanceof Armor) {
            if (armor != null) {
                inventory.addItem(armor);
            }

            armor = (Armor) item;
            log.info("Armor \"" + item.getName() + "\" was equipped.");
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Unset equipment.
     *
     * @param item      the item
     * @param inventory the inventory
     */
    public void unsetEquipment(AEquipment item, Inventory inventory) {
        if (item instanceof Weapon) {
            if (weapon == null) return;
            if (weapon.equals(item)) {
                if (inventory.addItem(item)) {
                    weapon = null;
                    log.info("Weapon \"" + item.getName() + "\" was unequipped.");
                }
            }
        }

        if (item instanceof Armor) {
            if (armor == null) return;
            if (armor.equals(item)) {
                if (inventory.addItem(item)) {
                    armor = null;
                    log.info("Weapon \"" + item.getName() + "\" was unequipped.");
                }
            }
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Gets weapon.
     *
     * @return the weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Gets armor.
     *
     * @return the armor
     */
    public Armor getArmor() {
        return armor;
    }
}
