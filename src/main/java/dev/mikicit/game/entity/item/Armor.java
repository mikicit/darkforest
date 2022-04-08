package dev.mikicit.game.entity.item;

public class Armor extends AItem implements IEquipable {
    public Armor(int id, String name) {
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
