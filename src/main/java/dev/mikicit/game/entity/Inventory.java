package dev.mikicit.game.entity;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Item> items;

    public Inventory() {

    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}
