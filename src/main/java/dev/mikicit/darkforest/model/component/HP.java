package dev.mikicit.darkforest.model.component;

import java.util.Observable;

public class HP extends Observable {
    private final double initialHealth;
    private double currentHealth;

    public HP(double health) {
        this.initialHealth = health;
        this.currentHealth = health;
    }

    public void addHealth(double health) {
        currentHealth = Math.min(currentHealth + health, initialHealth);
        setChanged();
        notifyObservers(currentHealth);
    }

    public void setHealth(double health) {
        this.currentHealth = health;
        setChanged();
        notifyObservers(health);
    }

    public double getHealth() {
        return currentHealth;
    }
}
