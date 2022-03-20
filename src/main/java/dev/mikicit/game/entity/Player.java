package dev.mikicit.game.entity;

public class Player {
    private final String name;
    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        inventory = new Inventory();
    }

    public String getName() {
        return name;
    }
}
