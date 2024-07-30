package dev.mikita.darkforest.controller;

import dev.mikita.darkforest.core.StateManager;
import dev.mikita.darkforest.model.GameModel;
import dev.mikita.darkforest.model.component.Inventory;
import dev.mikita.darkforest.model.entity.Item.AItem;
import dev.mikita.darkforest.model.entity.Item.bottle.HealthBottle;
import dev.mikita.darkforest.model.entity.Item.equipment.AEquipment;
import dev.mikita.darkforest.model.entity.Player;
import dev.mikita.darkforest.view.InventoryView;
import dev.mikita.darkforest.view.component.inventory.ItemView;
import javafx.scene.input.KeyEvent;

/**
 * The type Inventory controller.
 * <p>
 * Game inventory controller.
 */
public class InventoryController extends AController {
    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
        view = new InventoryView(this);
        view.init();
    }

    /**
     * Key pressed handler.
     *
     * @param e The event.
     */
    public void keyPressedHandler(KeyEvent e) {
        Player player = GameModel.getInstance().getPlayer();
        Inventory inventory = player.getInventory();
        String code = e.getCode().toString();

        if (code.equals("ESCAPE") || code.equals("I")) {
            StateManager.continueGame();
        }

        if (code.equals("J")) {
            if (e.getTarget() instanceof ItemView) {
                AItem item = ((ItemView) e.getTarget()).getItem();

                if (item instanceof AEquipment) {
                    if (inventory.isInInventory(item)) {
                        ((AEquipment) item).equip(player);
                    } else {
                        ((AEquipment) item).unEquip(player);
                    }
                }
            }
        }

        if (code.equals("K")) {
            if (e.getTarget() instanceof ItemView) {
                AItem item = ((ItemView) e.getTarget()).getItem();

                if (item instanceof HealthBottle) {
                    ((HealthBottle) item).use(player);
                }
            }
        }

        if (code.equals("L")) {
            if (e.getTarget() instanceof ItemView) {
                AItem item = ((ItemView) e.getTarget()).getItem();

                if (item != null) {
                    item.drop(player);
                }

            }
        }
    }

    @Override
    public void tick(double delta) {}
}
