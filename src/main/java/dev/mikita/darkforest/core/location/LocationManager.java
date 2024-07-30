package dev.mikita.darkforest.core.location;

import java.util.HashMap;

/**
 * The type Location manager.
 * <p>
 * Class for managing and creating locations. It also caches already created locations.
 */
public class LocationManager {
    /**
     * The locations in the game.
     */
    private final HashMap<Integer, Location> locations = new HashMap<>();

    /**
     * Gets location.
     *
     * @param locationId The location id.
     * @return The location.
     */
    public Location getLocation(int locationId) {
        if (locations.containsKey(locationId)) {
            return locations.get(locationId);
        }

        locations.put(locationId, new Location(locationId));
        return locations.get(locationId);
    }
}
