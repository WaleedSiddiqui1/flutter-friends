/**
 * This class represents the player and the actions they can take.
 * @author Theodore Igbeyi
 * @see Inventory
 * @see Pet
 * @see Item
 */

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.*;

public class Player {
    /** The player's name */
    private String playerName;
    /** The player's score */
    private int score;
    /** The player's inventory */
    private Inventory inventory;
    /** The player's pet */
    private Pet pet;
    /** The parental controls class used to access the parental controls information */
    private ParentalControls pControls = new ParentalControls();

    /**
     * The constructor for the Player class. Instantiates the class attributes.
     *
     * @param name the player's name
     * @param sc the player's score
     * @param inv the player's inventory
     * @param p the player's pet
     */
    public Player(String name, int sc, Inventory inv, Pet p) {
        playerName = name;
        score = sc;
        inventory = inv;
        pet = p;
    }

    /**
     * Play with the pet
     */
    public void play() { pet.play(); }

    /**
     * Feed the pet
     *
     * @param food the food that the pet will eat
     */
    public void feedPet(Food food) { pet.eat(food); }

    /**
     * Give the pet a gift
     *
     * @param gift the gift to be given
     */
    public void giveGift(Gift gift) { pet.receiveGift(gift); }

    /**
     * Take the pet to the vet
     */
    public void takeToVet() { pet.visitVet(); }

    /**
     * Save the game state
     *
     * @param fileNum the number of the save file the game will be saved in
     */
    public void saveGame(int fileNum) { /*create and save a csv file with inventory, pet, score, and playername info*/
        try {
            // Writing to inventory file
            File invFile = new File("File" + fileNum + "/inventory.csv");  // gotta include proper file path
            FileWriter writer = new FileWriter(invFile);

            ArrayList<Item> invList = (ArrayList<Item>) inventory.getItems();
            String[] invStr;
            String itemName = "";
            String itemType = "";
            String boostValue = "";
            String spriteName = "";

            for (int i = 0; i < invList.size(); i++) {
                itemName = invList.get(i).getName();

                if (invList.get(i) instanceof Food) {
                    itemType = "f";
                    boostValue = Integer.toString(((Food) invList.get(i)).getNutritionValue());
                }
                else if (invList.get(i) instanceof Gift) {
                    itemType = "g";
                    boostValue = Integer.toString(((Gift) invList.get(i)).getHappinessBoost());
                }

                spriteName = invList.get(i).getSprite();

                invStr = new String[]{itemName, itemType, boostValue, spriteName};
                writer.write(String.join(",", invStr));
            }

            // Writing to pet file
            File petFile = new File("File" + fileNum + "pet.csv");
            writer = new FileWriter(petFile);

            String[] petStr = {pet.getName(), Character.toString(pet.getType()), Integer.toString(pet.getHealth()), Integer.toString(pet.getSleep()), Integer.toString(pet.getFullness()), Integer.toString(pet.getHappiness()), Integer.toString(pet.getStage()), Character.toString(pet.getStateChar())};
            writer.write(String.join(",", petStr));

            // Writing to player file
            File playerFile = new File("File" + fileNum + "player.csv");
            writer = new FileWriter(playerFile);

            String[] playerStr = {playerName, Integer.toString(score), Long.toString(pControls.getGameTimes()[0][0]), Long.toString(pControls.getGameTimes()[0][1]), Long.toString(pControls.getGameTimes()[1][0]), Long.toString(pControls.getGameTimes()[1][1]), Long.toString(pControls.getGameTimes()[2][0]), Long.toString(pControls.getGameTimes()[2][1]), pControls.getPlaytimeTotal(false)};
            writer.write(String.join(",", playerStr));

            writer.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Load an existing game state
     *
     * @param fileNum the number of the save file that will be loaded
     */
    public void loadGame(int fileNum) { /* get the saved csv file with inventory, pet, score, and playername info and set those attributes to the saved info */
        Scanner in;
        String[] line = null;

        try {
            // Getting inventory information
            File invFile = new File("File" + fileNum + "inventory.csv");
            in = new Scanner(invFile);
            Inventory newInv = new Inventory();
            Item item = null;

            while (in.hasNextLine()) {
                line = in.nextLine().split(",");

                if (line[1].equals("f")) {
                    item = new Food(line[0], Integer.parseInt(line[2]), line[3]);
                }
                else if (line[1].equals("g")) {
                    item = new Gift(line[0], Integer.parseInt(line[3]), line[3]);
                }

                newInv.addItem(item);
            }

            // Getting pet information
            File petFile = new File("File" + fileNum + "pet.csv");
            in = new Scanner(petFile);

            while (in.hasNextLine()) {
                line = in.nextLine().split(",");
            }
            pet = new Pet(line[0], line[1].charAt(0), null); // what are the observers?
            pet.setHealth(Integer.parseInt(line[2]));
            pet.setSleep(Integer.parseInt(line[3]));
            pet.setFullness(Integer.parseInt(line[4]));
            pet.setHappiness(Integer.parseInt(line[5]));
            pet.setStage(Integer.parseInt(line[6]));
            pet.setState(null); // mitchell gotta figure out the getState function (and maybe setState)

            // Getting player information
            File playerFile = new File("File" + fileNum + "player.csv");
            in = new Scanner(playerFile);

            while (in.hasNextLine()) {
                line = in.nextLine().split(",");
            }
            playerName = line[0];
            score = Integer.parseInt(line[1]);
            pControls.setGameTimes(1, Long.parseLong(line[2]), Long.parseLong(line[3]));
            pControls.setGameTimes(2, Long.parseLong(line[4]), Long.parseLong(line[5]));
            pControls.setGameTimes(3, Long.parseLong(line[6]), Long.parseLong(line[7]));
            pControls.setPlaytimeTotal(Duration.parse(line[8]));

            in.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    // Getters

    /**
     * Gets the player's name
     * @return the player's name
     */
    public String getPlayerName() { return playerName; }

    /**
     * Gets the score
     * @return the score
     */
    public int getScore() { return score; }

    /**
     * Gets the current inventory
     * @return the current inventory
     */
    public Inventory getInventory() { return inventory; }

    /**
     * Gets the current pet
     * @return the current pet
     */
    public Pet getPet() { return pet; }

    // Setters

    /**
     * Sets the player name
     * @param name the new player name
     */
    public void setPlayerName(String name) { this.playerName = name; }

    /**
     * Set the score
     * @param sc the new score
     */
    public void setScore(int sc) { this.score = sc; }

    /**
     * Sets the inventory
     * @param inv the new inventory
     */
    public void setInventory(Inventory inv) { this.inventory = inv; }

    /**
     * Sets the pet
     * @param p the new pet
     */
    public void setPet(Pet p) { this.pet = p; }

}
