import dev.mikicit.darkforest.model.entity.Monster;
import dev.mikicit.darkforest.model.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MonsterTest {
    private Monster monster;
    private Player mockedPlayer;

    @BeforeEach
    public void singleTestSetup() {
        monster = new Monster("Test Monster", 500, 100, 50, 50, 60, 700);
        mockedPlayer = Mockito.mock(Player.class);
        Mockito.when(mockedPlayer.getDamage()).thenReturn(100.0);
    }

    @Test
    public void inAttackTest() {
        // ARRANGE
        int expectedValue = 400;

        // ACT
        monster.inAttack(mockedPlayer);

        // ASSERT
        Assertions.assertEquals(expectedValue, monster.getHealth());
    }

    @Test
    public void inAttackMoreThanThereAreLivesTest() {
        // ARRANGE
        Mockito.when(mockedPlayer.getDamage()).thenReturn(600.0);
        int expectedValue = 0;

        // ACT
        monster.inAttack(mockedPlayer);

        // ASSERT
        Assertions.assertEquals(expectedValue, monster.getHealth());
    }

    @Test
    public void deathTest() {
        // ARRANGE
        Mockito.when(mockedPlayer.getDamage()).thenReturn(600.0);

        // ACT
        monster.inAttack(mockedPlayer);

        Assertions.assertTrue(monster.isDead());
    }
}
