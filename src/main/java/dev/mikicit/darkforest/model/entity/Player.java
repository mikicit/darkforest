package dev.mikicit.darkforest.model.entity;

import dev.mikicit.darkforest.core.sprite.ASprite;
import dev.mikicit.darkforest.model.component.HP;
import dev.mikicit.darkforest.model.component.Inventory;
import dev.mikicit.darkforest.model.entity.Item.bottle.HealthBottle;
import dev.mikicit.darkforest.model.entity.Item.equipment.AEquipment;
import dev.mikicit.darkforest.model.entity.Item.equipment.Armor;
import dev.mikicit.darkforest.model.entity.Item.equipment.Weapon;

public class Player extends ASprite {
    private final String name;
    private final HP health;
    private final double basicDamage;
    private final double basicArmor;
    private final double damageRadius;
    private boolean isDead;

    // Inventory
    private final Inventory inventory;

    // Equipped Items
    private Weapon currentWeapon = null;
    private Armor currentArmor = null;

    public Player(String name, double health, double damage, double armor, double damageRadius) {
        this.name = name;
        this.health = new HP(health);
        this.basicDamage = damage;
        this.basicArmor = armor;
        this.damageRadius = damageRadius;
        this.inventory = new Inventory();
        this.isDead = false;

        setImage("player/player.png");

        System.out.println("Создан игрок!");
    }

    public void attack(Monster monster) {
        if (!monster.isDead()) {
            System.out.println("Игрок " + name + " атаковал монстра " + monster.getName() + "!");
            monster.inAttack(this);
        }
    }

    public void inAttack(Monster monster) {
//        System.out.println("Игрок " + name + " был атакован монстром " + monster.getName() + "!");
//        currentHealth = Math.max(currentHealth - monster.getDamage(), 0);
//        if (currentHealth == 0) {
//            isDead = true;
//            System.out.println("Игрок " + name + " погиб!");
//        }
    }

    public void inHealth(HealthBottle healthBottle) {
        if (!inventory.isInInventory(healthBottle)) return;
        health.addHealth(healthBottle.getHealth());
        inventory.removeItem(healthBottle);

        System.out.println("Было исползовано зелье " + healthBottle.getName() + "! Актуальное здоровье: " + getHealth() + "!");
    }

    public void setEquipment(AEquipment item) {
        if (!inventory.isInInventory(item)) return;
        inventory.removeItem(item);

        if (item instanceof Weapon) {
            if (currentWeapon != null) {
                inventory.addItem(currentWeapon);
            }

            currentWeapon = (Weapon) item;
        }

        if (item instanceof Armor) {
            if (currentArmor != null) {
                inventory.addItem(currentArmor);
            }

            currentArmor = (Armor) item;
        }
    }

    public void unsetEquipment(AEquipment item) {
        if (item instanceof Weapon) {
            if (currentWeapon == null) return;
            if (currentWeapon.equals(item)) {
                if (inventory.addItem(item)) {
                    currentWeapon = null;
                    System.out.println("Предмте " + item.getName() + " был снят!");
                }
            };
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public double getHealth() {
        return health.getHealth();
    }

    public double getDamage() {
        if (currentWeapon == null) return basicDamage;
        return basicDamage + currentWeapon.getDamage();
    }

    public double getArmor() {
        if (currentArmor == null) return basicArmor;
        return basicArmor + currentArmor.getArmor();
    }

    public String getName() {
        return name;
    }

    public boolean isDead() {
        return isDead;
    }

    public HP getHP() {
        return health;
    }
}