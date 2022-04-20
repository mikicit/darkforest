package dev.mikicit.darkforest.model.entity;

import dev.mikicit.darkforest.core.sprite.Sprite;

public class Monster extends Sprite {
    private final String name;
    private final double basicHealth;
    private double currentHealth;
    private final double armor;
    private final double damage;
    private final double damageRadius;
    private final double viewingRadius;
    private boolean isDead;

    public Monster(String name, double health, double armor, double damage, double damageRadius, double viewingRadius) {
        this.name = name;
        this.basicHealth = health;
        this.currentHealth = health;
        this.armor = armor;
        this.damage = damage;
        this.damageRadius = damageRadius;
        this.viewingRadius = viewingRadius;
        this.isDead = false;

        setImage("monster/monster.png");
    }

    public void attack(Player player) {
        if (!player.isDead()) {
            System.out.println("Монстр " + name + " атаковал игрока " + player.getName() + "!");
            player.inAttack(this);
        }
    }

    public void inAttack(Player player) {
        System.out.println("Монстр " + name + " был атакован игроком " + player.getName() + "!");
        currentHealth = Math.max(currentHealth - player.getDamage(), 0);
        if (currentHealth == 0) {
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
