package dev.mikicit.darkforest.core.tile;

import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Tile map manager.
 * <p>
 * Manager for creating maps (tilemap).
 */
public class TileMapManager {
    private static final HashMap<String, Tile> cachedTiles = new HashMap<>();

    /**
     * Create tile map tile map.
     *
     * @param filename the filename
     * @param tileSize the tile size
     * @return the tile map
     */
    public static TileMap createTileMap(String filename, int tileSize) {
        int mapWidth = 0;
        int mapHeight = 0;
        ArrayList<String> lines = new ArrayList<>();

        // Map Parsing
        try {
            Reader fileReader = new FileReader(filename);
            BufferedReader br = new BufferedReader(fileReader);

            String fileLine = null;

            while ((fileLine = br.readLine()) != null) {
                if (fileLine.length() > mapWidth) mapWidth = fileLine.length();
                lines.add(fileLine);
            }

            br.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        mapHeight = lines.size();

        // Tiles
        TileMap tileMap = new TileMap(mapWidth, mapHeight, tileSize);

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                char c = lines.get(i).charAt(j);
                tileMap.setTile(j, i, getTile(String.valueOf(c)));
            }
        }

        return tileMap;
    }

    /**
     * Get tile.
     * <p>
     * Method for creating a tile.
     *
     * @param id the id
     * @return the tile
     */
    private static Tile getTile(String id) {
        if (cachedTiles.containsKey(id)) {
            return cachedTiles.get(id);
        }

        try {
            File file = new File("src/main/resources/tile/" + id + "/config.json");
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject config = new JSONObject(content);

            Tile tile = new Tile("tile/" + id + "/image.png", config.getBoolean("passable"));
            cachedTiles.put(id, tile);

            return tile;

        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
