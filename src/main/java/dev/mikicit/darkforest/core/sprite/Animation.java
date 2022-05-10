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
    private PixelReader pr;

    // Config
    private final double animationTime; // in ms
    private final double animationTimePerFrame; // in ms
    private final double frameWidth;
    private final double frameHeight;
    private final int numberOfFrames;

    // State
    private double lastNanoTime;
    private int currentFrameNumber = 0;
    private boolean isAnimating = false;
    private double timeFromLastFrame;

    public Animation(Image image, double animationTime, int numberOfFrames, double frameWidth, double frameHeight) {
        imageSet = image;
        this.animationTime = animationTime;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.numberOfFrames = numberOfFrames;
        this.animationTimePerFrame = this.animationTime / numberOfFrames;
        pr = imageSet.getPixelReader();
    }

    public Animation(String path, double animationTime, int numberOfFrames, double frameWidth, double frameHeight) {
        imageSet = new Image(path);
        this.animationTime = animationTime;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.numberOfFrames = numberOfFrames;
        this.animationTimePerFrame = animationTime / numberOfFrames;
        pr = imageSet.getPixelReader();
    }

    public void play(ASprite sprite) {
        isAnimating = true;
        this.sprite = sprite;
        oldImage = sprite.getImage();
    }

    public void stop() {
        isAnimating = false;
        currentFrameNumber = 0;
        sprite = null;
        timeFromLastFrame = 0;
    }

    private Image getNextFrame() {
        if (currentFrameNumber < numberOfFrames) {
            Image frame = new WritableImage(pr, (int) (currentFrameNumber * frameWidth), 0, (int) frameWidth, (int) frameHeight);
            currentFrameNumber++;
            return frame;
        }

        return null;
    }

    public void update(double delta) {
        if (isAnimating) {
//            double currentNanoTime = System.nanoTime();
//
//            if ((currentNanoTime - lastNanoTime) / 1000000 > animationTimePerFrame) {
//                lastNanoTime = currentNanoTime;
//
//                Image image;
//                if ((image = getNextFrame()) != null) {
//                    sprite.setImage(image);
//                    return;
//                }
//                sprite.setImage(oldImage);
//                stop();
//            }

            timeFromLastFrame += delta;
            if (timeFromLastFrame > animationTimePerFrame / 300) {
                timeFromLastFrame = 0;

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
