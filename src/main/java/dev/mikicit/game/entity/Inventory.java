package dev.mikicit.game.entity;

import dev.mikicit.game.entity.item.AItem;

import java.util.ArrayList;

public class Inventory {
    ArrayList<AItem> items;

    public Inventory() {

    }

    public void addItem(AItem item) {
        items.add(item);
    }

    public void removeItem(AItem item) {
        items.remove(item);
    }
}
