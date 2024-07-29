package dev.mikita.darkforest.model.entity;

import dev.mikita.darkforest.core.sprite.ASprite;
import javafx.geometry.Rectangle2D;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * The type Monster.
 * <p>
 * A class that represents a particular monster.
 */
public class Monster extends ASprite {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    // Links
    private Random random = new Random();
    private Player aim;

    // Characteristics
    private final String name;
    private final double damage;
    private final double damageRadius;
    private final double viewingRadius;
    private double health;
    private double speed = 64; // px per second
    private double attackSpeed = 300; // in ms

    // Timers
    private double movingTimer = 2; // in sec
    private double waitingTimer; // in sec
    private int currentDirectionIndex = 0;

    // State
    private Integer[] directionSequence = {1, 2, 3, 4};
    private boolean inCombat = false;
    private boolean isDead;
    private double lastAttack;

    /**
     * Instantiates a new Monster.
     *
     * @param name          the name
     * @param health        the health
     * @param damage        the damage
     * @param damageRadius  the damage radius
     * @param viewingRadius the viewing radius
     * @param speed         the speed
     * @param attackSpeed   the attack speed
     */
    public Monster(String name, double health, double damage, double damageRadius, double viewingRadius, double speed, double attackSpeed) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.damageRadius = damageRadius;
        this.viewingRadius = viewingRadius;
        this.speed = speed;
        this.attackSpeed = attackSpeed;

        // setting random direction sequence
        List<Integer> intList = Arrays.asList(directionSequence);
        Collections.shuffle(intList);
        intList.toArray(directionSequence);

        // setting random start moving time
        waitingTimer = random.nextDouble() * 3;
    }

    /**
     * Attack.
     *
     * @param player the player
     */
    public void attack(Player player) {
        if ((System.currentTimeMillis() - lastAttack) < attackSpeed) {
            return;
        }

        lastAttack = System.currentTimeMillis(); // cooldown

        if (!player.isDead()) {
            player.inAttack(this);
        }
    }

    /**
     * In attack.
     *
     * @param player the player
     */
    public void inAttack(Player player) {
        double incomingDamage = player.getDamage();
        log.info("Monster \"" + getName() + "\" was attacked by player." + " Incoming damage is " + incomingDamage + ".");
        health = Math.max(health - incomingDamage, 0);

        if (health == 0) {
            isDead = true;
            log.info("Monster \"" + name + "\" is dead!");
        }

        // Counterattack
        attack(player);
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
     * Gets damage.
     *
     * @return the damage
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public double getHealth() {
        return health;
    }

    /**
     * Gets damage radius.
     *
     * @return the damage radius
     */
    public double getDamageRadius() {
        return damageRadius;
    }

    /**
     * Gets viewing radius.
     *
     * @return the viewing radius
     */
    public double getViewingRadius() {
        return viewingRadius;
    }

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Intersects radius view box boolean.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean intersectsRadiusViewBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getRadiusViewCollisionBox());
    }

    /**
     * Intersects damage box boolean.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean intersectsDamageBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getDamageRadiusBox());
    }

    /**
     * Gets damage radius box.
     *
     * @return the damage radius box
     */
    public Rectangle2D getDamageRadiusBox() {
        double damageRadius = getDamageRadius();

        return new Rectangle2D(
                positionX - damageRadius,
                positionY - damageRadius,
                width + (2 * damageRadius),
                height + (2 * damageRadius));
    }

    /**
     * Gets radius view collision box.
     *
     * @return the radius view collision box
     */
    public Rectangle2D getRadiusViewCollisionBox() {
        double viewingRadius = getViewingRadius();

        return new Rectangle2D(
                positionX - viewingRadius,
                positionY - viewingRadius,
                width + (2 * viewingRadius),
                height + (2 * viewingRadius));
    }

    /**
     * Sets aim.
     * <p>
     * Sets a target to pursue.
     *
     * @param player the player
     */
    public void setAim(Player player) {
        if (aim == null) {
            aim = player;
        }
        inCombat = true;
    }

    /**
     * Move To Aim.
     * <p>
     * The method is responsible for moving towards the goal.
     * Called every frame while the monster is chasing the target (in combat).
     *
     * @param delta the delta
     */
    private void moveToAim(double delta) {
        int path = (int) (speed * delta);

        if (intersectsDamageBox(aim)) {
            attack(aim);
            return;
        }

        double aimX = aim.getX();
        double aimY = aim.getY();

        if (Math.abs(aimX - positionX) > 10) {
            if (aim.getX() > positionX) {
                positionX += path;
            } else {
                positionX -= path;
            }
        }

        if (Math.abs(aimY - positionY) > 10) {
            if (aim.getY() > positionY) {
                positionY += path;
            } else {
                positionY -= path;
            }
        }
    }

    /**
     * Random Moving.
     * <p>
     * This method is responsible for the random movement of the monster.
     * Called every frame while the monster is not chasing anyone.
     *
     * @param delta the delta
     */
    private void randomMoving(double delta) {
        int path = (int) (speed * delta);

        waitingTimer -= delta;
        if (waitingTimer < 0) {
            movingTimer -= delta;
            if (movingTimer > 0) {
                int directionNumber = directionSequence[currentDirectionIndex];
                if (directionNumber == 1) {
                    setPosition(positionX + path, positionY);
                } else if (directionNumber == 2) {
                    setPosition(positionX - path, positionY);
                } else if (directionNumber == 3) {
                    setPosition(positionX, positionY + path);
                } else if (directionNumber == 4) {
                    setPosition(positionX, positionY - path);
                }
            } else {
                waitingTimer = 3;
                movingTimer = 2;
                currentDirectionIndex++;
                if (currentDirectionIndex > directionSequence.length - 1) {
                    currentDirectionIndex = 0;
                }
            }
        }
    }

    /**
     * Off combat.
     * <p>
     * Remove the target and combat mode from the monster (required when changing locations).
     */
    public void offCombat() {
        inCombat = false;
    }

    @Override
    public void update(double delta) {
        if (!inCombat) {
            randomMoving(delta);
        } else {
            moveToAim(delta);
        }
    }
}
