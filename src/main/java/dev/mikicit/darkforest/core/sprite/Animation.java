package dev.mikicit.darkforest.core.sprite;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

/**
 * The type Animated sprite.
 */
public class Animation {
    // Links
    private final Image imageSet;
    private Image oldImage;
    private ASprite sprite;

    // Config
    private final double animationTime; // in ms
    private final double animationTimePerFrame; // in ms
    private final double frameWidth;
    private final double frameHeight;
    private final int numberOfFrames;

    // State
    private double lastNanoTime;
    private int currentFrameIndex = 0;
    private boolean isAnimating = false;

    public Animation(Image image, double animationTime, int numberOfFrames, double frameWidth, double frameHeight) {
        imageSet = image;
        this.animationTime = animationTime;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.numberOfFrames = numberOfFrames;
        this.animationTimePerFrame = this.animationTime / numberOfFrames;
    }

    public Animation(String path, double animationTime, int numberOfFrames, double frameWidth, double frameHeight) {
        imageSet = new Image(path);
        this.animationTime = animationTime;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.numberOfFrames = numberOfFrames;
        this.animationTimePerFrame = animationTime / numberOfFrames;
    }

    public void play(ASprite sprite) {
        isAnimating = true;
        this.sprite = sprite;
        oldImage = sprite.getImage();
    }

    public void stop() {
        isAnimating = false;
        currentFrameIndex = 0;
        sprite = null;
    }

    private Image getNextFrame() {
        if (currentFrameIndex < numberOfFrames) {
            PixelReader pr = imageSet.getPixelReader();
            Image frame = new WritableImage(pr, (int) (currentFrameIndex * frameWidth), 0, (int) frameWidth, (int) frameHeight);
            currentFrameIndex++;
            return frame;
        }

        return null;
    }

    public void update() {
        if (isAnimating) {
            double currentNanoTime = System.nanoTime();

            if ((currentNanoTime - lastNanoTime) / 1000000 > animationTimePerFrame) {
                lastNanoTime = currentNanoTime;

                Image image;
                if ((image = getNextFrame()) != null) {
                    sprite.setImage(image);
                    return;
                }
                sprite.setImage(oldImage);
                stop();
            }
        }
    }
}
