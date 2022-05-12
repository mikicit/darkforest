import dev.mikicit.darkforest.model.component.Inventory;
import dev.mikicit.darkforest.model.entity.Item.AItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    public void singleTestSetup() {
        inventory = new Inventory(2);
    }

    @Test
    public void constructorTest() {
        // ARRANGE
        int expectedValue = 2;

        // ACT
        Inventory inventory = new Inventory(2);
        int result = inventory.getCapacity();

        // ASSERT
        Assertions.assertEquals(expectedValue, result);
    }

    @Test
    public void addOneItem() {
        // ARRANGE
        int expectedValue = inventory.getQuantity() + 1;

        // ACT
        AItem mockedItem = Mockito.mock(AItem.class);
        inventory.addItem(mockedItem);

        int result = inventory.getQuantity();

        // ASSERT
        Assertions.assertTrue(inventory.isInInventory(mockedItem));
        Assertions.assertEquals(expectedValue, result);
    }

    @Test
    public void removeOneItem() {
        // ARRANGE
        int expectedValue = 1;

        // ACT
        AItem mockedItem1 = Mockito.mock(AItem.class);
        AItem mockedItem2 = Mockito.mock(AItem.class);

        inventory.addItem(mockedItem1);
        inventory.addItem(mockedItem2);

        inventory.removeItem(mockedItem1);

        int result = inventory.getQuantity();

        // ASSERT
        Assertions.assertFalse(inventory.isInInventory(mockedItem1));
        Assertions.assertEquals(expectedValue, result);
    }

    @Test
    public void maxItemSize() {
        // ARRANGE
        int expectedValue = inventory.getCapacity();

        // ACT
        for (int i = 0; i < expectedValue + 2; i++) {
            inventory.addItem(Mockito.mock(AItem.class));
        }

        int result = inventory.getQuantity();

        // ASSERT
        Assertions.assertEquals(expectedValue, result);
    }
}
