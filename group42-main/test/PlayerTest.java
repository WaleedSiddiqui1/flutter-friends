import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PlayerTest {
    private Player player;
    private Inventory dummyInventory;
    private Pet dummyPet;

    @BeforeEach
    public void setUp() {
        // Create dummy objects for required dependencies
        dummyInventory = new Inventory();
        PetObserver dummyObserver = new PetObserver() {
            @Override
            public void update(Pet pet) {
                // Do nothing
            }
        };
        dummyPet = new Pet("TestPet", 'c', new PetObserver[]{dummyObserver});

        // Create player
        player = new Player("Waleed", 100, dummyInventory, dummyPet);
    }

    @Test
    void testConstructorInitialValues() {
        assertEquals("Waleed", player.getPlayerName());
        assertEquals(100, player.getScore());
        assertEquals(dummyInventory, player.getInventory());
        assertEquals(dummyPet, player.getPet());
    }

    @Test
    void testSetPlayerName() {
        player.setPlayerName("Waleed");
        assertEquals("Waleed", player.getPlayerName());
    }

    @Test
    void testSetScore() {
        player.setScore(200);
        assertEquals(200, player.getScore());
    }

    @Test
    void testSetPet() {
        Pet newPet = new Pet("Fluffy", 'd', new PetObserver[]{pet -> {}});
        player.setPet(newPet);
        assertEquals(newPet, player.getPet());
    }

    @Test
    void testSetInventory() {
        Inventory newInventory = new Inventory();
        player.setInventory(newInventory);
        assertEquals(newInventory, player.getInventory());
    }
    @Test
    void testPlayIncreasesHappiness() {
        int before = dummyPet.getHappiness();
        player.play();
        assertTrue(dummyPet.getHappiness() > before);
    }

}