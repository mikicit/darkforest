package dev.mikita.darkforest.core.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lombok.Getter;

/**
 * The type ASprite.
 * <p>
 * The base abstract class for representing sprites.
 */
@Getter
public abstract class ASprite {
    /**
     * The position x.
     *
     * @return The position x.
     */
    protected double positionX;
    /**
     * The position y.
     *
     * @return The position y.
     */
    protected double positionY;
    /**
     * The width.
     *
     * @return The width.
     */
    protected double width;
    /**
     * The weight.
     *
     * @return The weight.
     */
    protected double height;
    /**
     * The image.
     *
     * @return The image.
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
     * @param image The image.
     */
    public void setImage(Image image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
    }

    /**
     * Sets image.
     *
     * @param filename The filename.
     */
    public void setImage(String filename) {
        Image image = new Image(filename);
        setImage(image);
    }

    /**
     * Sets position.
     *
     * @param x The x.
     * @param y The y.
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
     * @param delta The delta time between frames.
     */
    public void update(double delta) {};

    /**
     * Render.
     * <p>
     * The render method is called every frame. Serves for drawing sprites.
     *
     * @param gc The graphics context of the canvas.
     */
    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }

    /**
     * Intersects collision box boolean.
     *
     * @param s The sprite.
     * @return The boolean. Whether the sprite intersects with the collision box of the current sprite.
     */
    public boolean intersectsCollisionBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getCollisionBox());
    }

    /**
     * Gets collision box.
     *
     * @return The collision box.
     */
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D(positionX, positionY, width, height);
    }
}