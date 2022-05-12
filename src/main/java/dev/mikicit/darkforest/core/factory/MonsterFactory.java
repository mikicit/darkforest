package dev.mikicit.darkforest.core.factory;

import dev.mikicit.darkforest.core.sprite.Animation;
import dev.mikicit.darkforest.model.entity.Monster;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MonsterFactory {
    public static Monster getMonster(int id) {
        try {
            File file = new File("src/main/resources/monster/" + id + "/config.json");
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
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
            monster.setImage("monster/" + id + "/image.png");

            return monster;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
