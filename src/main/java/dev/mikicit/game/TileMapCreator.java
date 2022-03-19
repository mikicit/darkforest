package dev.mikicit.game;

import javafx.scene.image.Image;
import java.io.*;
import java.util.ArrayList;

public class TileMapCreator {
    public static void main(String[] args) throws IOException {
        TileMapCreator tmc = new TileMapCreator();

        tmc.createTileMap("src/main/resources/map/map.txt");
    }

    public TileMap createTileMap(String filename) throws IOException {
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
        } catch (IOException e) {}

        height = lines.size();

        // Tiles
        Image waterTile = new Image("tile/water.png");
        Image grassTile = new Image("tile/grass.png");

        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                Character c = line.charAt(i);

                if (c.equals("A")) {

                } else if (c.equals("B")) {

                } else {

                }

                System.out.println(line.charAt(i));
            }
        }

        TileMap tileMap = new TileMap(width, height);

        return tileMap;
    }
}
