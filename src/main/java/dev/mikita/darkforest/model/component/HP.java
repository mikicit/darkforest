package dev.mikita.darkforest.model.component;

import lombok.Getter;
import lombok.Setter;
import java.util.Observable;

/**
 * The type Hp.
 * <p>
 * A class representing a character's lives and their management.
 */
@SuppressWarnings("deprecation")
public class HP extends Observable {
    /**
     * The Initial health.
     *
     * @param initialHealth The initial health.
     * @return The initial health.
     */
    @Getter @Setter private double initialHealth;

    /**
     * The current health.
     */
    private double currentHealth;

    /**
     * Instantiates a new Hp.
     *
     * @param health The health.
     */
    public HP(double health) {
        this.initialHealth = health;
        this.currentHealth = health;
    }

    /**
     * Add health.
     *
     * @param health The health.
     */
    public void addHealth(double health) {
        currentHealth = Math.min(currentHealth + health, initialHealth);
        setChanged();
        notifyObservers(Math.round(currentHealth));
    }

    /**
     * Reduce health.
     *
     * @param health The health.
     */
    public void reduceHealth(double health) {
        currentHealth = Math.max(currentHealth - health, 0);
        setChanged();
        notifyObservers(Math.round(currentHealth));
    }

    /**
     * Gets health.
     * <p>
     * Returns the current lives of the character.
     *
     * @return The health.
     */
    public double getHealth() {
        return Math.round(currentHealth);
    }
}
