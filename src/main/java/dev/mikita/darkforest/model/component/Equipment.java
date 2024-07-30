package dev.mikita.darkforest.model.component;

import dev.mikita.darkforest.model.entity.Item.equipment.AEquipment;
import dev.mikita.darkforest.model.entity.Item.equipment.Armor;
import dev.mikita.darkforest.model.entity.Item.equipment.Weapon;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.util.Observable;

/**
 * The type Equipment.
 * <p>
 * A class that represents a character's equipment (equipped items) and their management.
 */
@Slf4j
@Getter
@SuppressWarnings("deprecation")
public class Equipment extends Observable {
    /**
     * The Weapon.
     */
    private Weapon weapon;

    /**
     * The Armor.
     */
    private Armor armor;

    /**
     * Sets equipment.
     *
     * @param item      The item.
     * @param inventory The inventory.
     */
    public void setEquipment(AEquipment item, Inventory inventory) {
        inventory.removeItem(item);

        if (item instanceof Weapon) {
            if (weapon != null) {
                inventory.addItem(weapon);
            }

            weapon = (Weapon) item;
            log.info("Weapon \"{}\" was equipped.", item.getName());
        }

        if (item instanceof Armor) {
            if (armor != null) {
                inventory.addItem(armor);
            }

            armor = (Armor) item;
            log.info("Armor \"{}\" was equipped.", item.getName());
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Unset equipment.
     *
     * @param item      The item.
     * @param inventory The inventory.
     */
    public void unsetEquipment(AEquipment item, Inventory inventory) {
        if (item instanceof Weapon) {
            if (weapon == null) return;
            if (weapon.equals(item)) {
                if (inventory.addItem(item)) {
                    weapon = null;
                    log.info("Weapon \"{}\" was unequipped.", item.getName());
                }
            }
        }

        if (item instanceof Armor) {
            if (armor == null) return;
            if (armor.equals(item)) {
                if (inventory.addItem(item)) {
                    armor = null;
                    log.info("Armor \"{}\" was unequipped.", item.getName());
                }
            }
        }

        setChanged();
        notifyObservers();
    }
}
