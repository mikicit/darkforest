package dev.mikicit.darkforest.view;

import dev.mikicit.darkforest.controller.GameOverController;
import dev.mikicit.darkforest.core.Config;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * The type Game over view.
 * <p>
 * The class for representing the end of the game (game over).
 */
public class GameOverView extends AView {
    /**
     * Instantiates a new Game over view.
     *
     * @param controller the controller
     */
    public GameOverView(GameOverController controller) {
        this.controller = controller;
    }

    public void init() {
        // JavaFX init
        VBox vBox = new VBox();

        // Tile Pane Init
        vBox.setStyle("-fx-background-color: #232933; ");
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(32);

        // Title
        Text title = new Text("GAME OVER");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 64));
        title.setFill(Color.WHITE);

        // Description
        Text description = new Text("\n" +
                "Your path was very long and difficult, unfortunately, you fell from the hands of darkness.");
        description.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        description.setFill(Color.WHITE);

        // Main Menu
        Button mainMenu = new Button();
        mainMenu.setText("To Main Menu");

        mainMenu.setPrefHeight(56);
        mainMenu.setPrefWidth(200);
        mainMenu.setStyle("" +
                "-fx-background-color: #2b3542; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-cursor: hand;"
        );

        vBox.getChildren().add(title);
        vBox.getChildren().add(description);
        vBox.getChildren().add(mainMenu);

        scene = new Scene(vBox, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);

        // Attaching Event Listeners
        mainMenu.setOnMouseClicked(((GameOverController) controller)::goToMainMenuButtonClickHandler);
    }

    @Override
    public void render() {

    }
}
