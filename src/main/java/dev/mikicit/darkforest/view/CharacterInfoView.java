package dev.mikicit.darkforest.view;

import dev.mikicit.darkforest.controller.CharacterInfoController;
import dev.mikicit.darkforest.controller.InventoryController;
import dev.mikicit.darkforest.core.Config;
import dev.mikicit.darkforest.model.GameModel;
import dev.mikicit.darkforest.model.component.Inventory;
import dev.mikicit.darkforest.model.entity.Player;
import dev.mikicit.darkforest.view.component.inventory.HintsView;
import dev.mikicit.darkforest.view.component.inventory.ItemInfoView;
import dev.mikicit.darkforest.view.component.inventory.ItemsView;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class CharacterInfoView extends AView {
    public CharacterInfoView(CharacterInfoController controller) {
        this.controller = controller;
    }

    public void init() {
        GameModel gameModel = GameModel.getInstance();
        Player player = gameModel.getPlayer();
        Inventory inventory = player.getInventory();

        // Main Pane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(32);
        gridPane.setVgap(48);
        gridPane.setStyle("-fx-background-color: #232933; -fx-padding: 48px 96px;");

        // Item Info Box
        ItemInfoView itemInfoView = new ItemInfoView();
        gridPane.add(itemInfoView, 0, 0);

        // Items Box
        ItemsView itemsView = new ItemsView(inventory, itemInfoView);

        gridPane.add(itemsView, 1, 0);
        GridPane.setHgrow(itemsView, Priority.ALWAYS);
        GridPane.setVgrow(itemsView, Priority.ALWAYS);

        // Hints Box
        HintsView hintsView = new HintsView();
        gridPane.add(hintsView, 0, 1, 2, 2);

        // Setting Scene
        scene = new Scene(gridPane, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);

        // Attaching Event Listeners
        scene.setOnKeyPressed(((CharacterInfoController) controller)::keyPressedHandler);

        // Add observer
        inventory.addObserver(itemsView);
    }

    @Override
    public void render() {

    }
}
