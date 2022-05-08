package dev.mikicit.darkforest.model.entity.Item.equipment;

public class Weapon extends AEquipment {
    private final double damage;
    private final double radius;

    public Weapon(String name, double damage, double radius) {
        super(name);
        this.damage = damage;
        this.radius = radius;
    }

    public double getDamage() {
        return damage;
    }

    public double getRadius() {
        return radius;
    }
}
