package dev.mikicit.darkforest.core.factory;

import dev.mikicit.darkforest.model.entity.Monster;
import dev.mikicit.darkforest.model.entity.Portal;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The type Portal factory.
 * <p>
 * Factory class for getting portals by their id.
 */
public class PortalFactory {
    /**
     * Gets portal.
     *
     * @param id the id
     * @return the portal
     */
    public static Portal getPortal(int id) {
        try {
            File file = new File("src/main/resources/portal/" + id + "/config.json");
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject config = new JSONObject(content);

            // Creating Portal
            Portal portal = new Portal(
                    id,
                    config.getInt("locationId"),
                    config.getInt("playerX"),
                    config.getInt("playerY")
            );
            portal.setImage("portal/" + id + "/image.png");

            return portal;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
