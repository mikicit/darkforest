package dev.mikita.darkforest.model.entity.Item.equipment;

import lombok.Getter;

/**
 * The type Armor.
 */
@Getter
public class Armor extends AEquipment {
    /**
     * The armor.
     * -- GETTER --
     * Gets armor value.
     *
     * @return The armor value.
     */
    private final double armor;

    /**
     * Instantiates a new armor.
     *
     * @param id    The armor id.
     * @param name  The armor name.
     * @param armor The armor value.
     */
    public Armor(int id, String name, double armor) {
        super(id, name);
        this.armor = armor;
    }
}