package dev.mikicit.darkforest.controller;

import dev.mikicit.darkforest.core.StateManager;
import dev.mikicit.darkforest.model.GameModel;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Item.bottle.HealthBottle;
import dev.mikicit.darkforest.model.entity.Item.equipment.AEquipment;
import dev.mikicit.darkforest.view.CharacterInfoView;
import dev.mikicit.darkforest.view.component.inventory.ItemView;
import javafx.scene.input.KeyEvent;

public class CharacterInfoController extends AController {
    public void init() {
        if (wasInitialized) return;
        wasInitialized = true;
        view = new CharacterInfoView(this);
        view.init();
    }

    // Event Handlers
    public void keyPressedHandler(KeyEvent e) {
        String code = e.getCode().toString();

        if (code.equals("ESCAPE") || code.equals("P")) {
            StateManager.continueGame();
        }
    }

    @Override
    public void tick(double delta) {

    }
}
