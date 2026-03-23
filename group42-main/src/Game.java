/**
 * This class implements the main game. Stores the pet, inventory and score.
 * @author Mitchell Menzies
 * @version 1.0
 * @see Pet
 * @see Inventory
 */

import javax.swing.*;
import java.io.*;
import java.time.Duration;
import java.util.*;

public class Game {
    /**
     * The pet associated with this game;
     */
    private Pet pet;
    /**
     * The inventory associated with this game;
     */
    private Inventory inventory;
    /**
     * The score for this game;
     */
    private int score;

    private ParentalControls pControls = new ParentalControls();

    /**
     * Constructor, creates a null game with no set variables.
     */
    public Game(){
    }

    /**
     * Saves the current game's information to file.
     * @param file The name of the save file being used.
     */
    public void saveGame(String file) {
        ArrayList<Item> inventoryList; //Saves inventory as an array list
        File fileLocation; //Stores the file location
        BufferedWriter out; //Used to write to file
        Item item; //Stores item objects

        try { //Write to the inventory file
            fileLocation = new File("src/"+file+"/inventory.csv");
            FileWriter invFile = new FileWriter(fileLocation);
            out = new BufferedWriter(invFile);
            inventoryList = inventory.getItems();

            while (inventoryList.isEmpty() == false) { //Removes items from inventory list while adding them to file until list is empty
                out.newLine();
                item = inventoryList.get(0);
                out.write(item.getName() + ",");
                if (item.getClass() == Food.class) { //If item is food
                    out.write("f,");
                    out.write(((Food) item).getNutritionValue() + ",");
                    out.write(item.getSprite() + ",");
                }
                else if (item.getClass() == Gift.class) { //If item is gift
                    out.write("g,");
                    out.write(((Gift) item).getHappinessBoost() + ",");
                    out.write(item.getSprite() + ",");
                }
                else { //This should not run under normal circumstances
                    System.out.println("Invalid item type found in inventory.");
                }

                inventoryList.remove(0);
            }
            out.close();
        } catch (Exception e) {
            System.out.println("Something went wrong with writing to inventory file. "+e); //Debugging message
        }

        try { //Write to the pet file
            fileLocation = new File("src/"+file+"/pet.csv");
            FileWriter petFile = new FileWriter(fileLocation);
            out = new BufferedWriter(petFile);

            out.write(pet.getName()+",");
            out.write(pet.getType()+",");
            out.write(pet.getHealth()+",");
            out.write(pet.getSleep()+",");
            out.write(pet.getFullness()+",");
            out.write(pet.getHappiness()+",");
            out.write(pet.getStage()+",");
            out.write(pet.getStateChar()+",");
            out.close();
        } catch (Exception e) {
            System.out.println("Something went wrong with writing to pet file. "+e); //Debugging message
        }

        try { //Write to the player file
            fileLocation = new File("src/"+file+"/player.csv");
            FileWriter plaFile = new FileWriter(fileLocation);
            out = new BufferedWriter(plaFile);

//            out.write(score+",");
//            System.out.println(score+",");
            String[] playerStr = {Integer.toString(score), Long.toString(pControls.getGameTimes()[0][0]), Long.toString(pControls.getGameTimes()[0][1]), Long.toString(pControls.getGameTimes()[1][0]), Long.toString(pControls.getGameTimes()[1][1]), Long.toString(pControls.getGameTimes()[2][0]), Long.toString(pControls.getGameTimes()[2][1]), pControls.getPlaytimeTotal(false)};
            String outStr = String.join(",", playerStr);
            //System.out.println("outStr: " + outStr);
            out.write(outStr);
            out.close();
        } catch (Exception e) {
            System.out.println("Something went wrong with writing to player file. "+e); //Debugging message
        }
    }

