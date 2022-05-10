package dev.mikicit.darkforest.view;

import dev.mikicit.darkforest.controller.MainMenuController;
import dev.mikicit.darkforest.core.Config;
import dev.mikicit.darkforest.core.PlayerConfig;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainMenuView extends AView {
    public MainMenuView(MainMenuController controller) {
        this.controller = controller;

        // JavaFX init
        VBox vBox = new VBox();
        ArrayList<Button> buttons = new ArrayList<>();

        // Tile Pane Init
        vBox.setStyle("-fx-background-color: #232933; ");
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(16);

        // New Game
        Button newGame = new Button();
        newGame.setText("New Game");
        buttons.add(newGame);

        // Load Game
        Button loadGame = new Button();
        loadGame.setText("Load Game");
        buttons.add(loadGame);
        if (!PlayerConfig.isExistPlayerSave()) {
            loadGame.setVisible(false);
            loadGame.setManaged(false);
        }

        // Exit Game
        Button exitGame = new Button();
        buttons.add(exitGame);
        exitGame.setText("Exit Game");

        for (Button button : buttons) {
            button.setPrefHeight(56);
            button.setPrefWidth(200);
            button.setStyle("" +
                    "-fx-background-color: #2b3542; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-size: 16px; " +
                    "-fx-font-weight: bold; " +
                    "-fx-cursor: hand;"
            );

            vBox.getChildren().add(button);
        }

        scene = new Scene(vBox, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);

        // Attaching Event Listeners
        newGame.setOnMouseClicked(controller::gameStartButtonClickHandler);
        loadGame.setOnMouseClicked(controller::gameLoadButtonClickHandler);
        exitGame.setOnMouseClicked(controller::exitGameButtonClickHandler);
    }

    @Override
    public void render() {

    }
}