package dev.mikicit.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Start extends Application {
    private SpriteManager spriteManager;

    @Override
    public void start(Stage primaryStage) {
        spriteManager = new SpriteManager();

        Pane root = new Pane();
        Canvas canvas = new Canvas(1500, 1500);

        Pane pane = new Pane();
        TilePane tilePanel = new TilePane();

        tilePanel.setHgap(8);
        tilePanel.setPrefColumns(4);

        pane.getChildren().add(canvas);
        root.getChildren().add(tilePanel);
        root.getChildren().add(pane);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        pane.setStyle("-fx-background-color: green");
        int windowWidth = 1280;
        int windowHeight = 720;
        Scene scene = new Scene(root, windowWidth, windowHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RPG Game");
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        // Init Player
        Player player = new Player("God");

        for (int i = 0; i < 20; i++) {
            tilePanel.getChildren().add(new ImageView(new Image("sprite/player.png")));
        }

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

                gc.clearRect(0, 0, 500,500);
                player.sprite.setVelocity(0, 0);

                if (input.contains("LEFT"))
                    player.moveLeft();
                if (input.contains("RIGHT"))
                    player.moveRight();
                if (input.contains("UP"))
                    player.moveUp();
                if (input.contains("DOWN"))
                    player.moveDown();

                player.sprite.update(elapsedTime);
                player.sprite.render(gc);
            }
        }.start();

        // Open Window
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
