package dev.mikita.darkforest.core.factory;

import dev.mikita.darkforest.model.entity.Monster;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The type Monster factory.
 * <p>
 * Factory class for getting monsters by their id.
 */
public class MonsterFactory {
    /**
     * Gets monster.
     *
     * @param id The monster id.
     * @return The monster.
     */
    public static Monster getMonster(int id) {
        try {
            String content = new String(Files.readAllBytes(Path.of("config/monster/" + id + "/config.json")));
            JSONObject config = new JSONObject(content);

            // Characteristics
            String name = config.getString("name");
            double health = config.getDouble("health");
            double damage = config.getDouble("damage");
            double damageRadius = config.getDouble("damageRadius");
            double viewingRadius = config.getDouble("viewingRadius");
            double speed = config.getDouble("speed");
            double attackSpeed = config.getDouble("attackSpeed");

            // Creating Monster
            Monster monster = new Monster(name, health, damage, damageRadius, viewingRadius, speed, attackSpeed);
            monster.setImage(Path.of("config/monster/" + id + "/image.png").toUri().toString());

            return monster;
        } catch (IOException e) {
            throw new RuntimeException("Error while reading monster config", e);
        }
    }
}
