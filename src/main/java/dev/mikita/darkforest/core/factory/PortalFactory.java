package dev.mikita.darkforest.core.factory;

import dev.mikita.darkforest.model.entity.Portal;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The type Portal factory.
 * <p>
 * Factory class for getting portals by their id.
 */
public class PortalFactory {
    /**
     * Gets portal.
     *
     * @param id The portal id.
     * @return The portal.
     */
    public static Portal getPortal(int id) {
        try {
            String content = new String(Files.readAllBytes(Path.of("config/portal/" + id + "/config.json")));
            JSONObject config = new JSONObject(content);

            // Creating Portal
            Portal portal = new Portal(
                    id,
                    config.getInt("locationId"),
                    config.getInt("playerX"),
                    config.getInt("playerY")
            );
            portal.setImage(Path.of("config/portal/" + id + "/image.png").toUri().toString());

            return portal;
        } catch (IOException e) {
            throw new RuntimeException("Error while reading portal config", e);
        }
    }
}
