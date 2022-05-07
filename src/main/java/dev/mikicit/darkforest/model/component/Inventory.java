package dev.mikicit.darkforest.model.component;

import dev.mikicit.darkforest.model.entity.Item.AItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

public class Inventory extends Observable {
    private final int maxItems = 14;
    ArrayList<AItem> items = new ArrayList<>();

    public boolean addItem(AItem item) {
        if (!isFull()) {
            if (!isInInventory(item)) {
                items.add(item);
                System.out.println(new Date() + ": Item \"" + item.getName() + "\" was added to inventory!");
                setChanged();
                notifyObservers();
                return true;
            } else {
                System.out.println(new Date() + ": Item \"" + item.getName() + "\" is already in inventory!");
                setChanged();
                notifyObservers();
                return false;
            }
        } else {
            System.out.println(new Date() + ": Inventory is full!");
            return false;
        }
    }

    public boolean removeItem(AItem item) {
        if (items.remove(item)) {
            setChanged();
            notifyObservers();
            System.out.println(new Date() + ": Item \"" + item.getName() + "\" was deleted from inventory!");
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
