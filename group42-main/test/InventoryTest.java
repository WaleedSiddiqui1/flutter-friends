import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory inventory;
    private Food hardFood;
    private Gift insect;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        hardFood = new Food("Hard Food", 25, "food sprite");
        insect = new Gift("Insect", 10, "gift sprite");
    }

    @Test
    void testInitialInventoryIsEmpty() {
        assertDoesNotThrow(() -> inventory.getFood());
        assertDoesNotThrow(() -> inventory.getGift('c'));
    }

    @Test
    void testAddFoodIncreasesCount() {
        inventory.addItem(hardFood);
        assertDoesNotThrow(() -> inventory.getFood());
    }

    @Test
    void testAddGiftIncreasesCount() {
        inventory.addItem(insect);
        assertDoesNotThrow(() -> inventory.getGift('c'));
    }

    @Test
    void testMultipleItemsInInventory() {
        Food secondFood = new Food("Soft Food", 10, "soft food");
        Gift secondGift = new Gift("Ball", 5, "ball");

        inventory.addItem(hardFood);
        inventory.addItem(secondFood);
        inventory.addItem(insect);
        inventory.addItem(secondGift);

        assertDoesNotThrow(() -> inventory.getFood());
        assertDoesNotThrow(() -> inventory.getGift('c'));
    }
}
