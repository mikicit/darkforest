package dev.mikicit.game.entity.item;

public class Bottle extends AItem implements IUseable {
    public Bottle(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void use() {

    }
}
