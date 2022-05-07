package dev.mikicit.darkforest.model.entity;

import dev.mikicit.darkforest.core.sprite.ASprite;

public class Monster extends ASprite {
    private final String name;
    private final double damage;
    private final double damageRadius;
    private final double viewingRadius;
    private double health;
    private boolean isDead;

    public Monster(String name, double health, double damage, double damageRadius, double viewingRadius) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.damageRadius = damageRadius;
        this.viewingRadius = viewingRadius;
    }

    public void attack(Player player) {
        if (!player.isDead()) {
            System.out.println("Монстр " + name + " атаковал игрока " + player.getName() + "!");
            player.inAttack(this);
        }
    }

    public void inAttack(Player player) {
        System.out.println("Монстр " + name + " был атакован игроком " + player.getName() + "!");
        health = Math.max(health - player.getDamage(), 0);
        if (health == 0) {
            isDead = true;
            System.out.println("Монстр " + name + " погиб!");
        }
    }

    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public boolean isDead() {
        return isDead;
    }
}
