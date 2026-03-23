/**
 * This class represents gift items that increase the pet's happiness levels
 * Implements the Item interface
 * @author Waleed Siddiqui
 *
 * @see Item
 * @see Pet
 * @see Inventory
 */
public class Gift implements Item {
    private String name;
    private int happinessBoost;
    private String sprite;

    /**
     * Creates a new Gift item
     *
     * @param name the name of the gift
     * @param happinessBoost how much happiness it adds
     */
    public Gift(String name, int happinessBoost, String sprite) {
        this.name = name;
        this.happinessBoost = happinessBoost;
        this.sprite = sprite;
    }

    /**
     * Gets the name of the gift
     *
     * @return the name as a String
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Applies the gift's effect to the pet, increasing happiness
     *
     * @param pet the pet receiving the gift
     */
    @Override
    public void applyEffect(Pet pet) {
        pet.increaseHappiness(happinessBoost);
        System.out.println(name + " gifted to pet. Happiness increased by " + happinessBoost);
    }

    /**
     * Returns how much happiness this gift gives
     *
     * @return happiness boost
     */
    public int getHappinessBoost() {
        return happinessBoost;
    }

    /**
     * Returns the sprite name for this gift item.
     * @return Sprite name
     */
    public String getSprite() {
        return sprite;
    }
}
