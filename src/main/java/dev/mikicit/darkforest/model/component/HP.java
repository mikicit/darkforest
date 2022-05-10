package dev.mikicit.darkforest.model.component;

import java.util.Observable;

public class HP extends Observable {
    private double initialHealth;
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

    public void reduceHealth(double health) {
        currentHealth = Math.max(currentHealth - health, 0);
        setChanged();
        notifyObservers(currentHealth);
    }

    public void setHealth(double health) {
        this.currentHealth = health;
        setChanged();
        notifyObservers(health);
    }

    public void setInitialHealthHealth(double health) {
        this.initialHealth = health;
    }

    public double getInitialHealth() {
        return initialHealth;
    }

    public double getHealth() {
        return currentHealth;
    }
}
