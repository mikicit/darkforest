package dev.mikicit.game.core.util.tileEngine;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.json.*;

public class Tile {
    private final Image image;
    private final int id;
    private final boolean passable;

    public Tile(String imagePath, int id, boolean passable) {
        this.passable = passable;
        this.id = id;
        this.image = new Image(imagePath);

//        try {
//            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/tile/" + id + ".json"));
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//
//            while (line != null) {
//                sb.append(line);
//                sb.append(System.lineSeparator());
//                line = br.readLine();
//            }
//
//            String everything = sb.toString();
//            br.close();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
    }

    public Image getImage() {
        return image;
    }

    public void render(GraphicsContext gc, double x, double y) {
        gc.drawImage(image, x, y);
    }

    public int getId() {
        return id;
    }

    public boolean isPassable() {
        return passable;
    }
}
