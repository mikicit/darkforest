package dev.mikicit.darkforest.view;

import dev.mikicit.darkforest.controller.InventoryController;
import dev.mikicit.darkforest.core.Config;
import dev.mikicit.darkforest.model.GameModel;
import dev.mikicit.darkforest.model.component.Equipment;
import dev.mikicit.darkforest.model.component.Inventory;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Item.bottle.HealthBottle;
import dev.mikicit.darkforest.model.entity.Item.equipment.Armor;
import dev.mikicit.darkforest.model.entity.Item.equipment.Weapon;
import dev.mikicit.darkforest.model.entity.Player;
import dev.mikicit.darkforest.view.component.inventory.ItemView;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Observable;
import java.util.Observer;

/**
 * The type Inventory view.
 * <p>
 * Inventory view class.
 */
public class InventoryView extends AView implements Observer {
    private VBox itemInfoView;
    private Pane itemInfoContainer;
    private VBox itemsView;
    private Text inventoryTitle;
    private Text capacity;
    private FlowPane items;
    private FlowPane equippedItems;
    private Inventory inventory;
    private Player player;

    /**
     * Instantiates a new Inventory view.
     *
     * @param controller the controller
     */
    public InventoryView(InventoryController controller) {
        this.controller = controller;
    }

    public void init() {
        GameModel gameModel = GameModel.getInstance();
        player = gameModel.getPlayer();
        inventory = player.getInventory();

        // Main Pane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(32);
        gridPane.setVgap(48);
        gridPane.setStyle("-fx-background-color: #232933; -fx-padding: 48px 96px;");

        // Item Info Box
        itemInfoView = new VBox();

        itemInfoView.setStyle(
                "-fx-background-color: #1B2029; " +
                        "-fx-padding: 32px;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: #13171E"
        );

        itemInfoView.setSpacing(24);

        // Title
        Text itemInfoTitle = new Text("Item characteristics");
        itemInfoTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        itemInfoTitle.setFill(Color.WHITE);

        // Inside
        itemInfoContainer = new Pane();

        itemInfoView.getChildren().add(itemInfoTitle);
        itemInfoView.getChildren().add(itemInfoContainer);

        gridPane.add(itemInfoView, 0, 0);

        // Items Box
        itemsView = new VBox();

        itemsView.setStyle(
                "-fx-background-color: #1B2029; " +
                        "-fx-padding: 32px;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: #13171E"
        );

        itemsView.setSpacing(24);

        // Title
        inventoryTitle = new Text("Inventory");
        inventoryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        inventoryTitle.setFill(Color.WHITE);

        // Capacity
        capacity = new Text("Capacity " + inventory.getQuantity() + " / " + inventory.getCapacity());
        capacity.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        capacity.setFill(Color.WHITE);

        // Items
        items = new FlowPane();
        items.setHgap(24);
        items.setVgap(24);
        items.setPrefWidth(64);
        items.setPrefHeight(64);

        // Equipped Items Title
        Text equippedItemsTitle = new Text("Equipped Items");
        equippedItemsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        equippedItemsTitle.setFill(Color.WHITE);

        // Equipped Items
        equippedItems = new FlowPane();
        equippedItems.setHgap(24);
        equippedItems.setVgap(24);
        equippedItems.setPrefWidth(64);
        equippedItems.setPrefHeight(64);

        renderItems();
        renderEquippedItems();

        itemsView.getChildren().add(inventoryTitle);
        itemsView.getChildren().add(capacity);
        itemsView.getChildren().add(items);
        itemsView.getChildren().add(equippedItemsTitle);
        itemsView.getChildren().add(equippedItems);

        gridPane.add(itemsView, 1, 0);
        GridPane.setHgrow(itemsView, Priority.ALWAYS);
        GridPane.setVgrow(itemsView, Priority.ALWAYS);

        // Hints Box
        HBox hintsView = new HBox();
        hintsView.setStyle(
                "-fx-background-color: #1B2029; " +
                        "-fx-padding: 32px;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: #13171E"
        );

        hintsView.setSpacing(32);

        Text hintTextEquipeUnequipe = new Text("J: Equipe/Unequipe");
        hintTextEquipeUnequipe.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        hintTextEquipeUnequipe.setFill(Color.WHITE);

        Text hintTextUse = new Text("K: Use");
        hintTextUse.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        hintTextUse.setFill(Color.WHITE);

        Text hintTextRemove = new Text("L: Remove");
        hintTextRemove.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        hintTextRemove.setFill(Color.WHITE);

        hintsView.getChildren().addAll(hintTextEquipeUnequipe, hintTextUse, hintTextRemove);

        gridPane.add(hintsView, 0, 1, 2, 2);

        // Setting Scene
        scene = new Scene(gridPane, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);

        // Attaching Event Listeners
        scene.setOnKeyPressed(((InventoryController) controller)::keyPressedHandler);

        // Add observer
        inventory.addObserver(this);
        player.getEquipment().addObserver(this);
    }

