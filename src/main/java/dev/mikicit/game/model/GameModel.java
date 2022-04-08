package dev.mikicit.game.model;

import dev.mikicit.game.entity.Monster;
import dev.mikicit.game.entity.Player;
import java.util.ArrayList;

public class GameModel extends AModel {
    ArrayList<Monster> monsters = new ArrayList<>();
    Player player;

    public GameModel() {
        player = new Player("Mikita");
        monsters.add(new Monster("Skeleton"));
    }

    public Player getPlayer() {
        return player;
    }

    public void update(double delta) {
        player.update(delta);

        for (Monster monster : monsters) {
            monster.update(delta);
        }
    }
}
