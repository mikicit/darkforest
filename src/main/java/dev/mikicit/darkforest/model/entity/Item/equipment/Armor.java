package dev.mikicit.darkforest.model.entity.Item.equipment;

public class Armor extends AEquipment {
    private final double armor;

    public Armor(String name, double armor) {
        super(name);
        this.armor = armor;
    }

    public double getArmor() {
        return armor;
    }
}