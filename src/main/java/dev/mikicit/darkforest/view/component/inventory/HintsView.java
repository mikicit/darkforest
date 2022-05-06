package dev.mikicit.darkforest.view.component.inventory;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HintsView extends HBox {
    public HintsView() {
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

        setSpacing(32);

        Text hintTextEquipeUnequipe = new Text("J: Equipe/Unequipe");
        hintTextEquipeUnequipe.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        hintTextEquipeUnequipe.setFill(Color.WHITE);

        Text hintTextUse = new Text("K: Use");
        hintTextUse.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        hintTextUse.setFill(Color.WHITE);

        Text hintTextRemove = new Text("L: Remove");
        hintTextRemove.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        hintTextRemove.setFill(Color.WHITE);

        getChildren().addAll(hintTextEquipeUnequipe, hintTextUse, hintTextRemove);
    }
}
