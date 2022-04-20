package dev.mikicit.darkforest.core.tile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class TileMapCreator {
    public static TileMap createTileMap(String filename, int tileSize) {
        int mapWidth = 0;
        int mapHeight = 0;
        ArrayList<String> lines = new ArrayList<>();

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

        /*
         * TODO
         * Сделать загрузку по ID
         */

        Tile waterTile = new Tile("tile/water.png", 1, false);
        Tile grassTile = new Tile("tile/grass.png", 2, true);
        Tile bushTile = new Tile("tile/bush.png", 3, false);
        Tile earthTile = new Tile("tile/earth.png", 4, true);

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                char c = lines.get(i).charAt(j);

                if (c == 'A') {
                    tileMap.setTile(j, i, grassTile);
                } else if (c == 'B') {
                    tileMap.setTile(j, i, waterTile);
                } else if (c == 'C') {
                    tileMap.setTile(j, i, bushTile);
                } else if (c == 'D') {
                    tileMap.setTile(j, i, earthTile);
                } else {
                    tileMap.setTile(j, i, null);
                }
            }
        }

        return tileMap;
    }
}
