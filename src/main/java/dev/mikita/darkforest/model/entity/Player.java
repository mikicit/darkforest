package dev.mikita.darkforest.model.entity;

import dev.mikita.darkforest.core.StateManager;
import dev.mikita.darkforest.core.sprite.ASprite;
import dev.mikita.darkforest.model.component.Equipment;
import dev.mikita.darkforest.model.component.HP;
import dev.mikita.darkforest.model.component.Inventory;
import dev.mikita.darkforest.model.entity.Item.AItem;
import dev.mikita.darkforest.model.entity.Item.bottle.HealthBottle;
import dev.mikita.darkforest.model.entity.Item.equipment.AEquipment;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * The type Player.
 * <p>
 * The class represents the character and the methods to control it.
 */
@Slf4j
public class Player extends ASprite {
    /**
     * The enum Direction.
     */
    private enum Direction {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT
    }

    /**
     * The Images.
     * <p>
     * The images of the character in different directions.
     */
    private final HashMap<Direction, Image> images = new HashMap<>();

    /**
     * The Current direction.
     * <p>
     * The current direction of the character.
     */
    private Direction currentDirection;

    /**
     * The Is dead.
     * <p>
     * The flag of the character's death.
     */
    private boolean isDead;

    /**
     * The Last attack.
     * <p>
     * The time of the last attack.
     */
    private double lastAttack;

    // Characteristics
    /**
     * The Name.
     * -- GETTER --
     * Gets the name.
     *
     * @return The name.
     */
    @Getter private final String name;

    /**
     * The Health.
     */
    private final HP health;

    /**
     * The Basic damage.
     * -- GETTER --
     * Gets the basic damage.
     *
     * @return The basic damage.
     */
    @Getter private final double basicDamage;

    /**
     * The Basic armor.
     * -- GETTER --
     * Gets the basic armor.
     *
     * @return The basic armor.
     */
    @Getter private final double basicArmor;
    private final double damageRadius;

    /**
     * The Speed.
     * -- GETTER --
     * Gets the speed.
     *
     * @return The speed.
     */
    @Getter private final double speed = 160; // px per second

    /**
     * The Attack speed.
     */
    private final double attackSpeed = 300; // in ms

    // Inventory
    @Getter private final Inventory inventory;

    // Equipped Items
    @Getter private final Equipment equipment;

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
        images.put(Direction.TOP, new Image(Path.of("config/player/player_top.png").toUri().toString()));
        images.put(Direction.RIGHT, new Image(Path.of("config/player/player_right.png").toUri().toString()));
        images.put(Direction.BOTTOM, new Image(Path.of("config/player/player_bottom.png").toUri().toString()));
        images.put(Direction.LEFT, new Image(Path.of("config/player/player_left.png").toUri().toString()));

        // Setting Up Default Image
        currentDirection = Direction.BOTTOM;
        setImage(images.get(currentDirection));

        log.info("Player was created!");
    }

    /**
     * Attack.
     *
     * @param monster The monster to attack.
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
     * @param monster The monster that attacks the player.
     */
    public void inAttack(Monster monster) {
        double incomingDamage = monster.getDamage() * (100/(100 + getArmor())); // Given the character's armor.

        log.info("Player was attacked by monster \"{}\".Incoming damage is {}.", monster.getName(), incomingDamage);
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
     * @param healthBottle The health bottle to use.
     */
    public void inHealth(HealthBottle healthBottle) {
        if (!inventory.isInInventory(healthBottle)) return;
        health.addHealth(healthBottle.getHealth());
        inventory.removeItem(healthBottle);

        log.info("The potion \"{}\" was used. {} health has been added.", healthBottle.getName(), healthBottle.getHealth());
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
     * @param item The item to unset.
     */
    public void unsetEquipment(AEquipment item) {
        equipment.unsetEquipment(item, inventory);
    }

    /**
     * Gets damage.
     *
     * @return The damage.
     */
    public double getDamage() {
        if (equipment.getWeapon() == null) return basicDamage;
        return basicDamage + equipment.getWeapon().getDamage();
    }

    /**
     * Gets armor.
     *
     * @return The armor.
     */
    public double getArmor() {
        if (equipment.getArmor() == null) return basicArmor;
        return basicArmor + equipment.getArmor().getArmor();
    }

    /**
     * Gets damage radius.
     *
     * @return The damage radius.
     */
    public double getDamageRadius() {
        if (equipment.getWeapon() == null) return basicDamage;
        return damageRadius + equipment.getWeapon().getRadius();
    }

    /**
     * Gets basic damage radius.
     *
     * @return The basic damage radius.
     */
    public double getBasicDamageRadius() {
        return damageRadius;
    }

    /**
     * Gets health.
     *
     * @return The health.
     */
    public double getHealth() {
        return health.getHealth();
    }

    /**
     * Gets hp.
     *
     * @return The player's health.
     */
    public HP getHP() {
        return health;
    }

    /**
     * Gets current weapon.
     *
     * @return The current weapon.
     */
    public AItem getCurrentWeapon() {
        return equipment.getWeapon();
    }

    /**
     * Gets current armor.
     *
     * @return The current armor.
     */
    public AItem getCurrentArmor() {
        return equipment.getArmor();
    }

    /**
     * Is dead boolean.
     *
     * @return The boolean which indicates the player's death.
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
     * @param s The sprite.
     * @return The boolean which indicates the intersection.
     */
    public boolean intersectsAttackBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getAttackCollisionBox());
    }

    /**
     * Intersects move box boolean.
     * <p>
     * Checking the intersection with the legs.
     *
     * @param s The sprite.
     * @return The boolean which indicates the intersection.
     */
    public boolean intersectsMoveBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getMoveBox());
    }

    /**
     * Gets attack collision box.
     * <p>
     * Getting an attack area depending on the direction and damage radius of the character.
     *
     * @return The attack collision box.
     */
    public Rectangle2D getAttackCollisionBox() {
        return switch (currentDirection) {
            case TOP -> new Rectangle2D(positionX, positionY - getDamageRadius(), width, getDamageRadius());
            case RIGHT -> new Rectangle2D(positionX + width, positionY, getDamageRadius(), height);
            case BOTTOM -> new Rectangle2D(positionX, positionY + height, width, getDamageRadius());
            case LEFT -> new Rectangle2D(positionX - getDamageRadius(), positionY, getDamageRadius(), height);
        };

    }

    /**
     * Gets move box.
     * <p>
     * Getting the leg area of the character.
     *
     * @return The move box.
     */
    public Rectangle2D getMoveBox() {
        return new Rectangle2D(positionX, positionY + height - 16, width, 16);
    }

    /**
     * Move up.
     *
     * @param path The path to move.
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
     * @param path The path to move.
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
     * @param path The path to move.
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
     * @param path The path to move.
     */
    public void moveLeft(int path) {
        if (currentDirection != Direction.LEFT) {
            currentDirection = Direction.LEFT;
            setImage(images.get(currentDirection));
        }
        positionX -= path;
    }
}