/**
 * This is the main class to be ran for the program to run correctly.
 * @author Mitchell Menzies
 * @version 1.0
 * @see MainSettings
 */
public class Main {

    /**
     * Main method. Initializes main settings and opens the main menu.
     */
    public static void main(String[] args) {
        MainSettings.initialize();
        MainSettings.mainMenu();
    }
}