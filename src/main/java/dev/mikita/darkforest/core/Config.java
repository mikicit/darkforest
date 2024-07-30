package dev.mikita.darkforest.core;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The type Config.
 * <p>
 * Helper class for loading the main application configuration.
 * Provides access to settings anywhere in the application.
 */
@Slf4j
public class Config {
    /**
     * The json object.
     */
    private static JSONObject jo;

    /**
     * Init.
     *
     * @param path The path to the configuration file.
     */
    public static void init(String path) {
        File file = new File(path);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            jo = new JSONObject(content);
        } catch (IOException e) {
            log.error("Failed to load configuration file: {}", path);
            throw new RuntimeException("Failed to load configuration file: " + path);
        }
    }

    /**
     * Gets window width.
     *
     * @return The window width.
     */
    public static int getWindowWidth() {
        return jo.getInt("windowWidth");
    }

    /**
     * Gets window height.
     *
     * @return The window height.
     */
    public static int getWindowHeight() {
        return jo.getInt("windowHeight");
    }

    /**
     * Gets tile size.
     *
     * @return The tile size.
     */
    public static int getTileSize() {
        return jo.getInt("tileSize");
    }

    /**
     * Gets window name.
     *
     * @return The window name.
     */
    public static String getWindowName() {
        return jo.getString("windowName");
    }
}
