package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.tile.TileMap;
import dev.mikicit.darkforest.core.Config;
import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.model.GameModel;
import dev.mikicit.darkforest.model.entity.Monster;
import dev.mikicit.darkforest.model.entity.Player;
import dev.mikicit.darkforest.view.GameView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class GameController extends AController {
    protected ArrayList<String> input = new ArrayList<>();
    private final GameModel gameModel;
    private final Player player;
    private final ArrayList<Monster> monsters;
    private final TileMap tileMap;
    private final Pane canvasRoot;

    public GameController() {
        gameModel = GameModel.getInstance();
        view = new GameView(this);

        // Links to game entitles
        player = gameModel.getPlayer();
        monsters = gameModel.getMonsters();
        tileMap = gameModel.getTileMap();
        canvasRoot = ((GameView) view).getCanvasRoot();
    }

    // Event Handlers
    public void keyPressedHandler(KeyEvent e) {
        String code = e.getCode().toString();
        if (!input.contains(code)) {
            input.add(code);
        }

        if (code.equals("ESCAPE")) {
            StateManager.goToMainMenu();
        }
    }

    public void keyReleasedHandler(KeyEvent e) {
        String code = e.getCode().toString();
        input.remove(code);
    }

    @Override
    public void tick(double delta) {
        // Update

        // Player moving
        player.setVelocity(0, 0);

        if (input.contains("A") && player.getX() - 1 > 0) {
            player.moveLeft();
        }

        if (input.contains("D") && player.getX() + player.getWidth() + 1 < tileMap.getMapWidth()) {
            player.moveRight();
        }

        if (input.contains("W") && player.getY() - 1 > 0) {
            player.moveUp();
        }

        if (input.contains("S") && player.getY() + player.getHeight() + 1 < tileMap.getMapHeight()) {
            player.moveDown();
        }

        // Camera
        double offsetX = ((player.getX() - (double) (Config.getWindowWidth() / 2)) + player.getWidth() / 2);
        double offsetY = ((player.getY() - (double) (Config.getWindowHeight() / 2)) + player.getHeight() / 2);

        // X camera
        if (offsetY > 0 && offsetY < tileMap.getMapHeight() - Config.getWindowHeight()) {
            canvasRoot.setTranslateY(offsetY * -1);
        }

        // Y camera
        if (offsetX > 0 && offsetX < tileMap.getMapWidth() - Config.getWindowWidth()) {
            canvasRoot.setTranslateX(offsetX * -1);
        }

        // Check intersections
        for (Monster monster : monsters) {
            System.out.println(player.intersectsCollectionBox(monster));
        }

        player.update(delta);

        // Render
        view.render();
    }
}
