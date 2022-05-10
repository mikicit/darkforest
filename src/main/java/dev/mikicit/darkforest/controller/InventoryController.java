package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.model.GameModel;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Item.bottle.HealthBottle;
import dev.mikicit.darkforest.model.entity.Item.equipment.AEquipment;
import dev.mikicit.darkforest.view.InventoryView;
import dev.mikicit.darkforest.view.component.inventory.ItemView;
import javafx.scene.input.KeyEvent;

public class InventoryController extends AController {
    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
        view = new InventoryView(this);
        view.init();
    }

    // Event Handlers
    public void keyPressedHandler(KeyEvent e) {
        String code = e.getCode().toString();

        if (code.equals("ESCAPE") || code.equals("I")) {
            StateManager.continueGame();
        }

        if (code.equals("J")) {
            if (e.getTarget() instanceof ItemView) {
                AItem item = ((ItemView) e.getTarget()).getItem();

                if (item instanceof AEquipment) {
                    ((AEquipment) item).equip(GameModel.getInstance().getPlayer());
                }
            }
        }

        if (code.equals("K")) {
            if (e.getTarget() instanceof ItemView) {
                AItem item = ((ItemView) e.getTarget()).getItem();

                if (item instanceof HealthBottle) {
                    ((HealthBottle) item).use(GameModel.getInstance().getPlayer());
                }
            }
        }

        if (code.equals("L")) {
            if (e.getTarget() instanceof ItemView) {
                AItem item = ((ItemView) e.getTarget()).getItem();

                if (item != null) {
                    item.drop(GameModel.getInstance().getPlayer());
                }

            }
        }
    }

    @Override
    public void tick(double delta) {

    }
}
