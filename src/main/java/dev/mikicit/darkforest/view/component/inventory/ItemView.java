package dev.mikicit.darkforest.view.component.inventory;

import dev.mikicit.darkforest.model.entity.Item.AItem;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ItemView extends Button {
    AItem itemRef;

    public ItemView(AItem item) {
        super();
        itemRef = item;

        setGraphic(new ImageView(item.getImage()));
        setPrefWidth(64);
        setPrefHeight(64);
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

    public AItem getItem() {
        return itemRef;
    }
}
