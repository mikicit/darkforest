package dev.mikicit.darkforest.view;

import dev.mikicit.darkforest.controller.GameMenuController;
import dev.mikicit.darkforest.core.Config;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameMenuView extends AView{
    public GameMenuView(GameMenuController controller) {
        this.controller = controller;

        // JavaFX init
        VBox vBox = new VBox();
        ArrayList<Button> buttons = new ArrayList<>();

        // Tile Pane Init
        vBox.setStyle("-fx-background-color: #232933; ");
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(16);

        Button continueGame = new Button();
        Button saveGame = new Button();
        Button toMainMenu = new Button();
        Button exitGame = new Button();

        buttons.add(continueGame);
        buttons.add(saveGame);
        buttons.add(toMainMenu);
        buttons.add(exitGame);

        continueGame.setText("Continue Game");
        saveGame.setText("Save Game");
        toMainMenu.setText("To Main Menu");
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
        scene.setOnKeyPressed(controller::keyPressedHandler);
        continueGame.setOnMouseClicked(controller::gameContinueButtonClickHandler);
        saveGame.setOnMouseClicked(controller::gameSaveButtonClickHandler);
        toMainMenu.setOnMouseClicked(controller::toMainMenuButtonClickHandler);
        exitGame.setOnMouseClicked(controller::exitGameButtonClickHandler);
    }

    @Override
    public void render() {

    }
}
