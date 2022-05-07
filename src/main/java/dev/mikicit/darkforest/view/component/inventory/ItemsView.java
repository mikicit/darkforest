package dev.mikicit.darkforest.view.component.inventory;

import dev.mikicit.darkforest.model.component.Inventory;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Observable;
import java.util.Observer;

public class ItemsView extends VBox implements Observer {
    private final FlowPane flowPane;
    private final Text capacity;

    public ItemsView(Inventory inventory, ItemInfoView itemInfoView) {
        super();

        setStyle(
                "-fx-background-color: #1B2029; " +
                "-fx-padding: 32px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-style: solid;" +
                "-fx-border-color: #13171E"
        );

        setSpacing(24);

        // Title
        Text inventoryTitle = new Text("Inventory");
        inventoryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        inventoryTitle.setFill(Color.WHITE);

        // Capacity
        capacity = new Text("Capacity " + inventory.getQuantity() + " / " + inventory.getCapacity());
        capacity.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        capacity.setFill(Color.WHITE);

        // Items
        flowPane = new FlowPane();
        flowPane.setHgap(24);
        flowPane.setVgap(24);
        flowPane.setPrefWidth(64);
        flowPane.setPrefHeight(64);

        renderItems(inventory);

        getChildren().add(inventoryTitle);
        getChildren().add(capacity);
        getChildren().add(flowPane);
    }

    private void renderItems(Inventory inventory) {
        flowPane.getChildren().clear();

        for (int i = 0; i < inventory.getCapacity(); i++) {
            flowPane.getChildren().add(new ItemView());
        }

        int count = 0;
        for (AItem item : inventory.getItems()) {
            ((ItemView) flowPane.getChildren().get(count)).setItem(item);
            count++;
        }
    }

    public FlowPane getItemsContainer() {
        return flowPane;
    }

    @Override
    public void update(Observable o, Object arg) {
        Inventory inventory = (Inventory) o;
        capacity.setText("Capacity " + inventory.getQuantity() + " / " + inventory.getCapacity());

        renderItems(inventory);
    }
}
