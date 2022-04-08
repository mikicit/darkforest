package dev.mikicit.game.core.tileEngine;

import java.io.*;
import java.util.ArrayList;

public class TileMapReader {
    private int tileSize;

    public TileMap createTileMap(String filename, int tileSize) {
        this.tileSize = tileSize;
        int width = 0;
        int height = 0;
        ArrayList<String> lines = new ArrayList<String>();

        try {
            Reader fileReader = new FileReader(filename);
            BufferedReader br = new BufferedReader(fileReader);

            String fileLine = null;

            while ((fileLine = br.readLine()) != null) {
                if (fileLine.length() > width) width = fileLine.length();
                lines.add(fileLine);
            }

            br.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        height = lines.size();

        // Tiles
        TileMap tileMap = new TileMap(width, height, tileSize);

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
