package dev.mikicit.darkforest.view.component.inventory;

import dev.mikicit.darkforest.model.entity.Item.AItem;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ItemView extends Button {
    private AItem itemRef;

    public ItemView() {
        super();
        setPrefWidth(64);
        setPrefHeight(64);
        setDefaultStyle();

        // Focus Events
        focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                setFocusedStyle();
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

    public void setItem(AItem item) {
        itemRef = item;
        setGraphic(new ImageView(item.getImage()));
    }

    public AItem getItem() {
        return itemRef;
    }
}
