import dev.mikicit.darkforest.model.component.HP;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HPTest {
    private HP hp;

    @BeforeEach
    public void singleTestSetup() {
        hp = new HP(500);
    }

    @Test
    public void constructorTest() {
        // ARRANGE
        int expectedValue = 500;

        // ACT
        HP hp = new HP(500);

        // ASSERT
        Assertions.assertEquals(expectedValue, hp.getHealth());
        Assertions.assertEquals(expectedValue, hp.getInitialHealth());
    }

    @Test
    public void reduceHealth() {
        // ARRANGE
        int expectedValue = 300;

        // ACT
        hp.reduceHealth(200);

        // ASSERT
        Assertions.assertEquals(expectedValue, hp.getHealth());
    }

    @Test
    public void reduceHealthMoreAvailable() {
        // ARRANGE
        int expectedValue = 0;

        // ACT
        hp.reduceHealth(1000);

        // ASSERT
        Assertions.assertEquals(expectedValue, hp.getHealth());
    }


    @Test
    public void addHealth() {
        // ARRANGE
        int expectedValue = 400;

        // ACT
        HP hp = new HP(500);
        hp.reduceHealth(300);
        hp.addHealth(200);

        // ASSERT
        Assertions.assertEquals(expectedValue, hp.getHealth());
    }

    @Test
    public void addHealthMoreAvailable() {
        // ARRANGE
        int expectedValue = 500;

        // ACT
        HP hp = new HP(500);
        hp.addHealth(200);

        // ASSERT
        Assertions.assertEquals(expectedValue, hp.getHealth());
    }
}
