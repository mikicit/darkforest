package dev.mikicit.darkforest.core.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class ASprite {
    protected double positionX;
    protected double positionY;
    protected double width;
    protected double height;
    protected Image image;

    public ASprite() {
        positionX = 0;
        positionY = 0;
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

    public void update(double time) {

    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }

    // Intersections
    public boolean intersectsCollectionBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getCollisionBox());
    }

    public boolean intersectsMoveBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getMoveBox());
    }

    // Getters
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public Rectangle2D getMoveBox() {
        return new Rectangle2D(positionX, positionY + height - 16, width, 16);
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

    public Image getImage() {
        return image;
    }
}

