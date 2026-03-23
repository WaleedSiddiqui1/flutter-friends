/**
 * This class represents a consumable item that increases the pet's fullness levels when used
 * Implements the Item interface
 * @author Waleed Siddiqui
 * @see Item
 * @see Pet
 * @see Inventory
 */
public class Food implements Item {
    private String name;
    private int nutritionValue;
    private String sprite;

    /**
     * Creates a new Food item.
     *
     * @param name the name of the food item
     * @param nutritionValue how much fullness this food provides
     */
    public Food(String name, int nutritionValue, String sprite) {
        this.name = name;
        this.nutritionValue = nutritionValue;
        this.sprite = sprite;
    }

    /**
     * Gets the name of this food item.
     *
     * @return the name as a String
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Applies this food's effect to a pet, increasing fullness.
     *
     * @param pet the pet receiving the food
     */
    @Override
    public void applyEffect(Pet pet) {
        pet.increaseFullness(nutritionValue);
        System.out.println(name + " fed to pet. Fullness increased by " + nutritionValue);
    }

    /**
     * Returns how much fullness this food gives.
     *
     * @return nutrition value
     */
    public int getNutritionValue() {
        return nutritionValue;
    }

    /**
     * Returns the sprite name for this food item.
     * @return Sprite name
     */
    public String getSprite() {
        return sprite;
    }
}
