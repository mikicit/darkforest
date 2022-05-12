package dev.mikicit.darkforest.core.location;

import dev.mikicit.darkforest.model.entity.Player;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * The type Location manager.
 * <p>
 * Class for managing and creating locations. It also caches already created locations.
 */
public class LocationManager {
    // Logger
    private static Logger log = Logger.getLogger(Player.class.getName());

    // Cached Locations
    private final HashMap<Integer, Location> locations = new HashMap<>();

    /**
     * Gets location.
     *
     * @param locationId the location id
     * @return the location
     */
    public Location getLocation(int locationId) {
        if (locations.containsKey(locationId)) {
            return locations.get(locationId);
        }

        locations.put(locationId, new Location(locationId));
        return locations.get(locationId);
    }
}
