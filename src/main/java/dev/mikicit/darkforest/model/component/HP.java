package dev.mikicit.darkforest.model.component;

import java.util.Observable;

/**
 * The type Hp.
 * <p>
 * A class representing a character's lives and their management.
 */
public class HP extends Observable {
    private double initialHealth;
    private double currentHealth;

    /**
     * Instantiates a new Hp.
     *
     * @param health the health
     */
    public HP(double health) {
        this.initialHealth = health;
        this.currentHealth = health;
    }

    /**
     * Add health.
     *
     * @param health the health
     */
    public void addHealth(double health) {
        currentHealth = Math.min(currentHealth + health, initialHealth);
        setChanged();
        notifyObservers(Math.round(currentHealth));
    }

    /**
     * Reduce health.
     *
     * @param health the health
     */
    public void reduceHealth(double health) {
        currentHealth = Math.max(currentHealth - health, 0);
        setChanged();
        notifyObservers(Math.round(currentHealth));
    }

    /**
     * Sets initial health health.
     *
     * @param health the health
     */
    public void setInitialHealthHealth(double health) {
        this.initialHealth = health;
    }

    /**
     * Gets initial health.
     *
     * @return the initial health
     */
    public double getInitialHealth() {
        return initialHealth;
    }

    /**
     * Gets health.
     * <p>
     * Returns the current lives of the character.
     *
     * @return the health
     */
    public double getHealth() {
        return Math.round(currentHealth);
    }
}