    /**
     * Render equipped items.
     *
     * Displays currently equipped items.
     */
    private void renderEquippedItems() {
        equippedItems.getChildren().clear();

        ItemView weaponView = new ItemView(this);
        equippedItems.getChildren().add(weaponView);

        AItem currentWeapon = player.getCurrentWeapon();
        if (currentWeapon != null) {
            weaponView.setItem(currentWeapon);
        }

        ItemView armorView = new ItemView(this);
        equippedItems.getChildren().add(armorView);

        AItem currentArmor = player.getCurrentArmor();
        if (currentArmor != null) {
            armorView.setItem(currentArmor);
        }
    }

    /**
     * Render items.
     *
     * Displays actual items in the inventory.
     */
    private void renderItems() {
        items.getChildren().clear();

        for (int i = 0; i < inventory.getCapacity(); i++) {
            items.getChildren().add(new ItemView(this));
        }

        int count = 0;
        for (AItem item : inventory.getItems()) {
            ((ItemView) items.getChildren().get(count)).setItem(item);
            count++;
        }
    }

    /**
     * Sets item info.
     *
     * Shows the characteristics of the subject when the focus changes.
     *
     * @param item the item
     */
    public void setItemInfo(AItem item) {
        itemInfoContainer.getChildren().clear();
        if (item == null) return;

        VBox itemInfo = new VBox();
        itemInfo.setSpacing(8);

        // Name
        Text nameTitle = new Text("Name:");
        nameTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nameTitle.setFill(Color.WHITE);

        Text name = new Text(item.getName());
        name.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        name.setFill(Color.WHITE);

        itemInfo.getChildren().add(nameTitle);
        itemInfo.getChildren().add(name);

        // Weapon
        if (item instanceof Weapon) {
            Weapon weapon = (Weapon) item;

            // Damage
            Text damageTitle = new Text("Damage:");
            damageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            damageTitle.setFill(Color.WHITE);

            Text damage = new Text(String.valueOf(weapon.getDamage()));
            damage.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
            damage.setFill(Color.WHITE);

            // Damage Radius
            Text damageRadiusTitle = new Text("Damage Radius:");
            damageRadiusTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            damageRadiusTitle.setFill(Color.WHITE);

            Text damageRadius = new Text(String.valueOf(weapon.getRadius()));
            damageRadius.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
            damageRadius.setFill(Color.WHITE);

            itemInfo.getChildren().add(damageTitle);
            itemInfo.getChildren().add(damage);
            itemInfo.getChildren().add(damageRadiusTitle);
            itemInfo.getChildren().add(damageRadius);
        }

        // Armor
        if (item instanceof Armor) {
            Armor armor = (Armor) item;

            // Armor
            Text armorTitle = new Text("Armor:");
            armorTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            armorTitle.setFill(Color.WHITE);

            Text armorProperty = new Text(String.valueOf(armor.getArmor()));
            armorProperty.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
            armorProperty.setFill(Color.WHITE);

            itemInfo.getChildren().add(armorTitle);
            itemInfo.getChildren().add(armorProperty);
        }

        // Bottle
        if (item instanceof HealthBottle) {
            HealthBottle bottle = (HealthBottle) item;

            // Armor
            Text bottleTitle = new Text("Health:");
            bottleTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            bottleTitle.setFill(Color.WHITE);

            Text health = new Text("+" + bottle.getHealth());
            health.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
            health.setFill(Color.WHITE);

            itemInfo.getChildren().add(bottleTitle);
            itemInfo.getChildren().add(health);
        }

        itemInfoContainer.getChildren().add(itemInfo);
    }

    @Override
    public void render() {

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Inventory) {
            capacity.setText("Capacity " + inventory.getQuantity() + " / " + inventory.getCapacity());
            renderItems();
        }

        if (o instanceof Equipment) {
            renderEquippedItems();
        }
    }
}