package dev.mikicit.darkforest.model.entity.Item;

import java.util.ArrayList;

public class Inventory {
    private final int maxItems = 2;
    ArrayList<AItem> items = new ArrayList<>();

    public boolean addItem(AItem item) {
        if (!isFull()) {
            if (!isInInventory(item)) {
                items.add(item);
                System.out.println("Предмет " + item.getName() + " добавлен в инвентарь!");
                return true;
            } else {
                System.out.println("Предмет " + item.getName() + " уже в инвентаре!");
                return false;
            }
        } else {
            System.out.println("Инвентарь полный!");
            return false;
        }
    }

    public void removeItem(AItem item) {
        if (items.remove(item)) {
            System.out.println("Предмет " + item.getName() + " был удален из инвентаря!");
        };
    }

    public boolean isInInventory(AItem item) {
        return items.contains(item);
    }

    public boolean isFull() {
        return maxItems == items.size();
    }

    public void printItems() {
        System.out.println("Премдеты в инвентаре: ");
        for (AItem item : items) {
            System.out.println(item.getName());
        }
    }
}
