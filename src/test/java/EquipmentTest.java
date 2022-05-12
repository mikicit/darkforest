import dev.mikicit.darkforest.model.component.Equipment;
import dev.mikicit.darkforest.model.component.Inventory;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import dev.mikicit.darkforest.model.entity.Item.equipment.Armor;
import dev.mikicit.darkforest.model.entity.Item.equipment.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class EquipmentTest {
    private Inventory mockedInventory;
    private Equipment equipment;

    @BeforeEach
    public void singleTestSetup() {
        mockedInventory = Mockito.mock(Inventory.class);
        equipment = new Equipment();
        Mockito.when(mockedInventory.addItem(any(AItem.class))).thenReturn(true);
    }

    @Test
    public void constructorTest() {
        // ASSERT
        Assertions.assertNull(equipment.getWeapon());
        Assertions.assertNull(equipment.getArmor());
    }

    @Test
    public void setEquipment() {
        // ARRANGE
        Weapon mockedWeapon = Mockito.mock(Weapon.class);
        Armor mockedArmor = Mockito.mock(Armor.class);

        // ACT
        equipment.setEquipment(mockedWeapon, mockedInventory);
        equipment.setEquipment(mockedArmor, mockedInventory);

        // ASSERT
        Assertions.assertEquals(mockedWeapon, equipment.getWeapon());
        Assertions.assertEquals(mockedArmor, equipment.getArmor());
    }

    @Test
    public void unsetEquipment() {
        // ARRANGE
        Weapon mockedWeapon = Mockito.mock(Weapon.class);
        Armor mockedArmor = Mockito.mock(Armor.class);
        equipment.setEquipment(mockedWeapon, mockedInventory);
        equipment.setEquipment(mockedArmor, mockedInventory);

        // ACT
        equipment.unsetEquipment(mockedWeapon, mockedInventory);
        equipment.unsetEquipment(mockedArmor, mockedInventory);

        // ASSERT
        Assertions.assertNull(equipment.getWeapon());
        Assertions.assertNull(equipment.getArmor());
    }
}
