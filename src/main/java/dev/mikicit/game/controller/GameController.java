package dev.mikicit.game.controller;

import dev.mikicit.game.core.Config;
import dev.mikicit.game.core.util.LongValue;
import dev.mikicit.game.model.GameModel;
import dev.mikicit.game.view.GameView;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class GameController {
    private final Stage primaryStage;
    private final GameModel gameModel;
    private final GameView gameView;
    private final GameLoop gameLoop;
    private final GameHandler gameHandler;

    public GameController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.gameModel = new GameModel();
        this.gameView = new GameView(this);
        this.gameLoop = new GameLoop();
        this.gameHandler = new GameHandler();

        primaryStage.setTitle(Config.getWindowName());
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setScene(gameView.getScene());

        gameHandler.init(gameView.getScene());

        primaryStage.show();
    }

    public void startGame() {
        gameLoop.start();
    }

    public void stopGame() {
        gameLoop.stop();
    }

    public void goToMainMenu() {

    }

    public void exit() {

    }

    public void endGame() {

    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public Stage getStage() {
        return primaryStage;
    }

    private class GameLoop extends AnimationTimer {
        private final LongValue lastNanoTime = new LongValue(System.nanoTime());

        @Override
        public void handle(long now) {
            double delta = (now - lastNanoTime.value) / 1000000000.0;
            lastNanoTime.value = now;

            gameView.update(delta);
            gameView.render(delta);
        }
    }
}