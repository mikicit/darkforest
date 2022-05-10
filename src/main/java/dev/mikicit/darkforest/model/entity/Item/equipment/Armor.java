package dev.mikicit.darkforest.model.entity.Item.equipment;

public class Armor extends AEquipment {
    private final double armor;

    public Armor(int id, String name, double armor) {
        super(id, name);
        this.armor = armor;
    }

    public double getArmor() {
        return armor;
    }
}