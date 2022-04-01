package dev.mikicit.game.model;


public class GameModel {
    Player player;

    public GameModel() {
        player = new Player("");
    }

    public Player getPlayer() {
        return player;
    }
}
