package dev.mikicit.darkforest.core.factory;

import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Item.bottle.HealthBottle;
import dev.mikicit.darkforest.model.entity.Item.equipment.Armor;
import dev.mikicit.darkforest.model.entity.Item.equipment.Weapon;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The type Item factory.
 * <p>
 * Factory class for getting items by their id.
 */
public class ItemFactory {
    /**
     * Gets item.
     *
     * @param id the id
     * @return the item
     */
    public static AItem getItem(int id) {
        try {
            File file = new File("src/main/resources/item/" + id + "/config.json");
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject config = new JSONObject(content);

            String itemType = config.getString("type");

            switch (itemType) {
                case "bottle":
                    return createBottle(config);
                case "weapon":
                    return createWeapon(config);
                case "armor":
                    return createArmor(config);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Create bottle.
     *
     * @param config the config
     * @return the item
     */
    private static AItem createBottle(JSONObject config) {
        HealthBottle healthBottle = new HealthBottle(
                config.getInt("id"),
                config.getString("name"),
                config.getDouble("health"));
        healthBottle.setImage("item/" + config.getInt("id") + "/image.png");

        return healthBottle;
    }

    /**
     * Create weapon.
     *
     * @param config the config
     * @return the item
     */
    private static AItem createWeapon(JSONObject config) {
        Weapon weapon = new Weapon(
                config.getInt("id"),
                config.getString("name"),
                config.getDouble("damage"),
                config.getDouble("radius"));
        weapon.setImage("item/" + config.getInt("id") + "/image.png");

        return weapon;
    }

    /**
     * Create armor.
     *
     * @param config the config
     * @return the item
     */
    private static AItem createArmor(JSONObject config) {
        Armor armor = new Armor(
                config.getInt("id"),
                config.getString("name"),
                config.getDouble("armor"));
        armor.setImage("item/" + config.getInt("id") + "/image.png");

        return armor;
    }
}
