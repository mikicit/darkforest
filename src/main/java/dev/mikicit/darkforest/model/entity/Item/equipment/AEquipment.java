package dev.mikicit.darkforest.model.entity.Item.equipment;

import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Player;

public abstract class AEquipment extends AItem {
    public AEquipment(String name) {
        super(name);
    }

    public void equip(Player player) {
        player.setEquipment(this);
    }

    public void unEquip(Player player) {
        player.unsetEquipment(this);
    }
}