    /**
     * Loads a game from a given already saved file.
     * @param file The name of the file that this save is stored in.
     */
    public void loadGame(String file) {
        String fileLine; //Saves the current line of the file
        BufferedReader in; //File reader
        StringTokenizer tokenizer; //Splits lines at the commas
        String itemName; //Saves item name
        this.inventory = new Inventory(); //Creates a new inventory to fill

        try { //Reads from the inventory file
            FileReader invReader = new FileReader("src/"+file + "/inventory.csv");
            in = new BufferedReader(invReader);
            in.readLine();
            fileLine = in.readLine();

            while (fileLine != null) { //Cycle through each line of inventory file
                tokenizer = new StringTokenizer(fileLine,",");
                itemName = tokenizer.nextToken(); //Save item name before reading for type

                if (tokenizer.nextToken().charAt(0) == 'f') { //If item is a food
                    inventory.addItem(new Food(itemName, Integer.parseInt(tokenizer.nextToken()), tokenizer.nextToken()));
                } else { //Otherwise assume gift (Nothing else should be possible)
                    inventory.addItem(new Gift(itemName, Integer.parseInt(tokenizer.nextToken()), tokenizer.nextToken()));
                }

                fileLine = in.readLine();
                System.out.println(fileLine);
            }
        } catch (Exception e) {
            System.out.println(e); //Debugging message. Appears in console but not visible in normal gameplay.
            inventory = null; //If unable to read, set inventory to null
        }

        try { //Reads from the pet file
            FileReader petReader = new FileReader("src/"+file + "/pet.csv");
            in = new BufferedReader(petReader);
            fileLine = in.readLine();
            System.out.println(fileLine);
            tokenizer = new StringTokenizer(fileLine,",");

            //Creates pet object and sets variables from the file's contents (in the order they are inputted in the save function)
            pet = new Pet(tokenizer.nextToken(), tokenizer.nextToken().charAt(0), new PetObserver[0]);
            pet.setHealth(Integer.parseInt(tokenizer.nextToken()));
            pet.setSleep(Integer.parseInt(tokenizer.nextToken()));
            pet.setFullness(Integer.parseInt(tokenizer.nextToken()));
            pet.setHappiness(Integer.parseInt(tokenizer.nextToken()));
            pet.setStage(Integer.parseInt(tokenizer.nextToken()));
            pet.setStateChar(tokenizer.nextToken().charAt(0));

        } catch (Exception e) {
            System.out.println(e); //Debugging message. Appears in console but not visible in normal gameplay.
            pet = null; //Sets pet to null
        }

        try { //Reads from the player file
            FileReader plaReader = new FileReader("src/"+file + "/player.csv");
            in = new BufferedReader(plaReader);
//            fileLine = in.readLine();
//            tokenizer = new StringTokenizer(fileLine,",");
//            score = Integer.parseInt(tokenizer.nextToken());
            String[] line = in.readLine().split(",");
            score = Integer.parseInt(line[0]);
            pControls.setGameTimes(1, Long.parseLong(line[1]), Long.parseLong(line[2]));
            pControls.setGameTimes(2, Long.parseLong(line[3]), Long.parseLong(line[4]));
            pControls.setGameTimes(3, Long.parseLong(line[5]), Long.parseLong(line[6]));
            pControls.setPlaytimeTotal(Duration.parse(line[7]));
            //for (int i = 0; i < 8; i++) { System.out.print(line[i] + ", "); }

        } catch (Exception e) {
            System.out.println(e); //Debugging message. Appears in console but not visible in normal gameplay.
            score = 0; //Set score to 0 if file not read.
        }
    }

    /**
     * Returns the pet stored in this game.
     * @return A pet object representing the pet.
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Sets the pet for the game
     * @param pet The new pet object
     */
    public void setPet(Pet pet) { this.pet = pet;}

    /**
     * Returns this game's score
     * @return An integer representing the score
     */
    public int getScore() { return score; }

    /**
     * Returns this game's inventory object
     * @return An inventory object representing this game's inventory
     */
    public Inventory getInventory() { return inventory; }

    /**
     * Increments the score for this game by a set amount.
     * @param amount An integer representing the amount incremented.
     */
    public void incrementScore(int amount) { score += amount;}

    /**
     * Creates a new game with defaults, given a newly created pet object.
     * @param pet The pet object associated with this new game.
     */
    public void newGame(Pet pet) {
        this.pet = pet;
        this.inventory = new Inventory();
        inventory.defaultInventory(pet.getType()); //Fill inventory with starting items
        this.score = 0;
    }

    /**
     * Runs main gameplay on a constant cycle. Ends as soon as MainSetting's runTick variable is set to false.
     * @param gui The gui where the gameplay is displayed.
     */
    public void runGameplay(GUI gui) {
            while (MainSettings.runTick) { //Loop as long as tick is meant to run
                this.tick(gui);
            }
    }

    /**
     * A tick function that runs every second during normal gameplay.
     * @param gui The gui where the gameplay is displayed.
     */
    private void tick (GUI gui) {
        gui.mainGameplay(this.pet, this.score, this.inventory); //Display the current screen
        try {
            Thread.sleep(1000); //Waits 1 second before updating

            this.pet.updateStats(); //Updates the pet's stats
            if (MainSettings.runTick) //If runTick is still active, update the screen
                gui.mainGameplay(this.pet, this.score, this.inventory);

            // Increment vet and play cooldowns
            MainSettings.vetCooldown++;
            MainSettings.playCooldown++;

            if (MainSettings.canVet) { //If the vet option is not disabled by the pet's current state
                if (MainSettings.vetCooldown < 50) //If the vet cooldown is less than 50, disable vet option
                    MainSettings.canVet = false;
            }
            if (MainSettings.canPlay) { //Repeat for play cooldown
                if (MainSettings.playCooldown < 20)
                    MainSettings.canPlay = false;
            }
        } catch(Exception e) {} //Catches any errors caused by Thread.sleep()

        if (score > 500 && pet.getStage() == 0 && MainSettings.runTick) { //Triggers the evolving of the pet if the right circumstances are met
            MainSettings.runTick = false;
            String oldSprite = pet.getSprite(); //Saves current sprite for levelup animation
            pet.levelup(); //Levelup the pet
            gui.levelup(pet, score, oldSprite); //Display levelup animation
        }
    }
}
