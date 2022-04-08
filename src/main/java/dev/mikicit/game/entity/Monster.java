package dev.mikicit.game.entity;

import dev.mikicit.game.model.Sprite;

public class Monster extends Sprite {
    String name;
    private double baseHealth = 150;
    private double currentHealth = 150;
    private double damageSize = 10;
    private double impactSpeed = 0.5;
    private double impactRadius = 30;

    public Monster(String name) {
        this.name = name;
        setImage("sprite/skeleton.png");
    }
}
