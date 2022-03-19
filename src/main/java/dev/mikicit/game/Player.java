package dev.mikicit.game;

public class Player {
    private final String name;
    private Inventory inventory;
    public Sprite sprite;

    public Player(String name) {
        this.name = name;
        sprite = new Sprite();
        sprite.setImage("sprite/player.png");
        inventory = new Inventory();
    }

    public void moveUp() {
        sprite.addVelocity(0, -50);
    }

    public void moveRight() {
        sprite.addVelocity(50, 0);
    }

    public void moveDown() {
        sprite.addVelocity(0, 50);
    }

    public void moveLeft() {
        sprite.addVelocity(-50, 0);
    }

    public String getName() {
        return name;
    }
}
