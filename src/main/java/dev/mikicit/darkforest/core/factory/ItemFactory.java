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

public class ItemFactory {
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

    private static AItem createBottle(JSONObject config) {
        HealthBottle healthBottle = new HealthBottle(config.getString("name"), config.getDouble("health"));
        healthBottle.setImage("item/" + config.getInt("id") + "/image.png");

        return healthBottle;
    }

    private static AItem createWeapon(JSONObject config) {
        Weapon weapon = new Weapon(config.getString("name"), config.getDouble("damage"), config.getDouble("radius"));
        weapon.setImage("item/" + config.getInt("id") + "/image.png");

        return weapon;
    }

    private static AItem createArmor(JSONObject config) {
        Armor armor = new Armor(config.getString("name"), config.getDouble("armor"));
        armor.setImage("item/" + config.getInt("id") + "/image.png");

        return armor;
    }
}
