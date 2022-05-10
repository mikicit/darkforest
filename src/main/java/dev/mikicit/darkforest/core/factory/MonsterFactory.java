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

            // Creating Monster
            Monster monster = new Monster(name, health, damage, damageRadius, viewingRadius);
            monster.setImage("monster/" + id + "/image.png");

            // Animation Config
            JSONArray animations = config.getJSONArray("animations");

            for (Object anim : animations) {
                JSONObject animConfig = (JSONObject) anim;
                Animation animation = new Animation(
                        "monster/" + id + "/" + animConfig.getString("name") + ".png",
                        animConfig.getDouble("animationTime"),
                        animConfig.getInt("numberOfFrames"),
                        animConfig.getDouble("frameWidth"),
                        animConfig.getDouble("frameHeight")
                        );

                monster.setAnimation(animConfig.getString("name"), animation);
            }

            return monster;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
