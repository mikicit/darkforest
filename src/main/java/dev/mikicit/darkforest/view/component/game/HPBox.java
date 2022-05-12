package dev.mikicit.darkforest.view.component.game;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.Observable;
import java.util.Observer;

/**
 * The type Hp box.
 * <p>
 * A GUI element that displays the character's actual lives.
 */
public class HPBox implements Observer {
    private final Text health;

    /**
     * Instantiates a new Hp box.
     *
     * @param health the health
     */
    public HPBox(double health) {
        this.health = new Text(50, 50, "HP: " + health);
        this.health.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        this.health.setFill(Color.WHITE);
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public Text getText() {
        return health;
    }

    @Override
    public void update(Observable o, Object health) {
        this.health.setText("HP: " + health);
    }
}