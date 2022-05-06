package dev.mikicit.darkforest.model.component;

import dev.mikicit.darkforest.model.entity.Item.AItem;

import java.util.ArrayList;
import java.util.Observable;

public class Inventory extends Observable {
    private final int maxItems = 2;
    ArrayList<AItem> items = new ArrayList<>();

    public boolean addItem(AItem item) {
        if (!isFull()) {
            if (!isInInventory(item)) {
                items.add(item);
                System.out.println("Предмет " + item.getName() + " добавлен в инвентарь!");
                setChanged();
                notifyObservers();
                return true;
            } else {
                System.out.println("Предмет " + item.getName() + " уже в инвентаре!");
                setChanged();
                notifyObservers();
                return false;
            }
        } else {
            System.out.println("Инвентарь полный!");
            return false;
        }
    }

    public boolean removeItem(AItem item) {
        if (items.remove(item)) {
            setChanged();
            notifyObservers();
            System.out.println("Предмет " + item.getName() + " был удален из инвентаря!");
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

    public ArrayList<AItem> getItems() {
        return items;
    }
}
