/**
 * This class stores the main settings and variables for the game. Contains methods that are called by the
 * gui components in order to run various essential functions.
 * @author Mitchell Menzies
 * @version 1.0
 * @see Pet
 * @see Inventory
 * @see Game
 * @see GUI
 * @see ParentalControls
 */

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;

public class MainSettings {

    private static Timer mainTimer; //Main timer
    private static Pet[] pets; //Stores the 3 pets for the program
    private static Game game; //Stores the current game
    private static Instant start, end;
    private static Duration duration;
    private static ParentalControls pControls = new ParentalControls(); //Stores parental controls
    private static GUI gui; //Stores the gui object used
    public static boolean runTick; //A boolean that determines whether constant tick functions are to be run
    private static String[] petNames; //Stores the names of the 3 pets
    private static String currentFile; //Stores the current file being used

    //Booleans for whether certain commands can be performed
    public static boolean canSleep = true;
    public static boolean canPlay = true;
    public static boolean canWalk = true;
    public static boolean canInventory = true;
    public static boolean canVet = true;
    public static boolean canFeed = true;

    //Integers recording cooldowns for certain commands
    public static int vetCooldown;
    public static int playCooldown;

    /**
     * Initializes the main settings, used at the start of the program running.
     */
    public static void initialize() {
        //start = Instant.now();  // time control
        game = new Game();
        pets = new Pet[3]; // got to update array of user pets
        petNames = new String[3];
        //Loads file1 to save its pet information

        game.loadGame("File1");
        pets[0] = game.getPet();
        if (pets[0] == null)
            petNames[0] = null;
        else
            petNames[0] = pets[0].getName();

        //Loads file2 to save its pet information
        game.loadGame("File2");
        pets[1] = game.getPet();
        if (pets[1] == null)
            petNames[1] = null;
        else
            petNames[1] = pets[1].getName();

        //Loads file3 to save its pet information
        game.loadGame("File3");
        pets[2] = game.getPet();
        if (pets[2] == null)
            petNames[2] = null;
        else
            petNames[2] = pets[2].getName();
        //pControls = new ParentalControls(null, pets);
        gui = new GUI();
        runTick = false;

        //Initializes cooldowns to be arbitrarily high, so that they are immediately available.
        playCooldown = 100;
        vetCooldown = 100;
    }

    /**
     * Ends the program.
     */
    public static void end() {
//        end = Instant.now();
//        duration = Duration.between(start, end);
//        pControls.addPlaytime(duration, Integer.parseInt(Character.toString(currentFile.charAt(4))));  // time control
        //System.out.println(pControls.getPlaytimeTotal());
        //System.out.println(duration.toString());
        System.exit(0);
    }

    /**
     * Opens the main menu.
     */
    public static void mainMenu() {
        runTick = false;
        gui.mainMenu();
    }

    /**
     * Opens parental controls.
     */
    public static void parentalControls() {
        runTick = false;
        gui.enterParentalControls(pets);
    }

    /**
     * Resets a given save file, for parental controls.
     * @param file An integer representing the number of this save file
     */
    public static void resetSave(int file) {
        //Sets all stats back to full
        pets[file-1].setHealth(100);
        pets[file-1].setFullness(100);
        pets[file-1].setHappiness(100);
        pets[file-1].setSleep(100);
        pets[file-1].setState(new NormalState());
        //Sets game to this current pet
        game.loadGame("File"+file); //Loads this save to get the correct score and inventory
        game.setPet(pets[file-1]);
        //Saves the file
        game.saveGame("File"+file);
        //Resets parental controls screen to show change in stats.
        gui.parentalControls(pets);
    }

    /**
     * Exits the main gameplay.
     */
    public static void exitGameplay() {
        runTick = false;
        game.saveGame(currentFile); //Autosaves
        //Updates the pet objects in the pets array
        pets = new Pet[3];
        petNames = new String[3];
        game.loadGame("File1");
        pets[0] = game.getPet();
        if (pets[0] == null)
            petNames[0] = null;
        else
            petNames[0] = pets[0].getName();
        game.loadGame("File2");
        pets[1] = game.getPet();
        if (pets[1] == null)
            petNames[1] = null;
        else
            petNames[1] = pets[1].getName();
        game.loadGame("File3");
        pets[2] = game.getPet();
        if (pets[2] == null)
            petNames[2] = null;
        else
            petNames[2] = pets[2].getName();

        //Returns to main menu
        gui.mainMenu();
    }

