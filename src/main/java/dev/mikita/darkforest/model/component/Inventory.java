package dev.mikita.darkforest.model.component;

import dev.mikita.darkforest.model.entity.Item.AItem;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Observable;

/**
 * The type Inventory.
 * <p>
 * A class representing a character's inventory and managing it.
 */
@Slf4j
@Getter
@SuppressWarnings("deprecation")
public class Inventory extends Observable {
    /**
     * The capacity.
     *
     * @return The capacity.
     */
    private final int capacity;

    /**
     * The Items.
     *
     * @return The items.
     */
    private final ArrayList<AItem> items = new ArrayList<>();

    /**
     * Instantiates a new Inventory.
     *
     * @param capacity The capacity.
     */
    public Inventory(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Add item boolean.
     *
     * @param item The item.
     * @return The boolean whether the item was added.
     */
    public boolean addItem(AItem item) {
        if (!isFull()) {
            if (!isInInventory(item)) {
                items.add(item);
                setChanged();
                notifyObservers();

                log.info("Item \"{}\" was added to inventory!", item.getName());
                return true;
            } else {
                log.info("Item \"{}\" is already in inventory!", item.getName());
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
     * @param item The item.
     * @return The boolean whether the item was removed.
     */
    public boolean removeItem(AItem item) {
        if (items.remove(item)) {
            setChanged();
            notifyObservers();
            log.info("Item \"{}\" was deleted from inventory!", item.getName());
            return true;
        };

        return false;
    }

    /**
     * Is in inventory boolean.
     *
     * @param item The item.
     * @return The boolean whether the item is in inventory.
     */
    public boolean isInInventory(AItem item) {
        return items.contains(item);
    }

    /**
     * Is full boolean.
     *
     * @return The boolean whether the inventory is full.
     */
    public boolean isFull() {
        return capacity == items.size();
    }

    /**
     * Gets quantity.
     *
     * @return The quantity of items in inventory.
     */
    public int getQuantity() {
        return items.size();
    }
}
