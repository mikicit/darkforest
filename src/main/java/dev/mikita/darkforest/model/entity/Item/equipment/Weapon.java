package dev.mikita.darkforest.model.entity.Item.equipment;

import lombok.Getter;

/**
 * The type Weapon.
 */
@Getter
public class Weapon extends AEquipment {
    /**
     * The damage.
     * -- GETTER --
     * Returns the damage value.
     *
     * @return The damage value.
     */
    private final double damage;

    /**
     * The damage radius.
     * -- GETTER --
     * Returns the damage radius.
     *
     * @return The damage radius.
     */
    private final double radius;

    /**
     * Instantiates a new weapon.
     *
     * @param id     The weapon id.
     * @param name   The weapon name.
     * @param damage The weapon damage.
     * @param radius The weapon damage radius.
     */
    public Weapon(int id, String name, double damage, double radius) {
        super(id, name);
        this.damage = damage;
        this.radius = radius;
    }
}
