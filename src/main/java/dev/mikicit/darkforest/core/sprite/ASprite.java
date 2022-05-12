package dev.mikicit.darkforest.core.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The type ASprite.
 * <p>
 * The base abstract class for representing sprites.
 */
public abstract class ASprite {
    /**
     * The Position x.
     */
    protected double positionX;
    /**
     * The Position y.
     */
    protected double positionY;
    /**
     * The Width.
     */
    protected double width;
    /**
     * The Height.
     */
    protected double height;
    /**
     * The Image.
     */
    protected Image image;

    /**
     * Instantiates a new A sprite.
     */
    public ASprite() {
        positionX = 0;
        positionY = 0;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(Image image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
    }

    /**
     * Sets image.
     *
     * @param filename the filename
     */
    public void setImage(String filename) {
        Image image = new Image(filename);
        setImage(image);
    }

    /**
     * Sets position.
     *
     * @param x the x
     * @param y the y
     */
    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    /**
     * Update.
     * <p>
     * This method is called every frame of the main timer.
     * Used to update the state of the sprite.
     *
     * @param delta the delta
     */
    public void update(double delta) {

    };

    /**
     * Render.
     * <p>
     * The render method is called every frame. Serves for drawing sprites.
     *
     * @param gc the gc
     */
    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }

    /**
     * Intersects collision box boolean.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean intersectsCollisionBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getCollisionBox());
    }

    /**
     * Gets collision box.
     *
     * @return the collision box
     */
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return positionX;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return positionY;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return image;
    }
}

