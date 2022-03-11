package dev.mikicit.game;

public class Player {
    private final String name;
    private int x = 0;
    private int y = 0;
    private int speed = 20;
    public Sprite sprite;

    public Player(String name) {
        this.name = name;
        sprite = new Sprite("images/player.png");
    }

    public void moveUp() {
        y -= speed;
    }

    public void moveRight() {
        x -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x += speed;
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }
}
