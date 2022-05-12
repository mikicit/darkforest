package dev.mikicit.darkforest.model.component;

import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Player;

import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Logger;

/**
 * The type Inventory.
 * <p>
 * A class representing a character's inventory and managing it.
 */
public class Inventory extends Observable {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    private final int maxItems = 14;
    /**
     * The Items.
     */
    ArrayList<AItem> items = new ArrayList<>();

    /**
     * Add item boolean.
     *
     * @param item the item
     * @return the boolean
     */
    public boolean addItem(AItem item) {
        if (!isFull()) {
            if (!isInInventory(item)) {
                items.add(item);
                setChanged();
                notifyObservers();

                log.info("Item \"" + item.getName() + "\" was added to inventory!");
                return true;
            } else {
                log.info("Item \"" + item.getName() + "\" is already in inventory!");
                return false;
            }
        } else {
            log.info("Inventory is full!");
            return false;
        }
    }

    /**
     * Remove item boolean.
     *
     * @param item the item
     * @return the boolean
     */
    public boolean removeItem(AItem item) {
        if (items.remove(item)) {
            setChanged();
            notifyObservers();
            log.info("Item \"" + item.getName() + "\" was deleted from inventory!");
            return true;
        };

        return false;
    }

    /**
     * Is in inventory boolean.
     *
     * @param item the item
     * @return the boolean
     */
    public boolean isInInventory(AItem item) {
        return items.contains(item);
    }

    /**
     * Is full boolean.
     *
     * @return the boolean
     */
    public boolean isFull() {
        return maxItems == items.size();
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return items.size();
    }

    /**
     * Gets capacity.
     *
     * @return the capacity
     */
    public int getCapacity() {
        return maxItems;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public ArrayList<AItem> getItems() {
        return items;
    }
}