    /**
     * Visits the vet
     */
    public static void visitVet() {
        runTick = false;
        game.incrementScore(50); //Increase score
        MainSettings.vetCooldown = 0; //Set cooldown to 0
        game.getPet().visitVet();
        gui.toVet(game.getPet());

        //For visiting the vet, the user gets 2 new food items and 1 new gift.
        game.getInventory().getGift(game.getPet().getType());
        game.getInventory().getFood();
        game.getInventory().getFood();

        //Return to main gameplay.
        runTick = true;
        Thread thread2 = new Thread(() -> {game.runGameplay(gui);});
        thread2.start();
    }

    /**
     * Play command.
     */
    public static void play() {
        runTick = false;
        MainSettings.playCooldown = 0;
        gui.toPlay(game.getPet(),game.getScore());
        game.getPet().play();
        game.incrementScore(25);

        //Return to main gameplay.
        runTick = true;
        Thread thread2 = new Thread(() -> {game.runGameplay(gui);});
        thread2.start();
    }

    /**
     * Exercise command.
     */
    public static void toExercise() {
        runTick = false;
        game.incrementScore(40);
        game.getPet().exercise();
        gui.toExercise(game.getPet());

        //For exercising, user receives 5 new food items.
        game.getInventory().getFood();
        game.getInventory().getFood();
        game.getInventory().getFood();
        game.getInventory().getFood();
        game.getInventory().getFood();

        //Return to main gameplay
        runTick = true;
        Thread thread2 = new Thread(() -> {game.runGameplay(gui);});
        thread2.start();
    }

    /**
     * Goes to the inventory screen
     * @param pet The pet object.
     * @param score The player's score.
     * @param inventory The player's inventory.
     */
    public static void toInventory(Pet pet, int score, Inventory inventory) {
        runTick = false;
        gui.toInventory(inventory);
    }

    /**
     * Returns to main gameplay
     */
    public static void returnToGameplay() {
        runTick = true;
        Thread thread2 = new Thread(() -> {game.runGameplay(gui);});
        thread2.start();
    }

    /**
     * Goes to the choose new game screen.
     */
    public static void chooseNewGame() {
        gui.chooseNewGame(pets[0] != null,pets[1] != null,pets[2] !=null,petNames[0],petNames[1],petNames[2]);
    }

    /**
     * Goes to the continue game screen.
     */
    public static void chooseContinueGame() {
        gui.continueGame(pets[0] != null,pets[1] != null,pets[2] !=null,petNames[0],petNames[1],petNames[2]);
    }

    /**
     * Starts a new game given a pet type, name, and a file
     * @param type The type of the new game's pet
     * @param name The name of the new game's pet
     * @param file The name of the save file for this new game
     */
    public static void newGame(char type, String name, String file) {
        game.newGame(new Pet(name, type, new PetObserver[0]));
        currentFile = file;
        game.saveGame(file);

        //Starts main gameplay
        runTick = true;
        Thread thread2 = new Thread(() -> {game.runGameplay(gui);});
        thread2.start();
        //System.out.println(name);
    }

    public static void loadGame(String file) {
        game.newGame(new Pet(null, 'n', null));
        currentFile = file;
        game.loadGame(file);
        runTick = true;
        Thread thread2 = new Thread(() -> {game.runGameplay(gui);});
        thread2.start();
        //System.out.println(name);
    }

    /**
     * Opens the tutorial.
     */
    public static void loadTutorial(){
        gui.tutorial();
    }

    /**
     * Uses a given item
     * @param item The item being used
     */
    public static void useItem(Item item) {
        if (item.getClass() == Food.class) { //If the item is food, use the feed commands
            gui.feed(game.getPet(),game.getScore(),(Food)item);
            game.incrementScore(25);
            game.getPet().increaseFullness(((Food) item).getNutritionValue());
        }
        else if (item.getClass() == Gift.class) { //If the item is gift, use the giveGift commands
            gui.giveGift(game.getPet(),game.getScore(), (Gift)item);
            game.incrementScore(50);
            game.getPet().increaseHappiness(((Gift) item).getHappinessBoost());
        }
        game.getInventory().removeItem(item); //Remove the used item from the inventory
    }

    /**
     * Inflicts a given point penalty
     * @param amount The amount of points removed
     */
    public static void pointPenalty(int amount) {
        game.incrementScore(-amount);
    }

    public static void enableParentalControls(){

    }
}
