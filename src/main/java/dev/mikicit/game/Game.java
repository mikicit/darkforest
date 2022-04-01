//package dev.mikicit.game;
//
//import dev.mikicit.game.model.Sprite;
//import dev.mikicit.game.model.SpriteManager;
//import dev.mikicit.game.core.util.tileEngine.TileMap;
//import dev.mikicit.game.core.util.tileEngine.TileMapReader;
//import dev.mikicit.game.core.util.LongValue;
//import javafx.animation.AnimationTimer;
//import javafx.application.Application;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import java.util.ArrayList;
//
//public class Game extends Application {
//    private final int tileSize = 64;
//    private final int windowWidth = 1200;
//    private final int windowHeight = 720;
//    private int mapWidth;
//    private int mapHeight;
//
//    private SpriteManager spriteManager;
//    private TileMap tileMap;
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Sprites Init
//        spriteManager = new SpriteManager();
//        Sprite playerSprite = new Sprite();
//        playerSprite.setImage("sprite/player.png");
//        double playerStartX = (windowWidth / 2) - playerSprite.getWidth() / 2;
//        double playerStartY = (windowHeight / 2) - playerSprite.getHeight() / 2;
//        playerSprite.setPosition(playerStartX, playerStartY);
//        spriteManager.addPlayer(playerSprite);
//
//        // TileMap Init
//        TileMapReader tileMapReader = new TileMapReader();
//        tileMap = tileMapReader.createTileMap("src/main/resources/map/map.txt", tileSize);
//        mapWidth = tileMap.getWidth() * tileSize;
//        mapHeight = tileMap.getHeight() * tileSize;
//
//        // Entity Init
//        Player player = new Player("God");
//
//        Pane root = new Pane();
//        Canvas canvas = new Canvas(mapWidth, mapHeight);
//        Pane pane = new Pane();
//
//        pane.getChildren().add(canvas);
//        root.getChildren().add(pane);
//
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        Scene scene = new Scene(root, windowWidth, windowHeight);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("RPG Game");
//        primaryStage.setResizable(false);
//        primaryStage.centerOnScreen();
//
//        // Input
//        ArrayList<String> input = new ArrayList<>();
//
//        // Events
//        class KeyPressedHandler implements EventHandler<KeyEvent> {
//            @Override
//            public void handle(KeyEvent e) {
//                String code = e.getCode().toString();
//                if (!input.contains(code)) {
//                    input.add(code);
//                }
//            }
//        }
//
//        class KeyReleasedHandler implements EventHandler<KeyEvent> {
//            @Override
//            public void handle(KeyEvent e) {
//                String code = e.getCode().toString();
//                input.remove(code);
//            }
//        }
//
//        KeyPressedHandler keyPressedHandler = new KeyPressedHandler();
//        KeyReleasedHandler keyReleasedHandler = new KeyReleasedHandler();
//
//        scene.setOnKeyPressed(keyPressedHandler);
//        scene.setOnKeyReleased(keyReleasedHandler);
//
//        // Game Loop
//        LongValue lastNanoTime = new LongValue(System.nanoTime());
//
//        new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                double elapsedTime = (now - lastNanoTime.value) / 1000000000.0;
//                lastNanoTime.value = now;
//
//                gc.clearRect(0, 0, mapWidth, mapHeight);
//                playerSprite.setVelocity(0, 0);
//
//                int convertedX = tileMap.convertPixelToTile(playerSprite.getX());
//                int convertedY = tileMap.convertPixelToTile(playerSprite.getY());
//
//                System.out.println("X: " + convertedX + ", Y: " + convertedY + ", TileID: " + tileMap.getTile(convertedX, convertedY).getId());
//
//                if (input.contains("LEFT") && playerSprite.getX() - 1 > 0) {
//                    playerSprite.moveLeft();
//                }
//                if (input.contains("RIGHT") && playerSprite.getX() + playerSprite.getWidth() + 1 < mapWidth) {
//                    playerSprite.moveRight();
//                }
//                if (input.contains("UP") && playerSprite.getY() - 1 > 0) {
//                    playerSprite.moveUp();
//                }
//                if (input.contains("DOWN") && playerSprite.getY() + playerSprite.getHeight() + 1 < mapHeight) {
//                    playerSprite.moveDown();
//                }
//
//                spriteManager.update(elapsedTime);
//                tileMap.render(gc);
//                spriteManager.render(gc);
//
//                // Camera
//                double cameraX = ((playerSprite.getX() - windowWidth / 2) + playerSprite.getWidth() / 2);
//                double cameraY = ((playerSprite.getY() - windowHeight / 2) + playerSprite.getHeight() / 2);
//
//                if ((playerSprite.getY() + windowHeight / 2 + playerSprite.getHeight() / 2) < mapHeight &&
//                    (playerSprite.getY() - windowHeight / 2 + playerSprite.getHeight() / 2) > 0
//                ) {
//                    pane.setTranslateY(cameraY * -1);
//                }
//
//                if ((playerSprite.getX() + windowWidth / 2 + playerSprite.getWidth() / 2) < mapWidth &&
//                    (playerSprite.getX() - windowWidth / 2 + playerSprite.getWidth() / 2) > 0
//                ) {
//                    pane.setTranslateX(cameraX * -1);
//                }
//            }
//        }.start();
//
//        // Open Window
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
