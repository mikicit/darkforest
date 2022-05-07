package dev.mikicit.darkforest.core.location;

import dev.mikicit.darkforest.core.factory.ItemFactory;
import dev.mikicit.darkforest.core.factory.MonsterFactory;
import dev.mikicit.darkforest.core.sprite.SpriteManager;
import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.core.tile.TileMapManager;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Monster;
import dev.mikicit.darkforest.model.entity.Player;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Location {
    private JSONObject config;
    private final int locationId;
    private final String name;
    private Player player;
    private ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<AItem> items = new ArrayList<>();
    private TileMap tileMap;
    private SpriteManager spriteManager;

    public Location(int locationId) {
        this.locationId = locationId;

        try {
            File file = new File("src/main/resources/location/" + locationId + "/config.json");
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            config = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.name = config.getString("name");
    }

    public void init() {
        // Sprite Manager Init
        spriteManager = new SpriteManager();

        // Tile Map Init
        tileMap = TileMapManager.createTileMap("src/main/resources/location/" + locationId + "/map.txt", 64);

        // Monsters Init
        for (Object monsterConfig : config.getJSONArray("monsters")) {
            Monster monster = MonsterFactory.getMonster(((JSONObject) monsterConfig).getInt("id"));
            monster.setPosition(
                    tileMap.convertTileToPixel(((JSONObject) monsterConfig).getInt("positionX")),
                    tileMap.convertTileToPixel(((JSONObject) monsterConfig).getInt("positionY")));

            monsters.add(monster);
            spriteManager.addSprite(monster);
        }

        // Items Init
        for (Object itemConfig : config.getJSONArray("items")) {
            AItem item = ItemFactory.getItem(((JSONObject) itemConfig).getInt("id"));
            item.setPosition(
                    tileMap.convertTileToPixel(((JSONObject) itemConfig).getInt("positionX")),
                    tileMap.convertTileToPixel(((JSONObject) itemConfig).getInt("positionY")));

            items.add(item);
            spriteManager.addSprite(item);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
        spriteManager.addSprite(player);
    }

    public int getId() {
        return locationId;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public ArrayList<AItem> getItems() {
        return items;
    }

    public TileMap getTileMap() {
        return tileMap;
    }

    public SpriteManager getSpriteManager() {
        return spriteManager;
    }
}
