package dev.mikicit.game;

public class Player {
    private final String name;
    private int x = 0;
    private int y = 0;

    public Player(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public void moveUp() {
        y++;
    }

    public void moveRight() {
        x++;
    }

    public void moveDown() {
        y--;
    }

    public void moveLeft() {
        x--;
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
