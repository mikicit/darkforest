package dev.mikita.darkforest.core;

import dev.mikita.darkforest.core.tile.TileMap;
import dev.mikita.darkforest.model.GameModel;
import dev.mikita.darkforest.model.entity.Item.AItem;
import dev.mikita.darkforest.model.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The type Player config.
 * <p>
 * Helper class for loading player configuration (standard or from save).
 */
@Slf4j
public class PlayerConfig {
    /**
     * Gets player config.
     *
     * @param fromSave The boolean value that indicates whether to load player config from save or not.
     * @return The player configuration.
     */
    public static JSONObject getPlayerConfig(boolean fromSave) {
        String path = fromSave ? "save/player.json" : "config/player.json";

        try {
            String content = new String(Files.readAllBytes(Path.of(path)));
            return new JSONObject(content);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading player config", e);
        }
    }

    /**
     * Is exist player save boolean.
     *
     * @return The boolean value that indicates whether player save exists or not.
     */
    public static boolean isExistPlayerSave() {
        return Path.of("save/player.json").toFile().exists();
    }

    /**
     * Save player config.
     */
    public static void savePlayerConfig() {
        JSONObject playerConfig = new JSONObject();
        GameModel gameModel = GameModel.getInstance();
        Player player = gameModel.getPlayer();

        playerConfig.put("name", player.getName());
        playerConfig.put("initialHealth", player.getHP().getInitialHealth());
        playerConfig.put("health", player.getHealth());
        playerConfig.put("damage", player.getBasicDamage());
        playerConfig.put("armor", player.getBasicArmor());
        playerConfig.put("damageRadius", player.getBasicDamageRadius());
        playerConfig.put("locationId", gameModel.getCurrentLocation().getLocationId());
        playerConfig.put("positionX", TileMap.convertPixelToTile(player.getPositionX()));
        playerConfig.put("positionY", TileMap.convertPixelToTile(player.getPositionY()));

        AItem currentWeapon = player.getCurrentWeapon();
        if (currentWeapon != null) {
            playerConfig.put("equippedWeaponId", currentWeapon.getId());
        } else {
            playerConfig.put("equippedWeaponId", -1);
        }

        AItem currentArmor = player.getCurrentArmor();
        if (currentArmor != null) {
            playerConfig.put("equippedArmorId", currentArmor.getId());
        } else {
            playerConfig.put("equippedArmorId", -1);
        }

        JSONArray inventory = new JSONArray();
        playerConfig.put("inventory", inventory);
        for (AItem item : player.getInventory().getItems()) {
            inventory.put(item.getId());
        }

        Path path = Path.of("save/player.json");
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            Files.write(path, playerConfig.toString().getBytes());
        } catch (IOException e) {
            log.error("Error while saving player config", e);
            throw new RuntimeException("Error while saving player config", e);
        }
    }
}
