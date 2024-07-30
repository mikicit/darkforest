package dev.mikita.darkforest.core.factory;

import dev.mikita.darkforest.model.entity.Item.AItem;
import dev.mikita.darkforest.model.entity.Item.bottle.HealthBottle;
import dev.mikita.darkforest.model.entity.Item.equipment.Armor;
import dev.mikita.darkforest.model.entity.Item.equipment.Weapon;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The type Item factory.
 * <p>
 * Factory class for getting items by their id.
 */
public class ItemFactory {
    /**
     * Gets item.
     *
     * @param id The item id.
     * @return The item.
     */
    public static AItem getItem(int id) {
        try {
            String content = new String(Files.readAllBytes(Path.of("config/item/" + id + "/config.json")));
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
            throw new RuntimeException("Error while reading item config", e);
        }

        return null;
    }

    /**
     * Create bottle.
     *
     * @param config The bottle config.
     * @return The bottle.
     */
    private static AItem createBottle(JSONObject config) {
        HealthBottle healthBottle = new HealthBottle(
                config.getInt("id"),
                config.getString("name"),
                config.getDouble("health"));
        healthBottle.setImage(Path.of("config/item/" + config.getInt("id") + "/image.png").toUri().toString());

        return healthBottle;
    }

    /**
     * Create weapon.
     *
     * @param config The weapon config.
     * @return The weapon.
     */
    private static AItem createWeapon(JSONObject config) {
        Weapon weapon = new Weapon(
                config.getInt("id"),
                config.getString("name"),
                config.getDouble("damage"),
                config.getDouble("radius"));
        weapon.setImage(Path.of("config/item/" + config.getInt("id") + "/image.png").toUri().toString());

        return weapon;
    }

    /**
     * Create armor.
     *
     * @param config The armor config.
     * @return The armor.
     */
    private static AItem createArmor(JSONObject config) {
        Armor armor = new Armor(
                config.getInt("id"),
                config.getString("name"),
                config.getDouble("armor"));
        armor.setImage(Path.of("config/item/" + config.getInt("id") + "/image.png").toUri().toString());

        return armor;
    }
}
