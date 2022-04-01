package dev.mikicit.game.core;

public class Config {
    private static final String windowName = "RPG Game";
    private static final int windowWidth = 1200;
    private static final int windowHeight = 700;
    private static final int tileSize = 64;

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public static int getTileSize() {
        return tileSize;
    }

    public static String getWindowName() {
        return windowName;
    }
}
