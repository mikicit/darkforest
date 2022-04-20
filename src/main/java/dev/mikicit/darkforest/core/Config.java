package dev.mikicit.darkforest.core;

import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {
    private static JSONObject jo;

    public static void init(String path) {
        File file = new File(path);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            jo = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getWindowWidth() {
        return jo.getInt("windowWidth");
    }

    public static int getWindowHeight() {
        return jo.getInt("windowHeight");
    }

    public static int getTileSize() {
        return jo.getInt("tileSize");
    }

    public static String getWindowName() {
        return jo.getString("windowName");
    }
}
