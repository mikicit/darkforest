package dev.mikicit.darkforest.model.entity.Item.equipment;

/**
 * The type Weapon.
 */
public class Weapon extends AEquipment {
    private final double damage;
    private final double radius;

    /**
     * Instantiates a new Weapon.
     *
     * @param id     the id
     * @param name   the name
     * @param damage the damage
     * @param radius the radius
     */
    public Weapon(int id, String name, double damage, double radius) {
        super(id, name);
        this.damage = damage;
        this.radius = radius;
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
     * Gets radius.
     *
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }
}
