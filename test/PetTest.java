import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PetTest {
    private Pet pet;

    @BeforeEach
    void setUp() {
        // Create dummy observer (since the constructor needs one)
        PetObserver dummyObserver = new PetObserver() {
            @Override
            public void update(Pet pet) {
                // Do nothing for this test
            }
        };

        // Create pet with default stats
        pet = new Pet("Fluffy", 'c', new PetObserver[]{dummyObserver});
    }

    @Test
    void testConstructorInitialValues() {
        assertEquals("Fluffy", pet.getName());
        assertEquals('c', pet.getType());
        assertEquals(50, pet.getHealth());
        assertEquals(50, pet.getFullness());
        assertEquals(50, pet.getHappiness());
        assertEquals(50, pet.getSleep());
        assertEquals(0, pet.getStage());
        assertTrue(pet.getState() instanceof NormalState);
    }

    @Test
    void testIncreaseFullness() {
        pet.increaseFullness(30);
        assertEquals(80, pet.getFullness());
    }

    @Test
    void testIncreaseFullnessCapsAt100() {
        pet.increaseFullness(100);
        assertEquals(100, pet.getFullness());
    }

    @Test
    void testIncreaseHappiness() {
        pet.increaseHappiness(20);
        assertEquals(70, pet.getHappiness());
    }

    @Test
    void testSetHealth() {
        pet.setHealth(10);
        assertEquals(10, pet.getHealth());
    }

    @Test
    void testPlayIncreasesHappiness() {
        pet.play();
        assertEquals(100, pet.getHappiness()); // 50 + 50 = 100
    }

    @Test
    void testSetAndGetSleep() {
        pet.setSleep(20);
        assertEquals(20, pet.getSleep());
    }

    @Test
    void testSetAndGetFullness() {
        pet.setFullness(25);
        assertEquals(25, pet.getFullness());
    }

    @Test
    void testSetAndGetHappiness() {
        pet.setHappiness(88);
        assertEquals(88, pet.getHappiness());
    }

    @Test
    void testSetStage() {
        pet.setStage(1);
        assertEquals(1, pet.getStage());
    }

    @Test
    void testChangeState(){
        PetState newState = new NormalState();
        pet.setState(newState);
        assertEquals(newState, pet.getState());
    }
}
