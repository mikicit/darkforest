package dev.mikicit.darkforest.view.component.inventory;

import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.view.InventoryView;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * The type Item view.
 * <p>
 * A GUI element representing an item in the inventory GUI.
 */
public class ItemView extends Button {
    private AItem itemRef;

    /**
     * Instantiates a new Item view.
     *
     * @param inventoryView the inventory view
     */
    public ItemView(InventoryView inventoryView) {
        super();

        setPrefWidth(64);
        setPrefHeight(64);
        setDefaultStyle();

        // Focus Events
        focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                setFocusedStyle();
                inventoryView.setItemInfo(itemRef);
            } else {
                setDefaultStyle();
            }
        });
    }

    private void setFocusedStyle() {
        setStyle(
                "-fx-background-color: #232933;"+
                "-fx-border-radius: 2px;" +
                "-fx-background-radius: 2px;" +
                "-fx-border-width: 1px;" +
                "-fx-border-style: solid;" +
                "-fx-border-color: #20ADB6;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;"
        );
    }

    private void setDefaultStyle() {
        setStyle(
                "-fx-background-color: #232933;"+
                "-fx-border-radius: 2px;" +
                "-fx-background-radius: 2px;" +
                "-fx-border-width: 1px;" +
                "-fx-border-style: solid;" +
                "-fx-border-color: #13171E;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;"
        );
    }

    /**
     * Sets item.
     *
     * @param item the item
     */
    public void setItem(AItem item) {
        itemRef = item;
        setGraphic(new ImageView(item.getImage()));
    }

    /**
     * Gets item.
     *
     * @return the item
     */
    public AItem getItem() {
        return itemRef;
    }
}
