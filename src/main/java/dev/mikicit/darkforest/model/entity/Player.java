package dev.mikicit.darkforest.model.entity;

import dev.mikicit.darkforest.core.sprite.ASprite;
import dev.mikicit.darkforest.model.component.HP;
import dev.mikicit.darkforest.model.component.Inventory;
import dev.mikicit.darkforest.model.entity.Item.bottle.HealthBottle;
import dev.mikicit.darkforest.model.entity.Item.equipment.AEquipment;
import dev.mikicit.darkforest.model.entity.Item.equipment.Armor;
import dev.mikicit.darkforest.model.entity.Item.equipment.Weapon;
import javafx.scene.image.Image;

import java.util.HashMap;

public class Player extends ASprite {
    private enum Direction {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT
    }

    private final HashMap<Direction, Image> images = new HashMap<>();
    private Direction currentDirection;
    private final String name;
    private final HP health;
    private final double basicDamage;
    private final double basicArmor;
    private final double damageRadius;
    private double speed = 1;
    private boolean isDead;

    // Inventory
    private final Inventory inventory;

    // Equipped Items
    private Weapon currentWeapon = null;
    private Armor currentArmor = null;

    public Player(String name, double health, double damage, double armor, double damageRadius) {
        super();
        this.name = name;
        this.health = new HP(health);
        this.basicDamage = damage;
        this.basicArmor = armor;
        this.damageRadius = damageRadius;
        this.inventory = new Inventory();
        this.isDead = false;

        images.put(Direction.TOP, new Image("player/player_top.png"));
        images.put(Direction.RIGHT, new Image("player/player_right.png"));
        images.put(Direction.BOTTOM, new Image("player/player_bottom.png"));
        images.put(Direction.LEFT, new Image("player/player_left.png"));

        currentDirection = Direction.BOTTOM;
        setImage(images.get(currentDirection));

        System.out.println("Player was created!");
    }

    public void attack(Monster monster) {
        if (!monster.isDead()) {
            System.out.println("Player attacked " + name + " a monster " + monster.getName() + "!");
            monster.inAttack(this);
        }
    }

    public void inAttack(Monster monster) {
        System.out.println("Player " + name + " was attacked by monster " + monster.getName() + "!");
        health.reduceHealth(monster.getDamage());
        if (health.getHealth() == 0) {
            isDead = true;
            System.out.println("Player is " + name + " dead!");
        }
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

    public double getHealth() {
        return health.getHealth();
    }

    public HP getHP() {
        return health;
    }

    // Moving
    public void moveUp() {
        if (currentDirection != Direction.TOP) {
            currentDirection = Direction.TOP;
            setImage(images.get(currentDirection));
        }
        positionY -= speed;
    }

    public void moveRight() {
        if (currentDirection != Direction.RIGHT) {
            currentDirection = Direction.RIGHT;
            setImage(images.get(currentDirection));
        }
        positionX += speed;
    }

    public void moveDown() {
        if (currentDirection != Direction.BOTTOM) {
            currentDirection = Direction.BOTTOM;
            setImage(images.get(currentDirection));
        }
        positionY += speed;
    }

    public void moveLeft() {
        if (currentDirection != Direction.LEFT) {
            currentDirection = Direction.LEFT;
            setImage(images.get(currentDirection));
        }
        positionX -= speed;
    }
}
