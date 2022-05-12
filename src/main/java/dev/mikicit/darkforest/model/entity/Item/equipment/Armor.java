package dev.mikicit.darkforest.model.entity.Item.equipment;

/**
 * The type Armor.
 */
public class Armor extends AEquipment {
    private final double armor;

    /**
     * Instantiates a new Armor.
     *
     * @param id    the id
     * @param name  the name
     * @param armor the armor
     */
    public Armor(int id, String name, double armor) {
        super(id, name);
        this.armor = armor;
    }

    /**
     * Gets armor.
     *
     * @return the armor
     */
    public double getArmor() {
        return armor;
    }
}