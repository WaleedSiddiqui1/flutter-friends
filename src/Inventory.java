/**
 * This class stores and manages various items like food and gifts that the player earned and uses
 *
 * @author Waleed Siddiqui
 * @author Mitchell Menzies
 * @see Item
 * @see Food
 * @see Gift
 * @see Player
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Inventory {
    private List<Item> items; //The list of items used to implement the inventory

    /**
     * Initializes a new inventory.
     */
    public Inventory() {
        this.items = new ArrayList<>();
    }

    /**
     * Creates a default inventory based on the pet's type. Used at the start of a new game.
     * @param type A character representing the pet's type.
     */
    public void defaultInventory(char type) {
        //Adds all types of food
        this.addItem(new Food("Hard Food", 20, "Sprites/Food/hard_food.png"));
        this.addItem(new Food("Soft Food", 10, "Sprites/Food/soft_food.png"));
        this.addItem(new Food("Squeeze Up", 30, "Sprites/Food/squeeze_up.png"));
        if (type == 'b') { //If the type is bat, rat item gives biggest happiness boost
            this.addItem(new Gift("Rat", 80, "Sprites/Food/rat.png"));
            this.addItem(new Gift("Insect", 10, "Sprites/Food/insect.png"));
            this.addItem(new Gift("Fish", 10, "Sprites/Food/fish.png"));
        }
        if (type == 'c') { //If the type is chicken, insect item gives biggest happiness boost
            this.addItem(new Gift("Insect", 80, "Sprites/Food/insect.png"));
            this.addItem(new Gift("Rat", 10, "Sprites/Food/rat.png"));
            this.addItem(new Gift("Fish", 10, "Sprites/Food/fish.png"));
        }
        if (type == 'p') { //If the type is penguin, fish item gives the biggest happiness boost
            this.addItem(new Gift("Fish", 80, "Sprites/Food/fish.png"));
            this.addItem(new Gift("Rat", 10, "Sprites/Food/rat.png"));
            this.addItem(new Gift("Insect", 10, "Sprites/Food/insect.png"));
        }
    }

    /**
     * Adds a random gift item to this inventory object.
     * @param type The type of the pet (used to assign gift's happiness value)
     */
    public void getGift(char type) {
        int randomNumber;
        Random rand = new Random();
        randomNumber = rand.nextInt(3);

        if (randomNumber == 0) {
            if (type != 'b') //If the type is not bat, rat item only gives 10 happiness
                this.addItem(new Gift("Rat", 10, "Sprites/Food/rat.png"));
            else
                this.addItem(new Gift("Rat", 80, "Sprites/Food/rat.png"));
        }
        else if (randomNumber == 1) {
            if (type != 'c') //If the type is not chicken, insect only gives 10 happiness
                this.addItem(new Gift("Insect", 10, "Sprites/Food/insect.png"));
            else
                this.addItem(new Gift("Insect", 80, "Sprites/Food/insect.png"));
        }
        else if (randomNumber == 2) {
            if (type != 'p') //If the type is not penguin, fish only gives 10 happiness
                this.addItem(new Gift("Fish", 10, "Sprites/Food/fish.png"));
            else
                this.addItem(new Gift("Fish", 80, "Sprites/Food/fish.png"));
        }
    }

    /**
     * Adds a random food item to this inventory object.
     */
    public void getFood() {
        int randomNumber;
        Random rand = new Random();
        randomNumber = rand.nextInt(3);

        if (randomNumber == 0)
            this.addItem(new Food("Hard Food", 20, "Sprites/Food/hard_food.png"));
        else if (randomNumber == 1)
            this.addItem(new Food("Soft Food", 10, "Sprites/Food/soft_food.png"));
        else if (randomNumber == 2)
            this.addItem(new Food("Squeeze Up", 30, "Sprites/Food/squeeze_up.png"));
    }

    /**
     * Adds an item to the inventory when the player earns it
     *
     * @param item the item to add
     */
    public void addItem(Item item) {
        if (item != null) {
            items.add(item);
            System.out.println(item.getName() + " earned and added to inventory.");
        }
    }

    /**
     * Removes an item from the inventory when it is used
     *
     * @param item the item to remove
     * @return true if the item was removed successfully, false if item not found
     */
    public boolean removeItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);
            System.out.println(item.getName() + " used and removed from inventory.");
            return true;
        }
        System.out.println("Item not found in inventory.");
        return false;
    }

    /**
     * Returns a copy of the list of all items in the inventory
     *
     * @return a List of Item objects
     */
    public ArrayList<Item> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Finds an item in the inventory by name
     *
     * @param name the name of the item to find
     * @return the item object if found, otherwise null
     */
    public Item findItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Checks if an item exists in the inventory
     *
     * @param item the item to check
     * @return true if it exists, false otherwise
     */
    public boolean hasItem(Item item) {
        return items.contains(item);
    }

    /**
     * Prints the current contents of the inventory.
     */
    public void showInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory contains:");
            for (Item item : items) {
                System.out.println("- " + item.getName());
            }
        }
    }
}
