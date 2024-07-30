package dev.mikita.darkforest.model.entity;

import dev.mikita.darkforest.core.sprite.ASprite;
import javafx.geometry.Rectangle2D;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The type Monster.
 * <p>
 * A class that represents a particular monster.
 */
@Slf4j
public class Monster extends ASprite {
    /**
     * The random.
     */
    private final Random random = new Random();

    /**
     * The aim (target).
     * <p>
     * The target that the monster is currently chasing.
     */
    private Player aim;

    /**
     * The name.
     * -- GETTER --
     * Gets the name of the monster.
     *
     * @return The name of the monster.
     */
    @Getter private final String name;

    /**
     * The damage.
     * -- GETTER --
     * Gets the damage that the monster can inflict.
     *
     * @return The damage that the monster can inflict.
     */
    @Getter private final double damage;

    /**
     * The damage radius.
     * -- GETTER --
     * Gets the radius of the damage that the monster can inflict.
     *
     * @return The radius of the damage that the monster can inflict.
     */
    @Getter private final double damageRadius;

    /**
     * The viewing radius.
     * -- GETTER --
     * Gets the radius of the monster's view.
     *
     * @return The radius of the monster's view.
     */
    @Getter private final double viewingRadius;

    /**
     * The health.
     * -- GETTER --
     * Gets the health of the monster.
     *
     * @return The health of the monster.
     */
    @Getter private double health;

    /**
     * The speed.
     */
    private final double speed; // px per second

    /**
     * The attack speed.
     */
    private final double attackSpeed; // in ms

    /**
     * The moving timer.
     * <p>
     * The time during which the monster moves in one direction.
     */
    private double movingTimer = 2; // in sec

    /**
     * The waiting timer.
     * <p>
     * The time during which the monster waits before changing direction.
     */
    private double waitingTimer; // in sec

    /**
     * The current direction index.
     * <p>
     * The index of the current direction in the direction sequence.
     */
    private int currentDirectionIndex = 0;

    /**
     * The direction sequence.
     * <p>
     * The sequence of directions in which the monster moves.
     */
    private final Integer[] directionSequence = {1, 2, 3, 4};

    /**
     * The monster is in combat mode.
     * <p>
     * A flag that indicates whether the monster is in combat mode.
     */
    private boolean inCombat = false;

    /**
     * The monster is dead.
     * <p>
     * A flag that indicates whether the monster is dead.
     */
    private boolean isDead;

    /**
     * The last attack.
     * <p>
     * The time of the last attack.
     */
    private double lastAttack;

    /**
     * Instantiates a new Monster.
     *
     * @param name          The name.
     * @param health        The health.
     * @param damage        The damage.
     * @param damageRadius  The damage radius.
     * @param viewingRadius The viewing radius.
     * @param speed         The speed.
     * @param attackSpeed   The attack speed.
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
     * <p>
     * The method is responsible for attacking the player.
     *
     * @param player The player.
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
     * <p>
     * The method is responsible for the monster being attacked by the player.
     *
     * @param player The player.
     */
    public void inAttack(Player player) {
        double incomingDamage = player.getDamage();
        log.info("Monster \"{}\" was attacked by player. Incoming damage is {}.", getName(), incomingDamage);
        health = Math.max(health - incomingDamage, 0);

        if (health == 0) {
            isDead = true;
            log.info("Monster \"{}\" is dead!", name);
        }

        // Counterattack
        attack(player);
    }

    /**
     * Is dead boolean.
     *
     * @return The boolean that indicates whether the monster is dead.
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Intersects radius view box boolean.
     *
     * @param s The sprite.
     * @return The boolean that indicates whether the monster's view radius intersects with the sprite.
     */
    public boolean intersectsRadiusViewBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getRadiusViewCollisionBox());
    }

    /**
     * Intersects damage box boolean.
     *
     * @param s The sprite.
     * @return The boolean that indicates whether the monster's damage radius intersects with the sprite.
     */
    public boolean intersectsDamageBox(ASprite s) {
        return s.getCollisionBox().intersects(this.getDamageRadiusBox());
    }

    /**
     * Gets damage radius box.
     *
     * @return The damage radius box.
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
     * @return The radius view collision box.
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
     * @param player The player.
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
     * @param delta The delta.
     */
    private void moveToAim(double delta) {
        int path = (int) (speed * delta);

        if (intersectsDamageBox(aim)) {
            attack(aim);
            return;
        }

        double aimX = aim.getPositionX();
        double aimY = aim.getPositionY();

        if (Math.abs(aimX - positionX) > 10) {
            if (aim.getPositionX() > positionX) {
                positionX += path;
            } else {
                positionX -= path;
            }
        }

        if (Math.abs(aimY - positionY) > 10) {
            if (aim.getPositionY() > positionY) {
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
     * @param delta The delta.
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
