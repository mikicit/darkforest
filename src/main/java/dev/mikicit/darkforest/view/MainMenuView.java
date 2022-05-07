package dev.mikicit.darkforest.view;

import dev.mikicit.darkforest.controller.MainMenuController;
import dev.mikicit.darkforest.core.Config;
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

        Button startGame = new Button();
        Button saveGame = new Button();
        Button exitGame = new Button();

        buttons.add(startGame);
        buttons.add(saveGame);
        buttons.add(exitGame);

        startGame.setText("Start Game");
        saveGame.setText("Load Game");
        exitGame.setText("Exit Game");

        for (Button button : buttons) {
            button.setPrefHeight(56);
            button.setPrefWidth(200);
            button.setStyle("" +
                    "-fx-background-color: #2b3542; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-size: 16px; " +
                    "-fx-font-weight: bold; "
            );

            vBox.getChildren().add(button);
        }

        scene = new Scene(vBox, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);

        // Attaching Event Listeners
        startGame.setOnMouseClicked(controller::gameStartButtonClickHandler);
        exitGame.setOnMouseClicked(controller::exitGameButtonClickHandler);
    }

    @Override
    public void render() {

    }
}