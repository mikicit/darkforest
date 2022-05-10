package dev.mikicit.darkforest.model.entity;

import dev.mikicit.darkforest.core.sprite.ASprite;
import dev.mikicit.darkforest.model.component.HP;
import dev.mikicit.darkforest.model.component.Inventory;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Item.bottle.HealthBottle;
import dev.mikicit.darkforest.model.entity.Item.equipment.AEquipment;
import dev.mikicit.darkforest.model.entity.Item.equipment.Armor;
import dev.mikicit.darkforest.model.entity.Item.equipment.Weapon;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.logging.Logger;

public class Player extends ASprite {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    private enum Direction {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT
    }

    private final HashMap<Direction, Image> images = new HashMap<>();

    // State
    private Direction currentDirection;
    private boolean isDead;
    private double lastAttack;

    // Characteristics
    private final String name;
    private final HP health;
    private final double basicDamage;
    private final double basicArmor;
    private final double damageRadius;
    private double speed = 160; // px per second
    private double attackSpeed = 300; // in ms

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

        // Setting Up Direction Images
        images.put(Direction.TOP, new Image("player/player_top.png"));
        images.put(Direction.RIGHT, new Image("player/player_right.png"));
        images.put(Direction.BOTTOM, new Image("player/player_bottom.png"));
        images.put(Direction.LEFT, new Image("player/player_left.png"));

        // Setting Up Default Image
        currentDirection = Direction.BOTTOM;
        setImage(images.get(currentDirection));

        log.info("Player was created!");
    }

    public void attack(Monster monster) {
        if ((System.currentTimeMillis() - lastAttack) < attackSpeed) {
            return;
        }

        lastAttack = System.currentTimeMillis(); // cooldown

        if (!monster.isDead()) {
            monster.inAttack(this);
        }
    }

    public void inAttack(Monster monster) {
        double incomingDamage = monster.getDamage() * (100/(100 + getArmor())); // Given the character's armor.

        log.info("Player was attacked by monster \"" + monster.getName() + "\"." + "Incoming damage is " + incomingDamage + ".");
        health.reduceHealth(incomingDamage);
        if (health.getHealth() == 0) {
            isDead = true;
            log.info("Player is dead!");
        }
    }

    public void inHealth(HealthBottle healthBottle) {
        if (!inventory.isInInventory(healthBottle)) return;
        health.addHealth(healthBottle.getHealth());
        inventory.removeItem(healthBottle);

        log.info("The potion \"" + healthBottle.getName() + "\" was used. " + healthBottle.getHealth() + " health has been added.");
    }

    public void setEquipment(AEquipment item) {
        inventory.removeItem(item);

        if (item instanceof Weapon) {
            if (currentWeapon != null) {
                inventory.addItem(currentWeapon);
            }

            currentWeapon = (Weapon) item;
            log.info("Weapon \"" + item.getName() + "\" was equipped.");
        }

        if (item instanceof Armor) {
            if (currentArmor != null) {
                inventory.addItem(currentArmor);
            }

            currentArmor = (Armor) item;
            log.info("Armor \"" + item.getName() + "\" was equipped.");
        }
    }

    public void unsetEquipment(AEquipment item) {
        if (item instanceof Weapon) {
            if (currentWeapon == null) return;
            if (currentWeapon.equals(item)) {
                if (inventory.addItem(item)) {
                    currentWeapon = null;
                    log.info("Weapon \"" + item.getName() + "\" was unequipped.");
                }
            }
        }

        if (item instanceof Armor) {
            if (currentArmor == null) return;
            if (currentArmor.equals(item)) {
                if (inventory.addItem(item)) {
                    currentArmor = null;
                    log.info("Weapon \"" + item.getName() + "\" was unequipped.");
                }
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public double getDamage() {
        if (currentWeapon == null) return basicDamage;
        return basicDamage + currentWeapon.getDamage();
    }

    public double getBasicDamage() {
        return basicDamage;
    }

    public double getArmor() {
        if (currentArmor == null) return basicArmor;
        return basicArmor + currentArmor.getArmor();
    }

    public double getBasicArmor() {
        return basicArmor;
    }

    public double getDamageRadius() {
        if (currentWeapon == null) return basicDamage;
        return damageRadius + currentWeapon.getRadius();
    }

    public double getBasicDamageRadius() {
        return damageRadius;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health.getHealth();
    }

    public HP getHP() {
        return health;
    }

    public AItem getCurrentWeapon() {
        return currentWeapon;
    }

    public AItem getCurrentArmor() {
        return currentArmor;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isDead() {
        return isDead;
    }

    // Intersections

    // Checking the intersection with the attack area.
    public boolean intersectsAttackBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getAttackCollisionBox());
    }

    // Checking the intersection with the legs.
    public boolean intersectsMoveBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getMoveBox());
    }

    // Getting an attack area depending on the direction and damage radius of the character.
    public Rectangle2D getAttackCollisionBox() {
        switch (currentDirection) {
            case TOP:
                return new Rectangle2D(positionX, positionY - getDamageRadius(), width, getDamageRadius());
            case RIGHT:
                return new Rectangle2D(positionX + width, positionY, getDamageRadius(), height);
            case BOTTOM:
                return new Rectangle2D(positionX, positionY + height, width, getDamageRadius());
            case LEFT:
                return new Rectangle2D(positionX - getDamageRadius(), positionY, getDamageRadius(), height);
        }

        return new Rectangle2D(positionX, positionY - getDamageRadius(), width, getDamageRadius());
    }

    // Getting the leg area of the character.
    public Rectangle2D getMoveBox() {
        return new Rectangle2D(positionX, positionY + height - 16, width, 16);
    }

    // Moving
    public void moveUp(int path) {
        if (currentDirection != Direction.TOP) {
            currentDirection = Direction.TOP;
            setImage(images.get(currentDirection));
        }
        positionY -= path;
    }

    public void moveRight(int path) {
        if (currentDirection != Direction.RIGHT) {
            currentDirection = Direction.RIGHT;
            setImage(images.get(currentDirection));
        }
        positionX += path;
    }

    public void moveDown(int path) {
        if (currentDirection != Direction.BOTTOM) {
            currentDirection = Direction.BOTTOM;
            setImage(images.get(currentDirection));
        }
        positionY += path;
    }

    public void moveLeft(int path) {
        if (currentDirection != Direction.LEFT) {
            currentDirection = Direction.LEFT;
            setImage(images.get(currentDirection));
        }
        positionX -= path;
    }
}