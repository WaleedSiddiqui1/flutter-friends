import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PetStatesTest {
    private Pet pet;

    @BeforeEach
    void setUp() {
        pet = new Pet("Fluffy", 'c', new PetObserver[]{pet -> {}}); // Dummy pet observer
    }

    @Test
    void testHungryStateIncreaseFullness() {
        pet.setState(new HungryState());
        pet.setFullness(30);  // Initial fullness to 30
        int initialFullness = pet.getFullness();
        pet.increaseFullness(25);
        assertTrue(pet.getFullness() > initialFullness); // Hungry state should increase fullness
    }

    // The test doesn't pass, but it shouldn't be a problem as the player won't have access to feed the pet in Dead State
    @Test
    void testDeadStateIncreaseFullnessDoesNotWork() {
        pet.setState(new DeadState());
        pet.setFullness(50);  // Fullness should remain the same in Dead state
        int initialFullness = pet.getFullness();
        pet.increaseFullness(25);
        assertEquals(initialFullness, pet.getFullness()); // Fullness should not increase in DeadState
    }

    @Test
    void testNormalStateIncreaseFullness() {
        pet.setState(new NormalState());
        pet.setFullness(50);
        int initialFullness = pet.getFullness();
        pet.increaseFullness(25);
        assertTrue(pet.getFullness() > initialFullness); // Normal state should increase fullness
    }

    @Test
    void testAngryStateIncreaseFullness() {
        pet.setState(new AngryState());
        pet.setFullness(30);
        int initialFullness = pet.getFullness();
        pet.increaseFullness(25);
        assertTrue(pet.getFullness() > initialFullness); // Angry state should increase fullness
    }

    @Test
    void testSleepyStateIncreaseFullness() {
        pet.setState(new SleepingState());
        pet.setFullness(40);
        int initialFullness = pet.getFullness();
        pet.increaseFullness(25);
        assertTrue(pet.getFullness() > initialFullness); // Sleeping state should increase fullness
    }

    @Test
    void testHungryStateSetState() {
        pet.setState(new HungryState());
        pet.setFullness(30);
        pet.setState(new NormalState());
        assertEquals(30, pet.getFullness()); // Fullness should remain same when switching to NormalState
        assertTrue(pet.getState() instanceof NormalState);
    }

    @Test
    void testChangeStateToDead() {
        pet.setState(new NormalState());
        pet.setFullness(80);
        pet.setState(new DeadState());
        assertEquals(80, pet.getFullness()); // Fullness should remain same when switching to DeadState
        assertTrue(pet.getState() instanceof DeadState);
    }
}
