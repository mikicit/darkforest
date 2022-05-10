package dev.mikicit.darkforest.core.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.HashMap;

public abstract class ASprite {
    protected double positionX;
    protected double positionY;
    protected double width;
    protected double height;
    protected Image image;
    protected HashMap<String, Animation> animations = new HashMap<>();

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

//    public void setAnimation(String name, Animation animation) {
//        animations.put(name, animation);
//    }
//
//    public void playAnimation(String name) {
//        Animation animation = animations.get(name);
//        if (animation != null) {
//            animations.get(name).play(this);
//        }
//    }

    public void update(double delta) {
        animations.forEach((key, value) -> {
            value.update(delta);
        });
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }

    // Intersections
    public boolean intersectsCollisionBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getCollisionBox());
    }

    // Getters
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D(positionX, positionY, width, height);
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

