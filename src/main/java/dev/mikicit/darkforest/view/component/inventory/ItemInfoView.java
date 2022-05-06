package dev.mikicit.darkforest.view.component.inventory;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ItemInfoView extends VBox {
    public ItemInfoView() {
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
        Text itemInfoTitle = new Text("Item characteristics:");
        itemInfoTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        itemInfoTitle.setFill(Color.WHITE);

        getChildren().add(itemInfoTitle);
    }
}
