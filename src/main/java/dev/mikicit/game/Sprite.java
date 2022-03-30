package dev.mikicit.game;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Sprite {
    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private int priority;

    public Sprite() {
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
    }

    public void setImage(Image image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
    }

    public void setImage(String filename) {
        Image image = new Image(filename);
        setImage(image);
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }

    public Rectangle2D getCollisionBox() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public Rectangle2D getMoveBox() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean intersectsCollectionBox(Sprite s) {
        return s.getCollisionBox().intersects(this.getCollisionBox());
    }

    public boolean intersectsMoveBox(Sprite s) {
        return s.getCollisionBox().intersects(this.getMoveBox());
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return positionX;
    }

    public double getY() {
        return positionY;
    }

    public void moveUp() {
        addVelocity(0, -100);
    }

    public void moveRight() {
        addVelocity(100, 0);
    }

    public void moveDown() {
        addVelocity(0, 100);
    }

    public void moveLeft() {
        addVelocity(-100, 0);
    }

    public String toString() {
        return " Position: [" + positionX + "," + positionY + "]"
                + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
}
