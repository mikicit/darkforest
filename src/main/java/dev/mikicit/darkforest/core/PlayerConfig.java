package dev.mikicit.darkforest.core;

import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.model.GameModel;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Player;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Player config.
 *
 * Helper class for loading player configuration (standard or from save)
 */
public class PlayerConfig {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    /**
     * Gets player config.
     *
     * @param fromSave the fromSave
     * @return the player config
     */
    public static JSONObject getPlayerConfig(boolean fromSave) {
        String path = fromSave ? "save/player.json" : "player.json";

        try {
            File file = new File(path);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));

            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Is exist player save boolean.
     *
     * @return the boolean
     */
    public static boolean isExistPlayerSave() {
        File f = new File("save/player.json");
        return f.isFile();
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
        playerConfig.put("locationId", gameModel.getCurrentLocation().getId());
        playerConfig.put("positionX", TileMap.convertPixelToTile(player.getX()));
        playerConfig.put("positionY", TileMap.convertPixelToTile(player.getY()));

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

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter("save/player.json"));
        ) {
            writer.write(playerConfig.toString());
        } catch (IOException e) {
            log.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
