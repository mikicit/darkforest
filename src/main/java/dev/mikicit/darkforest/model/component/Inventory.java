package dev.mikicit.darkforest.model.component;

import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Player;

import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Logger;

public class Inventory extends Observable {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    private final int maxItems = 14;
    ArrayList<AItem> items = new ArrayList<>();

    public boolean addItem(AItem item) {
        if (!isFull()) {
            if (!isInInventory(item)) {
                items.add(item);
                setChanged();
                notifyObservers();

                log.info("Item \"" + item.getName() + "\" was added to inventory!");
                return true;
            } else {
                setChanged();
                notifyObservers();

                log.info("Item \"" + item.getName() + "\" is already in inventory!");
                return false;
            }
        } else {
            log.info("Inventory is full!");
            return false;
        }
    }

    public boolean removeItem(AItem item) {
        if (items.remove(item)) {
            setChanged();
            notifyObservers();
            log.info("Item \"" + item.getName() + "\" was deleted from inventory!");
            return true;
        };

        return false;
    }

    public boolean isInInventory(AItem item) {
        return items.contains(item);
    }

    public boolean isFull() {
        return maxItems == items.size();
    }

    public int getQuantity() {
        return items.size();
    }

    public int getCapacity() {
        return maxItems;
    }

    public ArrayList<AItem> getItems() {
        return items;
    }
}
