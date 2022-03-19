package dev.mikicit.game;

import dev.mikicit.game.entity.Player;
import dev.mikicit.game.util.LongValue;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Game extends Application {
    private SpriteManager spriteManager;
    private TileMap tileMap;

    @Override
    public void start(Stage primaryStage) {
        // Sprites Init
        spriteManager = new SpriteManager();
        Sprite playerSprite = new Sprite();
        playerSprite.setImage("sprite/player.png");
        spriteManager.addPlayer(playerSprite);

        // TileMap Init
        TileMapManager tileMapManager = new TileMapManager();
        tileMap = tileMapManager.createTileMap("src/main/resources/map/map.txt");

        // Entity Init
        Player player = new Player("God");

        Pane root = new Pane();
        Canvas canvas = new Canvas(tileMap.getWidth() * 64, tileMap.getHeight() * 64);
        Pane pane = new Pane();

        pane.getChildren().add(canvas);
        root.getChildren().add(pane);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(root, 1200, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RPG Game");
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        // Input
        ArrayList<String> input = new ArrayList<>();

        // Events
        class KeyPressedHandler implements EventHandler<KeyEvent> {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (!input.contains(code)) {
                    input.add(code);
                }
            }
        }

        class KeyReleasedHandler implements EventHandler<KeyEvent> {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                input.remove(code);
            }
        }

        KeyPressedHandler keyPressedHandler = new KeyPressedHandler();
        KeyReleasedHandler keyReleasedHandler = new KeyReleasedHandler();

        scene.setOnKeyPressed(keyPressedHandler);
        scene.setOnKeyReleased(keyReleasedHandler);

        // Game Loop
        LongValue lastNanoTime = new LongValue(System.nanoTime());

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double elapsedTime = (now - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = now;

                gc.clearRect(0, 0, tileMap.getWidth() * 64,tileMap.getHeight() * 64);
                playerSprite.setVelocity(0, 0);

                if (input.contains("LEFT"))
                    playerSprite.moveLeft();
                if (input.contains("RIGHT"))
                    playerSprite.moveRight();
                if (input.contains("UP"))
                    playerSprite.moveUp();
                if (input.contains("DOWN"))
                    playerSprite.moveDown();

                spriteManager.update(elapsedTime);
                tileMap.render(gc);
                spriteManager.render(gc);
            }
        }.start();

        System.out.println(tileMap);

        // Open Window
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
