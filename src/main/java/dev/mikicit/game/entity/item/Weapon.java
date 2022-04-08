package dev.mikicit.game.entity.item;

public class Weapon extends AItem implements IEquipable {
    public Weapon(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void equip() {

    }

    @Override
    public void unEquip() {

    }
}
