package dev.mikicit.darkforest.model.entity;

import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.core.sprite.ASprite;
import dev.mikicit.darkforest.model.component.Equipment;
import dev.mikicit.darkforest.model.component.HP;
import dev.mikicit.darkforest.model.component.Inventory;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Item.bottle.HealthBottle;
import dev.mikicit.darkforest.model.entity.Item.equipment.AEquipment;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * The type Player.
 * <p>
 * The class represents the character and the methods to control it.
 */
public class Player extends ASprite {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    private enum Direction {
        /**
         * Top direction.
         */
        TOP,
        /**
         * Right direction.
         */
        RIGHT,
        /**
         * Bottom direction.
         */
        BOTTOM,
        /**
         * Left direction.
         */
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
    private final Equipment equipment;

    /**
     * Instantiates a new Player.
     *
     * @param name         the name
     * @param health       the health
     * @param damage       the damage
     * @param armor        the armor
     * @param damageRadius the damage radius
     */
    public Player(String name, double health, double damage, double armor, double damageRadius) {
        super();
        this.name = name;
        this.health = new HP(health);
        this.basicDamage = damage;
        this.basicArmor = armor;
        this.damageRadius = damageRadius;
        this.inventory = new Inventory(14);
        this.equipment = new Equipment();
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

    /**
     * Attack.
     *
     * @param monster the monster
     */
    public void attack(Monster monster) {
        if ((System.currentTimeMillis() - lastAttack) < attackSpeed) {
            return;
        }

        lastAttack = System.currentTimeMillis(); // cooldown

        if (!monster.isDead()) {
            monster.inAttack(this);
        }
    }

    /**
     * In attack.
     *
     * @param monster the monster
     */
    public void inAttack(Monster monster) {
        double incomingDamage = monster.getDamage() * (100/(100 + getArmor())); // Given the character's armor.

        log.info("Player was attacked by monster \"" + monster.getName() + "\"." + "Incoming damage is " + incomingDamage + ".");
        health.reduceHealth(incomingDamage);
        if (health.getHealth() == 0) {
            isDead = true;
            log.info("Player is dead!");

            StateManager.gameOver();
        }
    }

    /**
     * In health.
     *
     * @param healthBottle the health bottle
     */
    public void inHealth(HealthBottle healthBottle) {
        if (!inventory.isInInventory(healthBottle)) return;
        health.addHealth(healthBottle.getHealth());
        inventory.removeItem(healthBottle);

        log.info("The potion \"" + healthBottle.getName() + "\" was used. " + healthBottle.getHealth() + " health has been added.");
    }

    /**
     * Sets equipment.
     *
     * @param item the item
     */
    public void setEquipment(AEquipment item) {
        equipment.setEquipment(item, inventory);
    }

    /**
     * Unset equipment.
     *
     * @param item the item
     */
    public void unsetEquipment(AEquipment item) {
        equipment.unsetEquipment(item, inventory);
    }

    /**
     * Gets inventory.
     *
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Gets equipment.
     *
     * @return the equipment
     */
    public Equipment getEquipment() {
        return equipment;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public double getDamage() {
        if (equipment.getWeapon() == null) return basicDamage;
        return basicDamage + equipment.getWeapon().getDamage();
    }

    /**
     * Gets basic damage.
     *
     * @return the basic damage
     */
    public double getBasicDamage() {
        return basicDamage;
    }

    /**
     * Gets armor.
     *
     * @return the armor
     */
    public double getArmor() {
        if (equipment.getArmor() == null) return basicArmor;
        return basicArmor + equipment.getArmor().getArmor();
    }

    /**
     * Gets basic armor.
     *
     * @return the basic armor
     */
    public double getBasicArmor() {
        return basicArmor;
    }

    /**
     * Gets damage radius.
     *
     * @return the damage radius
     */
    public double getDamageRadius() {
        if (equipment.getWeapon() == null) return basicDamage;
        return damageRadius + equipment.getWeapon().getRadius();
    }

    /**
     * Gets basic damage radius.
     *
     * @return the basic damage radius
     */
    public double getBasicDamageRadius() {
        return damageRadius;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public double getHealth() {
        return health.getHealth();
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public HP getHP() {
        return health;
    }

    /**
     * Gets current weapon.
     *
     * @return the current weapon
     */
    public AItem getCurrentWeapon() {
        return equipment.getWeapon();
    }

    /**
     * Gets current armor.
     *
     * @return the current armor
     */
    public AItem getCurrentArmor() {
        return equipment.getArmor();
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
    public boolean isDead() {
        return isDead;
    }

    // Intersections

    /**
     * Intersects attack box boolean.
     * <p>
     * Checking the intersection with the attack area.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean intersectsAttackBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getAttackCollisionBox());
    }

    /**
     * Intersects move box boolean.
     * <p>
     * Checking the intersection with the legs.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean intersectsMoveBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getMoveBox());
    }

    /**
     * Gets attack collision box.
     * <p>
     * Getting an attack area depending on the direction and damage radius of the character.
     *
     * @return the attack collision box
     */
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

    /**
     * Gets move box.
     * <p>
     * Getting the leg area of the character.
     *
     * @return the move box
     */
    public Rectangle2D getMoveBox() {
        return new Rectangle2D(positionX, positionY + height - 16, width, 16);
    }

    /**
     * Move up.
     *
     * @param path the path
     */
    public void moveUp(int path) {
        if (currentDirection != Direction.TOP) {
            currentDirection = Direction.TOP;
            setImage(images.get(currentDirection));
        }
        positionY -= path;
    }

    /**
     * Move right.
     *
     * @param path the path
     */
    public void moveRight(int path) {
        if (currentDirection != Direction.RIGHT) {
            currentDirection = Direction.RIGHT;
            setImage(images.get(currentDirection));
        }
        positionX += path;
    }

    /**
     * Move down.
     *
     * @param path the path
     */
    public void moveDown(int path) {
        if (currentDirection != Direction.BOTTOM) {
            currentDirection = Direction.BOTTOM;
            setImage(images.get(currentDirection));
        }
        positionY += path;
    }

    /**
     * Move left.
     *
     * @param path the path
     */
    public void moveLeft(int path) {
        if (currentDirection != Direction.LEFT) {
            currentDirection = Direction.LEFT;
            setImage(images.get(currentDirection));
        }
        positionX -= path;
    }
}