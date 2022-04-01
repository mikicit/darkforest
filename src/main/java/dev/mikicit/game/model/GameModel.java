package dev.mikicit.game.model;


import dev.mikicit.game.entity.Player;

public class GameModel {
    Player player;

    public GameModel() {
        player = new Player("");
    }

    public Player getPlayer() {
        return player;
    }
}
