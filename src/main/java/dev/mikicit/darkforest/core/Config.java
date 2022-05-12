package dev.mikicit.darkforest.core;

import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The type Config.
 *
 * Helper class for loading the main application configuration.
 * Provides access to settings anywhere in the application.
 */
public class Config {
    private static JSONObject jo;

    /**
     * Init.
     *
     * @param path the path
     */
    public static void init(String path) {
        File file = new File(path);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            jo = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets window width.
     *
     * @return the window width
     */
    public static int getWindowWidth() {
        return jo.getInt("windowWidth");
    }

    /**
     * Gets window height.
     *
     * @return the window height
     */
    public static int getWindowHeight() {
        return jo.getInt("windowHeight");
    }

    /**
     * Gets tile size.
     *
     * @return the tile size
     */
    public static int getTileSize() {
        return jo.getInt("tileSize");
    }

    /**
     * Gets window name.
     *
     * @return the window name
     */
    public static String getWindowName() {
        return jo.getString("windowName");
    }
}
