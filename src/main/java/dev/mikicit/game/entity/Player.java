package dev.mikicit.game.entity;

import dev.mikicit.game.model.Sprite;

public class Player extends Sprite {
    private final String name;
    private final Inventory inventory;
    private int health = 100;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
        setImage("sprite/player.png");
    }
}
