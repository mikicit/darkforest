package dev.mikicit;

import dev.mikicit.objects.Player;

public class Start {
    public static void main (String[] args) {
        Player player = new Player();

        Game game = new Game();
        game.startGame();
    }
}
