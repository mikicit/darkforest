package dev.mikicit.darkforest.model.entity;

import dev.mikicit.darkforest.core.sprite.ASprite;
import java.util.logging.Logger;

public class Monster extends ASprite {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

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
            player.inAttack(this);
        }
    }

    public void inAttack(Player player) {
        double incomingDamage = player.getDamage();
        log.info("Monster \"" + getName() + "\" was attacked by player." + " Incoming damage is " + incomingDamage + ".");
        health = Math.max(health - incomingDamage, 0);

//        playAnimation("inattack");

        if (health == 0) {
            isDead = true;
            log.info("Monster \"" + name + "\" is dead!");
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
