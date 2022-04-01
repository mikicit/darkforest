package dev.mikicit.game.model;

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
