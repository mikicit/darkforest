package dev.mikicit.game;

import javafx.scene.image.Image;
import java.io.*;
import java.util.ArrayList;

public class TileMapManager {
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
        Image waterTile = new Image("tile/water.png");
        Image grassTile = new Image("tile/grass.png");

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                char c = lines.get(i).charAt(j);

                if (c == 'A') {
                    tileMap.setTile(j, i, grassTile);
                } else if (c == 'B') {
                    tileMap.setTile(j, i, waterTile);
                } else {
                    tileMap.setTile(j, i, null);
                }
            }
        }

        return tileMap;
    }

    public int convertTileXToPixel(int x) {
        return x * tileSize;
    }

    public int convertTileYToPixel(int y) {
        return y * tileSize;
    }
}
