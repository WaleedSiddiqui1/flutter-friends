/**
 * Represents the GUI for this program.
 * @author Mitchell Menzies
 * @author Waleed Siddiqui
 * @author Theodore Igbeyi
 * @author Alicia Serdyuk
 * @version 1.0
 * @see Pet
 * @see Inventory
 * @see MainSettings
 */

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class GUI {
    private JFrame frame; //The frame for the program
    private JPanel panel; //The panel used for this frame
    private ParentalControls pControls = new ParentalControls();
    private Instant start, end;
    Duration duration;
    int currFileNum;

    /**
     * Builds a new GUI screen, with nothing displayed.
     */
    public GUI() {
        frame = new JFrame();
        panel = new JPanel();
        frame.setTitle("Flutter Friends");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(768, 768);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(panel);
    }

    /**
     * Clears the current frame so that a new frame can be animated.
     */
    private void clearFrame() {
        panel.removeAll();
        panel.repaint();
        frame.repaint();
    }

    /**
     * Displays the main menu for the game. Buttons lead to appropriate functions in MainSettings class.
     */
    public void mainMenu() {
        clearFrame();
        JButton newGame = new JButton();
        JButton loadGame = new JButton();
        JButton tutorial = new JButton();
        JButton parentalControls = new JButton();
        JButton exitGame = new JButton();
        //JLabel title = new JLabel("Flutter Friends");
        JLabel members = new JLabel("Group 42: Alicia Serdyuk, Waleed Siddiqui, Bosen Zhang, Mitchell Menzies, Theodore Igbeyi");
        JLabel project = new JLabel("Made for CS2212 at Western University");

        // Drawn Image as title
        ImageIcon titleIcon = new ImageIcon(Test.class.getResource("/Images/title2.png"));
        titleIcon.setImage(titleIcon.getImage().getScaledInstance(500, 150, Image.SCALE_SMOOTH));
        JLabel titleLabel = new JLabel(titleIcon);
        titleLabel.setBounds(134, 30, 500, 150);
        panel.add(titleLabel);

        panel.setBackground(new Color(230, 210, 255));

        members.setBounds(174, 670, 550, 50);
        members.setFont(new Font("Arial", Font.BOLD, 10));

        project.setBounds(288, 695, 550, 50);
        project.setFont(new Font("Arial", Font.BOLD, 10));

        //title.setBounds(234, 65, 400, 50);
        //title.setFont(new Font("Arial", Font.PLAIN, 50));

        newGame.setBounds(234, 200, 300, 50);
        //newGame.addActionListener(e -> System.out.println("*New Game*"));
        newGame.addActionListener(e -> MainSettings.chooseNewGame());
        newGame.setText("New Game");

        loadGame.setBounds(234, 300, 300, 50);
        loadGame.addActionListener(e -> MainSettings.chooseContinueGame());
        loadGame.setText("Continue Game");

        tutorial.setBounds(234, 400, 300, 50);
        tutorial.addActionListener(e -> MainSettings.loadTutorial());
        tutorial.setText("Tutorial");

        parentalControls.setBounds(234, 500, 300, 50);
        parentalControls.addActionListener(e -> MainSettings.parentalControls());
        parentalControls.setText("Parental Controls");

        exitGame.setBounds(234, 600, 300, 50);
        exitGame.addActionListener(e -> MainSettings.end());
        exitGame.setText("Exit Game");

        //Adds all elements to the panel and repaints
        panel.add(newGame);
        panel.add(loadGame);
        panel.add(tutorial);
        panel.add(parentalControls);
        panel.add(exitGame);
        //panel.add(title);
        panel.add(members);
        panel.add(project);
        panel.repaint();
    }

    /**
     * Displays the tutorial screen.
     */
    public void tutorial() {
        /*clearFrame();
        JButton back = new JButton();
        JLabel title = new JLabel("Tutorial");
        ImageIcon icon = new ImageIcon(Test.class.getResource("/Images/flutter_friends_tutorial.png"));
        icon.setImage(icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        JLabel img = new JLabel(icon);


        panel.setBackground(Color.GREEN);

        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Arial", Font.PLAIN, 25));

        back.setBounds(10, 10, 100, 25);
        back.addActionListener(e -> MainSettings.mainMenu());
        back.setText("Back");

        img.setBounds(300, 300, 300, 300);

        panel.add(img);
        panel.add(back);
        panel.add(title);

         */
        // Above is an older version of the tutorial screen. Has since been remastered.

        clearFrame();
        panel.setLayout(null);

        panel.setBackground(new Color(230, 210, 255)); // baby purple background

        // Back Button
        JButton back = new JButton("Back");
        back.setBounds(10, 10, 100, 25);
        back.addActionListener(e -> MainSettings.mainMenu());
        panel.add(back);

        // Title
        JLabel title = new JLabel("Tutorial");
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        title.setBounds(300, 20, 400, 40);
        panel.add(title);

        // Tutorial Text
        String htmlText =
                "<html>" +
                        "<h2>HOW TO PLAY:</h2>" +
                        "<p>Each pet has 4 stats: Health, Fullness, Sleep, and Happiness. The aim of the game is to keep your pet's health above 0. " +
                        "Your pet's health will decrease if its sleep or fullness reaches 0. These stats automatically decrease over time, but you can increase them by performing activities and using items.</p><br>" +

                        "<p>You gain points for positive actions like feeding and caring for the pet. You lose points for negative actions like letting it starve or ignoring it. <b>Remember to save</b> your game by pressing the \"Back\" button on the top left of the screen, instead of exiting the tab normally.</p><br>" +

                        "<h3>HAPPINESS:</h3>" +
                        "<p>If happiness reaches 0, your pet becomes angry and will only accept commands that improve happiness. It returns to normal when happiness is at least half of the max.</p><br>" +

                        "<h3>EVOLUTION:</h3>" +
                        "<p>Pets evolve to their second stage once you gain enough points. Evolved pets have doubled max stats.</p><br>" +

                        "<h3>COMMANDS:</h3>" +
                        "<p>Use the icons on the left of the screen to:<br>" +
                        "- Go to Bed (increase sleep)<br>" +
                        "- Inventory (feed and give gifts)<br>" +
                        "- Vet (boost health)<br>" +
                        "- Play (increase happiness)<br>" +
                        "- Exercise (increase health, reduce sleep & fullness)</p><br>" +

                        "<h3>ITEMS:</h3>" +
                        "<p>Food Types: Hard Food, Wet Food, Squeeze-Ups<br>" +
                        "Gift Types: Rats (bats), Insects (chickens), Fish (penguins)<br>" +
                        "Exercising your pet or visiting the vet will earn you items.<br>" +
                        "Access all items via your inventory.</p><br>" +

                        "<h3>START NEW GAME:</h3>" +
                        "<p>Click 'New Game' on the main menu, choose your pet, name it, then press 'Start Game'.</p><br>" +

                        "<h3>CONTINUE GAME:</h3>" +
                        "<p>Click 'Continue Game' to load a previously saved game slot.</p><br>" +

                        "<h3>TUTORIAL:</h3>" +
                        "<p>Access this tutorial anytime from the main menu.</p><br>" +

                        "<h3>PARENTAL CONTROLS:</h3>" +
                        "<p>Parents can view playtime, set time limits, and restore pet stats using the 'Parental Controls' option.</p><br>" +

                        "<h3>EXIT GAME:</h3>" +
                        "<p>Click 'Exit Game' or close the game window to exit.</p>" +
                        "</html>";

        JLabel tutorialText = new JLabel(htmlText);
        tutorialText.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        tutorialText.setVerticalAlignment(JLabel.TOP);

        JScrollPane scroll = new JScrollPane(tutorialText);
        scroll.setBounds(50, 80, 650, 600);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scroll);
        //panel.add(tutorialText);
        panel.repaint();
        frame.repaint();

    }

    /**
     * Displays the passcode prompt for parental controls. Takes in a list of pets to be used by
     * parental controls should the user enter the correct passcode.
     * @param pets An array of pets, used to generate parental controls screen. Not used here, but passed along
     */
    public void enterParentalControls(Pet[] pets) {
        clearFrame();
        //panel.setBackground(Color.pink);
        panel.setBackground(new Color(230, 210, 255)); // baby purple background

        // Back button to return to the main menu
        JButton back = new JButton();
        back.setBounds(10, 10, 100, 25);
        back.addActionListener(e -> MainSettings.mainMenu());
        back.setText("Back");
        panel.add(back);

        // Title
        JLabel title = new JLabel("Parental Controls");
        title.setBounds(300, 40, 400, 50);
        //title.setFont(new Font("Arial", Font.PLAIN, 25));
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        panel.add(title);

        //Enter passcode text
        JLabel enterPasscode = new JLabel("Enter passcode:");
        enterPasscode.setBounds(10, 300, 200, 50);
        enterPasscode.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(enterPasscode);

        //Field for inputting the passcode
        JPasswordField passcode = new JPasswordField();
        passcode.setBounds(150, 300, 300, 50);
        passcode.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(passcode);

        JButton validate = new JButton();
        validate.setBounds(500, 300, 200, 50);
        validate.setText("Validate Passcode");
        validate.addActionListener(e -> testPasscode(pets, passcode));
        panel.add(validate);
        panel.repaint();
    }

    /**
     * Tests the currently entered passcode.
     * @param pets An array of pets, passed along to parentalControls method should the user get the passcode correct
     * @param passcode The JPasswordField storing the passcode
     */
    private void testPasscode(Pet[] pets, JPasswordField passcode) {
        if (passcode.getText() == null) { //If the passcode is null, reject it and send user back to menu
            this.mainMenu();
        }
        else if (passcode.getText().matches("8675309")) { //If the passcode is correct, go to parentalControls screen
            parentalControls(pets);
        }
        else { //If passcode is incorrect, reject it and send user back to menu
            this.mainMenu();
        }
    }

    /**
     * Generates the parentalControls screen, displaying stats for the various pets.
     * @param pets An array of the 3 pets saved in the program.
     */
    public void parentalControls(Pet[] pets) {
        clearFrame();
        //panel.setBackground(Color.pink);
        panel.setBackground(new Color(230, 210, 255)); // baby purple background

        // Back button to return to the main menu
        JButton back = new JButton();
        back.setBounds(10, 10, 100, 25);
        back.addActionListener(e -> MainSettings.mainMenu());
        back.setText("Back");
        panel.add(back);

        // Title
        JLabel title = new JLabel("Parental Controls");
        title.setBounds(300, 40, 400, 50);
        //title.setFont(new Font("Arial", Font.PLAIN, 25));
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        panel.add(title);

        // Playtime labels showing average and total playtime for each game file
        // need to connect to actual games with selected pets
        JLabel game1 = new JLabel(pControls.getGameTimeAverages(1));
        JLabel game2 = new JLabel(pControls.getGameTimeAverages(2));
        JLabel game3 = new JLabel(pControls.getGameTimeAverages(3));
        JLabel total = new JLabel(pControls.getPlaytimeTotal(true));

        game1.setBounds(50, 80, 300, 25);
        game2.setBounds(50, 110, 300, 25);
        game3.setBounds(50, 140, 300, 25);
        total.setBounds(50, 170, 300, 25);

        panel.add(game1);
        panel.add(game2);
        panel.add(game3);
        panel.add(total);


        //Initializes variables
        JLabel pet1 = new JLabel();
        JLabel stats1 = new JLabel();
        JLabel pet2 = new JLabel();
        JLabel stats2 = new JLabel();
        JLabel pet3 = new JLabel();
        JLabel stats3 = new JLabel();

        // Reset button for resetting a pet's stats
        JButton reset1 = new JButton("Reset Game 1");
        reset1.setBounds(500, 250, 200, 25);
        reset1.addActionListener(e -> {
            MainSettings.resetSave(1);
        });

        JButton reset2 = new JButton("Reset Game 2");
        reset2.setBounds(500, 320, 200, 25);
        reset2.addActionListener(e -> {
            MainSettings.resetSave(2);
        });

        JButton reset3 = new JButton("Reset Game 3");
        reset3.setBounds(500, 390, 200, 25);
        reset3.addActionListener(e -> {
            MainSettings.resetSave(3);;
        });

        if (pets[0] == null) { //If the pet does not exist, show empty slot
            pet1 = new JLabel("Game 1 Pet: EMPTY SLOT");
            pet1.setBounds(50, 250, 300, 25);
            stats1 = new JLabel("Health: XXXXX | Fullness: XXXXX | Sleep: XXXXX | Happiness: XXXXX");
            stats1.setBounds(50, 275, 600, 25);
            reset1.setEnabled(false);
        }
        else { //If pet exists, show its stats
            pet1 = new JLabel("Game 1 Pet: "+pets[0].getName());
            pet1.setBounds(50, 250, 300, 25);
            stats1 = new JLabel("Health: "+pets[0].getHealth()+" | Fullness: "+pets[0].getFullness()+" | Sleep: "+pets[0].getSleep()+" | Happiness: "+pets[0].getHappiness());
            stats1.setBounds(50, 275, 600, 25);
        }

        if (pets[1] == null) { //Repeat for save file 2
            pet2 = new JLabel("Game 2 Pet: EMPTY SLOT");
            pet2.setBounds(50, 320, 300, 25);
            stats2 = new JLabel("Health: XXXXX | Fullness: XXXXX | Sleep: XXXXX | Happiness: XXXXX");
            stats2.setBounds(50, 345, 600, 25);
            reset2.setEnabled(false);
        }
        else {
            pet2 = new JLabel("Game 2 Pet: "+pets[1].getName());
            pet2.setBounds(50, 320, 300, 25);
            stats2 = new JLabel("Health: "+pets[1].getHealth()+" | Fullness: "+pets[1].getFullness()+" | Sleep: "+pets[1].getSleep()+" | Happiness: "+pets[1].getHappiness());
            stats2.setBounds(50, 345, 600, 25);
        }

        if (pets[2] == null) { //Repeat for save file 3
            pet3 = new JLabel("Game 3 Pet: EMPTY SLOT");
            pet3.setBounds(50, 390, 300, 25);
            stats3 = new JLabel("Health: XXXXX | Fullness: XXXXX | Sleep: XXXXX | Happiness: XXXXX");
            stats3.setBounds(50, 415, 600, 25);
            reset3.setEnabled(false);
        }
        else {
            pet3 = new JLabel("Game 3 Pet: "+pets[2].getName());
            pet3.setBounds(50, 390, 300, 25);
            stats3 = new JLabel("Health: "+pets[2].getHealth()+" | Fullness: "+pets[2].getFullness()+" | Sleep: "+pets[2].getSleep()+" | Happiness: "+pets[2].getHappiness());
            stats3.setBounds(50, 415, 600, 25);
        }

        panel.add(pet1);
        panel.add(stats1);
        panel.add(pet2);
        panel.add(stats2);
        panel.add(pet3);
        panel.add(stats3);

        panel.add(reset1);
        panel.add(reset2);
        panel.add(reset3);
    }

    /**
     * Displays screen for choosing the pet and name for a new game.
     * @param filename The name of the file that this new game will be saved to
     */
    public void newGame(String filename) {
        currFileNum = Integer.parseInt(Character.toString(filename.charAt(4)));
        clearFrame();
        JButton back = new JButton();
        JButton penguin = new JButton();
        JButton chicken = new JButton();
        JButton bat = new JButton();
        JButton startGame = new JButton();
        JTextField petName = new JTextField("");
        JLabel title = new JLabel("New Game");
        JLabel choosePet = new JLabel("Choose Pet:");
        JLabel namePet = new JLabel("Name your pet:");
        AtomicReference<Character> petChosen = new AtomicReference<>('0');

        //panel.setBackground(Color.cyan);
        panel.setBackground(new Color(230, 210, 255)); // baby purple background


        title.setBounds(300, 50, 400, 50);
        //title.setFont(new Font("Arial", Font.PLAIN, 25));
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

        choosePet.setBounds(10, 150, 200, 50);
        choosePet.setFont(new Font("Arial", Font.PLAIN, 20));

        namePet.setBounds(10, 650, 200, 50);
        namePet.setFont(new Font("Arial", Font.PLAIN, 20));

        petName.setBounds(150, 650, 300, 50);
        petName.setFont(new Font("Arial", Font.PLAIN, 20));

        startGame.setBounds(500, 650, 200, 50);
        startGame.setText("Start Game");
        startGame.addActionListener(e -> startGame(petChosen, petName, filename));
        startGame.setEnabled(false);

        back.setBounds(10, 10, 100, 25);
        back.addActionListener(e -> MainSettings.mainMenu());
        back.setText("Back");

        penguin.setBounds(25, 200, 200, 400);
        penguin.addActionListener(e -> petChosen.set(selectPet(penguin, bat, chicken, startGame)));
        penguin.setText("Penguin");
        penguin.setName("Penguin");
        penguin.setBackground(Color.red);

        bat.setBounds(275, 200, 200, 400);
        bat.addActionListener(e -> petChosen.set(selectPet(bat, penguin, chicken, startGame)));
        bat.setText("Bat");
        bat.setName("Bat");
        bat.setBackground(Color.red);

        chicken.setBounds(525, 200, 200, 400);
        chicken.addActionListener(e -> petChosen.set(selectPet(chicken, bat, penguin, startGame)));
        chicken.setText("Chicken");
        chicken.setName("Chicken");
        chicken.setBackground(Color.red);

        panel.add(back);
        panel.add(title);
        panel.add(choosePet);
        panel.add(penguin);
        panel.add(bat);
        panel.add(chicken);
        panel.add(namePet);
        panel.add(petName);
        panel.add(startGame);
        panel.repaint();
    }

    /**
     * Used as part of the newGame method. Sets one pet type to selected and the other 2 to not selected.
     * @param selected The button for the pet that was selected.
     * @param notSelected1 The button for the first pet not selected.
     * @param notSelected2 The button for the second pet not selected.
     * @param startGame The start game button, which is set to be enabled after a pet is picked.
     * @return Returns the single char associated with the pet type chosen, either 'c', 'b', or 'p'.
     */
    private char selectPet(JButton selected, JButton notSelected1, JButton notSelected2, JButton startGame) {
        //Selected pet's background is green, others are red.
        selected.setBackground(Color.green);
        notSelected1.setBackground(Color.red);
        notSelected2.setBackground(Color.red);
        startGame.setEnabled(true);

        if (selected.getName().matches("Penguin")) //If the selected button was Penguin
            return 'p';
        else if (selected.getName().matches("Bat")) //If it was bat
            return 'b';
        else //Otherwise must be chicken, as no other pets currently exist.
            return 'c';
    }

    /**
     * GUI for continuing an existing save.
     * @param game1 Boolean storing whether file1 has an existing game.
     * @param game2 Boolean storing whether file2 has an existing game.
     * @param game3 Boolean storing whether file3 has an existing game.
     * @param pet1 String storing the name of file1's pet.
     * @param pet2 String storing the name of file2's pet.
     * @param pet3 String storing the name of file3's pet.
     */
    public void continueGame(boolean game1, boolean game2, boolean game3, String pet1, String pet2, String pet3) {
        clearFrame();
        JButton back = new JButton();
        JButton game1Button = new JButton();
        JButton game2Button = new JButton();
        JButton game3Button = new JButton();
        JLabel title = new JLabel("Continue Game");

        //panel.setBackground(Color.cyan);
        panel.setBackground(new Color(230, 210, 255)); // baby purple background


        game1Button.setBounds(134, 200, 500, 100);
        game1Button.addActionListener(e -> currFileNum = 1);
        game1Button.addActionListener(e -> MainSettings.loadGame("File1"));
        if (game1) {
            game1Button.setText("Game 1: "+pet1);
        }
        else {
            game1Button.setText("Game 1: --");
            game1Button.setEnabled(false);
        }

        game2Button.setBounds(134, 400, 500, 100);
        game2Button.addActionListener(e -> currFileNum = 2);
        game2Button.addActionListener(e -> MainSettings.loadGame("File2"));
        if (game2) {
            game2Button.setText("Game 2: "+pet2);
        }
        else {
            game2Button.setText("Game 2: --");
            game2Button.setEnabled(false);
        }

        game3Button.setBounds(134, 600, 500, 100);
        game3Button.addActionListener(e -> currFileNum = 3);
        game3Button.addActionListener(e -> MainSettings.loadGame("File3"));
        if (game3) {
            game3Button.setText("Game 3: "+pet3);
        }
        else {
            game3Button.setText("Game 3: --");
            game3Button.setEnabled(false);
        }

        title.setBounds(245, 50, 400, 50);
        //title.setFont(new Font("Arial", Font.PLAIN, 25));
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 40));

        back.setBounds(10, 10, 100, 25);
        back.addActionListener(e -> MainSettings.mainMenu());
        back.setText("Back");

        panel.add(back);
        panel.add(title);
        panel.add(game1Button);
        panel.add(game2Button);
        panel.add(game3Button);
    }

    /**
     * Similar to continueGame, but for choosing the save file to be replaced by a new game.
     * @param game1 Boolean storing whether file1 has an existing game.
     * @param game2 Boolean storing whether file2 has an existing game.
     * @param game3 Boolean storing whether file3 has an existing game.
     * @param pet1 String storing the name of file1's pet.
     * @param pet2 String storing the name of file2's pet.
     * @param pet3 String storing the name of file3's pet.
     */
    public void chooseNewGame(boolean game1, boolean game2, boolean game3, String pet1, String pet2, String pet3) {
        clearFrame();
        JButton back = new JButton();
        JButton game1Button = new JButton();
        JButton game2Button = new JButton();
        JButton game3Button = new JButton();
        JLabel title = new JLabel("Choose Save File to Replace");

        //panel.setBackground(Color.cyan);
        panel.setBackground(new Color(230, 210, 255)); // baby purple background


        game1Button.setBounds(134, 200, 500, 100);
        game1Button.addActionListener(e -> this.newGame("File1"));
        if (game1) {
            game1Button.setText("Game 1: "+pet1);
        }
        else {
            game1Button.setText("Game 1: --");
        }

        game2Button.setBounds(134, 400, 500, 100);
        game2Button.addActionListener(e -> this.newGame("File2"));
        if (game2) {
            game2Button.setText("Game 2: "+pet2);
        }
        else {
            game2Button.setText("Game 2: --");
        }

        game3Button.setBounds(134, 600, 500, 100);
        game3Button.addActionListener(e -> this.newGame("File3"));
        if (game3) {
            game3Button.setText("Game 3: "+pet3);
        }
        else {
            game3Button.setText("Game 3: --");
        }

        title.setBounds(190, 50, 400, 50);
        //title.setFont(new Font("Arial", Font.PLAIN, 25));
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 25));

        back.setBounds(10, 10, 100, 25);
        back.addActionListener(e -> MainSettings.mainMenu());
        back.setText("Back");

        panel.add(back);
        panel.add(title);
        panel.add(game1Button);
        panel.add(game2Button);
        panel.add(game3Button);
    }

    /**
     * Starts a new game given a chosen pet type and pet name.
     * @param petChosen The AtomicReference<Character> containing the type of the pet.
     * @param petName The JTextField storing the name of the pet.
     * @param filename The name of the file that this pet is to be saved to.
     */
    private void startGame(AtomicReference<Character> petChosen, JTextField petName, String filename) {
        this.clearFrame();
        MainSettings.newGame(petChosen.get(), petName.getText(), filename);
    }

    private void updateTime() {
        end = Instant.now();
        duration = Duration.between(start, end);
        pControls.addPlaytime(duration, currFileNum);
        //System.out.println("Total Duration: " + pControls.getPlaytimeTotal(false));
    }

    /**
     * Displays the main gameplay screen, including the pet's current sprite and various bars storing its stats.
     * Contains buttons for performing all of the main commands.
     * @param pet The pet for this gameplay
     * @param playerScore The player's current score
     * @param inventory The inventory for this gameplay
     */
    public void mainGameplay(Pet pet, int playerScore, Inventory inventory) {
        start = Instant.now();
        this.clearFrame();
        JButton back = new JButton();
        JLabel score = new JLabel("Score: "+playerScore);
        JButton inv = new JButton(new ImageIcon());
        JButton bed = new JButton(new ImageIcon());
        JButton vet = new JButton(new ImageIcon());
        JButton soccer = new JButton(new ImageIcon());
        JButton dumbell = new JButton(new ImageIcon());
        JLabel health = new JLabel("Health");
        JLabel fullness = new JLabel("Fullness");
        JLabel sleep = new JLabel("Sleep");
        JLabel happiness = new JLabel("Happiness");
        JProgressBar healthBar = new JProgressBar();
        JProgressBar fullnessBar = new JProgressBar();
        JProgressBar sleepBar = new JProgressBar();
        JProgressBar happinessBar = new JProgressBar();

        String spriteFile = pet.currentSprite();

        //Initializes icons and sprites
        ImageIcon icon = new ImageIcon(Test.class.getResource(spriteFile));
        ImageIcon icon2 = new ImageIcon(Test.class.getResource("/Sprites/Indoors.png"));
        ImageIcon icon3 = new ImageIcon(Test.class.getResource("/Sprites/bagPic.png"));
        icon.setImage(icon.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
        icon2.setImage(icon2.getImage().getScaledInstance(768, 768, Image.SCALE_SMOOTH));
        JLabel background = new JLabel(icon2);
        JLabel sprite = new JLabel(icon);


        back.setBounds(10, 10, 100, 25);
        back.addActionListener(e -> this.updateTime());
        back.addActionListener(e -> MainSettings.exitGameplay());
        back.setText("Back");

        score.setBounds(500, 10, 100, 25);

        background.setBounds(0,0,768,768);
        sprite.setBounds(400, 125, 256, 256);
        if (pet.getType() == 'b' && pet.getState().getClass() == SleepingState.class)
            sprite.setBounds(400,0,256,256);

        inv.setBounds(10, 100, 25, 25);
        inv.addActionListener(e -> MainSettings.toInventory(pet, playerScore, inventory));
        icon3.setImage(icon3.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        inv.setIcon(icon3);
        inv.setEnabled(MainSettings.canInventory);

        bed.setBounds(10, 150, 25, 25);
        bed.addActionListener(e -> pet.sleep());
        icon3 = new ImageIcon(Test.class.getResource("/Sprites/bedPic.png"));
        icon3.setImage(icon3.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        bed.setIcon(icon3);
        bed.setEnabled(MainSettings.canSleep);

        vet.setBounds(10, 200, 25, 25);
        vet.addActionListener(e -> MainSettings.visitVet());
        icon3 = new ImageIcon(Test.class.getResource("/Sprites/vetPic.png"));
        icon3.setImage(icon3.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        vet.setIcon(icon3);
        vet.setEnabled(MainSettings.canVet);

        soccer.setBounds(10, 250, 25, 25);
        soccer.addActionListener(e -> MainSettings.play());
        icon3 = new ImageIcon(Test.class.getResource("/Sprites/soccerPic.png"));
        icon3.setImage(icon3.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        soccer.setIcon(icon3);
        soccer.setEnabled(MainSettings.canPlay);

        dumbell.setBounds(10, 300, 25, 25);
        dumbell.addActionListener(e -> MainSettings.toExercise());
        icon3 = new ImageIcon(Test.class.getResource("/Sprites/dumbbellPic.png"));
        icon3.setImage(icon3.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        dumbell.setIcon(icon3);
        dumbell.setEnabled(MainSettings.canWalk);

        health.setBounds(30, 410, 50, 50);
        fullness.setBounds(250, 410, 50, 50);
        sleep.setBounds(30, 450, 50, 50);
        happiness.setBounds(250, 450, 70, 50);

        if (pet.getStage() == 0) { //Healthbar size is halved if the pet is in its first stage
            //All variables are doubled before put into the bars, so that 50 (full) corresponds to 100%
            healthBar.setValue(pet.getHealth() * 2);
            healthBar.setBounds(70, 432, 75, 10);
            fullnessBar.setValue(pet.getFullness() * 2);
            fullnessBar.setBounds(300, 432, 75, 10);
            happinessBar.setValue(pet.getHappiness() * 2);
            happinessBar.setBounds(315, 472, 75, 10);
            sleepBar.setValue(pet.getSleep() * 2);
            sleepBar.setBounds(70, 472, 75, 10);
        }
        else {
            healthBar.setValue(pet.getHealth());
            healthBar.setBounds(70, 432, 150, 10);
            fullnessBar.setValue(pet.getFullness());
            fullnessBar.setBounds(300, 432, 150, 10);
            happinessBar.setValue(pet.getHappiness());
            happinessBar.setBounds(315, 472, 150, 10);
            sleepBar.setValue(pet.getSleep());
            sleepBar.setBounds(70, 472, 150, 10);
        }

        //If any bar is below 25%, it is red. Otherwise, goes to the normal bar color.
        if (healthBar.getValue() > 25)
            healthBar.setForeground(Color.green);
        else
            healthBar.setForeground(Color.red);

        if (fullnessBar.getValue() > 25)
            fullnessBar.setForeground(Color.orange);
        else
            fullnessBar.setForeground(Color.red);

        if (happinessBar.getValue() > 25)
            happinessBar.setForeground(Color.yellow);
        else
            happinessBar.setForeground(Color.red);

        if (sleepBar.getValue() > 25)
            sleepBar.setForeground(Color.blue);
        else
            sleepBar.setForeground(Color.red);

        //Add all aspects to the panel and repaint.
        panel.add(back);
        panel.add(score);
        panel.add(sprite);
        panel.add(inv);
        panel.add(bed);
        panel.add(vet);
        panel.add(soccer);
        panel.add(dumbell);
        panel.add(health);
        panel.add(fullness);
        panel.add(sleep);
        panel.add(happiness);
        panel.add(healthBar);
        panel.add(fullnessBar);
        panel.add(happinessBar);
        panel.add(sleepBar);
        panel.add(background);
        panel.repaint();
    }

    /**
     * Displays the player's inventory screen, and allows user to use items.
     * @param inventory The player's inventory object.
     */
    public void toInventory(Inventory inventory) {
        this.clearFrame();
        JButton back = new JButton("Back");
        JLabel title = new JLabel("Inventory");
        //Creates an array of 100 item buttons. Inventory size should never surpass 100.
        JButton[] itemButtons = new JButton[100];
        ArrayList<Item> items = inventory.getItems();
        ImageIcon icon;
        int x = 30; //Stores the x coordinate that the next button should be placed at.
        int y = 100; //Stores the y coordinate that the next button should be placed at.

        panel.setBackground(Color.cyan);

        //For each item in the player's inventory, initialize a button.
        for (int i = 0; i < items.size(); i++) {
            itemButtons[i] = new JButton();
            icon = new ImageIcon(Test.class.getResource(items.get(i).getSprite()));
            icon.setImage(icon.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
            itemButtons[i].setIcon(icon);

            //Food items have orange background
            if (items.get(i).getClass() == Food.class)
                itemButtons[i].setBackground(Color.orange);
            else //Gift items have pink background
                itemButtons[i].setBackground(Color.pink);

            if (items.get(i).getClass() == Food.class && !MainSettings.canFeed) //If this is a food item but the pet is unable to be fed, disable the button.
                itemButtons[i].setEnabled(false);
            int finalI = i;
            itemButtons[i].addActionListener(e -> {MainSettings.useItem(items.get(finalI));}); //Clicking this button will use the appropriate item.
            itemButtons[i].setBounds(x,y,96,96);
            panel.add(itemButtons[i]);
            x += 120; //Move x coordinate
            if (x > 700) { //If x coordinate has exited the screen, go back to the far left and move down a row.
                y += 120;
                x = 30;
            }
        }

        back.setBounds(10, 10, 100, 25);
        back.addActionListener(e -> MainSettings.returnToGameplay());

        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Arial", Font.PLAIN, 25));

        panel.add(back);
        panel.add(title);
        panel.repaint();
    }

    /**
     * Displays animation of this pet visiting the vet.
     * @param pet The pet object.
     */
    public void toVet(Pet pet) {
        try {
            this.clearFrame();
            String spriteFile1 = "/Sprites/" + pet.getSprite() + "/Walk1.png";
            String spriteFile2 = "/Sprites/" + pet.getSprite() + "/Walk2.png";
            ImageIcon icon = new ImageIcon(Test.class.getResource(spriteFile1));
            ImageIcon icon2 = new ImageIcon(Test.class.getResource(spriteFile2));
            ImageIcon icon3 = new ImageIcon(Test.class.getResource("/Sprites/Vet.png"));
            icon.setImage(icon.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            icon2.setImage(icon2.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            icon3.setImage(icon3.getImage().getScaledInstance(768, 768, Image.SCALE_SMOOTH));
            JLabel background = new JLabel(icon3);
            JLabel sprite = new JLabel(icon2);

            background.setBounds(0, 0, 768, 768);
            sprite.setBounds(-256, 370, 256, 256);

            panel.add(sprite);
            panel.add(background);

            // Pet moves towards the door of the vet
            while (sprite.getX() < 285) {
                Thread.sleep(10);
                sprite.setLocation(sprite.getX() + 2, sprite.getY());
                panel.paintImmediately(panel.getBounds());
            }
            //After reaching the door, pet disappears to go inside
            sprite.setVisible(false);
            panel.paintImmediately(panel.getBounds());
            Thread.sleep(2000);
            sprite.setIcon(icon);
            sprite.setVisible(true);
            //After some time, pet turns around and appears outside the vet
            while (sprite.getX() > -256) { //Pet leaves the screen via the left side
                Thread.sleep(10);
                sprite.setLocation(sprite.getX() - 3, sprite.getY());
                panel.paintImmediately(panel.getBounds());
            }
            panel.remove(sprite);
        } catch (Exception e) {}
    }

    /**
     * Displays the pet's playing animation.
     * @param pet The pet object.
     * @param playerScore The player's current score.
     */
    public void toPlay(Pet pet, int playerScore) {
        try {
            //Initializes main gameplay screen as normal, except with pet moved and command buttons removed.
            this.clearFrame();
            JButton back = new JButton();
            JLabel score = new JLabel("Score: " + playerScore);
            JLabel health = new JLabel("Health");
            JLabel fullness = new JLabel("Fullness");
            JLabel sleep = new JLabel("Sleep");
            JLabel happiness = new JLabel("Happiness");
            JProgressBar healthBar = new JProgressBar();
            JProgressBar fullnessBar = new JProgressBar();
            JProgressBar sleepBar = new JProgressBar();
            JProgressBar happinessBar = new JProgressBar();
            int counter = 0;

            String spriteFile1 = "/Sprites/" + pet.getSprite() + "/Happy1.png";
            String spriteFile2 = "/Sprites/" + pet.getSprite() + "/Happy2.png";

            ImageIcon icon = new ImageIcon(Test.class.getResource(spriteFile1));
            ImageIcon icon2 = new ImageIcon(Test.class.getResource(spriteFile2));
            ImageIcon icon3 = new ImageIcon(Test.class.getResource("/Sprites/Indoors.png"));
            icon.setImage(icon.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            icon2.setImage(icon2.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            icon3.setImage(icon3.getImage().getScaledInstance(768, 768, Image.SCALE_SMOOTH));
            JLabel background = new JLabel(icon3);
            JLabel sprite = new JLabel(icon);

            back.setBounds(10, 10, 100, 25);
            back.setEnabled(false);
            back.setText("Back");

            score.setBounds(500, 10, 100, 25);

            background.setBounds(0, 0, 768, 768);
            sprite.setBounds(400, 480, 256, 256);

            health.setBounds(30, 410, 50, 50);
            fullness.setBounds(250, 410, 50, 50);
            sleep.setBounds(30, 450, 50, 50);
            happiness.setBounds(250, 450, 70, 50);

            if (pet.getStage() == 0) {
                healthBar.setValue(pet.getHealth() * 2);
                healthBar.setBounds(70, 432, 75, 10);
                fullnessBar.setValue(pet.getFullness() * 2);
                fullnessBar.setBounds(300, 432, 75, 10);
                happinessBar.setValue(pet.getHappiness() * 2);
                happinessBar.setBounds(315, 472, 75, 10);
                sleepBar.setValue(pet.getSleep() * 2);
                sleepBar.setBounds(70, 472, 75, 10);
            }
            else {
                healthBar.setValue(pet.getHealth());
                healthBar.setBounds(70, 432, 150, 10);
                fullnessBar.setValue(pet.getFullness());
                fullnessBar.setBounds(300, 432, 150, 10);
                happinessBar.setValue(pet.getHappiness());
                happinessBar.setBounds(315, 472, 150, 10);
                sleepBar.setValue(pet.getSleep());
                sleepBar.setBounds(70, 472, 150, 10);
            }

            if (healthBar.getValue() > 25)
                healthBar.setForeground(Color.green);
            else
                healthBar.setForeground(Color.red);

            if (fullnessBar.getValue() > 25)
                fullnessBar.setForeground(Color.orange);
            else
                fullnessBar.setForeground(Color.red);

            if (happinessBar.getValue() > 25)
                happinessBar.setForeground(Color.yellow);
            else
                happinessBar.setForeground(Color.red);

            if (sleepBar.getValue() > 25)
                sleepBar.setForeground(Color.blue);
            else
                sleepBar.setForeground(Color.red);

            panel.add(back);
            panel.add(score);
            panel.add(sprite);
            panel.add(health);
            panel.add(fullness);
            panel.add(sleep);
            panel.add(happiness);
            panel.add(healthBar);
            panel.add(fullnessBar);
            panel.add(happinessBar);
            panel.add(sleepBar);
            panel.add(background);
            panel.paintImmediately(0, 0, panel.getWidth(), panel.getHeight());

            //Begins animation
            for (int i = 0; i < 2; i++) { //Pet will run back and fourth 3 times.
                while (sprite.getX() > 50) { //Pet moves to the left
                    counter++; //Counter variable has pet's sprite change every so often during movement
                    if (counter == 15)
                        sprite.setIcon(icon2);
                    else if (counter == 30) {
                        sprite.setIcon(icon);
                        counter = 0;
                    }
                    Thread.sleep(10);
                    sprite.setBounds(sprite.getX() - 8, 480, 256, 256);
                    panel.paintImmediately(0, 0, panel.getWidth(), panel.getHeight());
                }
                while (sprite.getX() < 400) { //Pet moves to the right
                    counter++; //Sprite continues to swap back and fourth
                    if (counter == 15)
                        sprite.setIcon(icon2);
                    else if (counter == 30) {
                        sprite.setIcon(icon);
                        counter = 0;
                    }
                    Thread.sleep(10);
                    sprite.setBounds(sprite.getX() + 8, 480, 256, 256);
                    panel.paintImmediately(0, 0, panel.getWidth(), panel.getHeight());
                }
            }
        } catch (Exception e) {}
    }

    /**
     * Displays the pet's exercise animation.
     * @param pet The pet object.
     */
    public void toExercise(Pet pet) {
        try {
            this.clearFrame();
            String spriteFile1 = "/Sprites/" + pet.getSprite() + "/Walk1.png";
            String spriteFile2 = "/Sprites/" + pet.getSprite() + "/Walk2.png";
            ImageIcon icon = new ImageIcon(Test.class.getResource(spriteFile1));
            ImageIcon icon2 = new ImageIcon(Test.class.getResource(spriteFile2));
            ImageIcon icon3 = new ImageIcon(Test.class.getResource("/Sprites/Outdoors.png"));
            icon.setImage(icon.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            icon2.setImage(icon2.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            icon3.setImage(icon3.getImage().getScaledInstance(768, 768, Image.SCALE_SMOOTH));
            JLabel background = new JLabel(icon3);
            JLabel sprite = new JLabel(icon2);

            background.setBounds(0, 0, 768, 768);
            sprite.setBounds(-256, 370, 256, 256);

            panel.add(sprite);
            panel.add(background);

            while (sprite.getX() < 768) { //Pet moves from left to right side of the screen
                Thread.sleep(10);
                sprite.setLocation(sprite.getX() + 3, sprite.getY());
                panel.paintImmediately(panel.getBounds());
            }
            //Pause for a while, and then have pet's sprite change the direction it is facing.
            Thread.sleep(2000);
            sprite.setIcon(icon);
            while (sprite.getX() > -256) { //Pet moves from right to the left side of the screen.
                Thread.sleep(10);
                sprite.setLocation(sprite.getX() - 3, sprite.getY());
                panel.paintImmediately(panel.getBounds());
            }
            panel.remove(sprite);
        } catch (Exception e) {}
    }

    /**
     * Animation for feeding the pet
     * @param pet The pet object
     * @param playerScore The player's current score
     * @param foodItem The food item being given
     */
    public void feed(Pet pet, int playerScore, Food foodItem) {
        try {
            //Setup main gameplay screen as normal, but with command buttons removed.
            this.clearFrame();
            JButton back = new JButton();
            JLabel score = new JLabel("Score: " + playerScore);
            JLabel health = new JLabel("Health");
            JLabel fullness = new JLabel("Fullness");
            JLabel sleep = new JLabel("Sleep");
            JLabel happiness = new JLabel("Happiness");
            JProgressBar healthBar = new JProgressBar();
            JProgressBar fullnessBar = new JProgressBar();
            JProgressBar sleepBar = new JProgressBar();
            JProgressBar happinessBar = new JProgressBar();

            String spriteFile = pet.currentSprite();

            ImageIcon icon = new ImageIcon(Test.class.getResource(spriteFile));
            ImageIcon icon2 = new ImageIcon(Test.class.getResource("/Sprites/Indoors.png"));
            ImageIcon happy1 = new ImageIcon(Test.class.getResource("/Sprites/" + pet.getSprite() + "/Happy1.png"));
            ImageIcon happy2 = new ImageIcon(Test.class.getResource("/Sprites/" + pet.getSprite() + "/Happy2.png"));
            ImageIcon foodSprite = new ImageIcon(Test.class.getResource(foodItem.getSprite()));
            icon.setImage(icon.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            icon2.setImage(icon2.getImage().getScaledInstance(768, 768, Image.SCALE_SMOOTH));
            happy1.setImage(happy1.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            happy2.setImage(happy2.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            foodSprite.setImage(foodSprite.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
            JLabel background = new JLabel(icon2);
            JLabel sprite = new JLabel(icon);
            JLabel food = new JLabel(foodSprite);

            back.setBounds(10, 10, 100, 25);
            back.setEnabled(false);
            back.setText("Back");

            score.setBounds(500, 10, 100, 25);

            background.setBounds(0, 0, 768, 768);
            sprite.setBounds(400, 125, 256, 256);
            food.setBounds(500, -32, 64, 64);

            health.setBounds(30, 410, 50, 50);
            fullness.setBounds(250, 410, 50, 50);
            sleep.setBounds(30, 450, 50, 50);
            happiness.setBounds(250, 450, 70, 50);

            if (pet.getStage() == 0) {
                healthBar.setValue(pet.getHealth() * 2);
                healthBar.setBounds(70, 432, 75, 10);
                fullnessBar.setValue(pet.getFullness() * 2);
                fullnessBar.setBounds(300, 432, 75, 10);
                happinessBar.setValue(pet.getHappiness() * 2);
                happinessBar.setBounds(315, 472, 75, 10);
                sleepBar.setValue(pet.getSleep() * 2);
                sleepBar.setBounds(70, 472, 75, 10);
            }
            else {
                healthBar.setValue(pet.getHealth());
                healthBar.setBounds(70, 432, 150, 10);
                fullnessBar.setValue(pet.getFullness());
                fullnessBar.setBounds(300, 432, 150, 10);
                happinessBar.setValue(pet.getHappiness());
                happinessBar.setBounds(315, 472, 150, 10);
                sleepBar.setValue(pet.getSleep());
                sleepBar.setBounds(70, 472, 150, 10);
            }

            if (healthBar.getValue() > 25)
                healthBar.setForeground(Color.green);
            else
                healthBar.setForeground(Color.red);

            if (fullnessBar.getValue() > 25)
                fullnessBar.setForeground(Color.orange);
            else
                fullnessBar.setForeground(Color.red);

            if (happinessBar.getValue() > 25)
                happinessBar.setForeground(Color.yellow);
            else
                happinessBar.setForeground(Color.red);

            if (sleepBar.getValue() > 25)
                sleepBar.setForeground(Color.blue);
            else
                sleepBar.setForeground(Color.red);

            panel.add(back);
            panel.add(score);
            panel.add(food);
            panel.add(sprite);
            panel.add(health);
            panel.add(fullness);
            panel.add(sleep);
            panel.add(happiness);
            panel.add(healthBar);
            panel.add(fullnessBar);
            panel.add(happinessBar);
            panel.add(sleepBar);
            panel.add(background);
            panel.paintImmediately(panel.getBounds());

            while (food.getY() < 225) { //Food item falls from top of the screen until it reaches the pet
                Thread.sleep(10);
                food.setLocation(food.getX(), food.getY() + 3);
                panel.paintImmediately(panel.getBounds());
            }
            panel.remove(food); //Food is removed
            panel.paintImmediately(panel.getBounds());

            for (int i = 0; i < 5; i++) { //Pet cycles between its 2 happy sprites 5 times.
                sprite.setIcon(happy1);
                panel.paintImmediately(0, 0, panel.getWidth(), panel.getHeight());
                Thread.sleep(100);
                sprite.setIcon(happy2);
                panel.paintImmediately(0, 0, panel.getWidth(), panel.getHeight());
                Thread.sleep(100);
            }
        } catch (Exception e) {System.out.println(e); }
        MainSettings.returnToGameplay();
    }

    /**
     * Animation for giving pet a gift.
     * @param pet The pet object
     * @param playerScore The player's current score
     * @param giftItem The gift item being given
     */
    public void giveGift(Pet pet, int playerScore, Gift giftItem) {
        try {
            //Sets up main gameplay screen as normal, but with pet moved and buttons removed.
            this.clearFrame();
            JButton back = new JButton();
            JLabel score = new JLabel("Score: " + playerScore);
            JLabel health = new JLabel("Health");
            JLabel fullness = new JLabel("Fullness");
            JLabel sleep = new JLabel("Sleep");
            JLabel happiness = new JLabel("Happiness");
            JProgressBar healthBar = new JProgressBar();
            JProgressBar fullnessBar = new JProgressBar();
            JProgressBar sleepBar = new JProgressBar();
            JProgressBar happinessBar = new JProgressBar();
            int counter = 0;

            String spriteFile1 = "/Sprites/" + pet.getSprite() + "/Happy1.png";
            String spriteFile2 = "/Sprites/" + pet.getSprite() + "/Happy2.png";

            ImageIcon icon = new ImageIcon(Test.class.getResource(spriteFile1));
            ImageIcon icon2 = new ImageIcon(Test.class.getResource(spriteFile2));
            ImageIcon icon3 = new ImageIcon(Test.class.getResource("/Sprites/Indoors.png"));
            ImageIcon giftSprite = new ImageIcon(Test.class.getResource(giftItem.getSprite()));
            ImageIcon boxSprite = new ImageIcon(Test.class.getResource("/Sprites/gift.png"));
            icon.setImage(icon.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            icon2.setImage(icon2.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            icon3.setImage(icon3.getImage().getScaledInstance(768, 768, Image.SCALE_SMOOTH));
            giftSprite.setImage(giftSprite.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
            boxSprite.setImage(boxSprite.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
            JLabel background = new JLabel(icon3);
            JLabel sprite = new JLabel(icon);
            JLabel giftBox = new JLabel(boxSprite);
            JLabel gift = new JLabel(giftSprite);

            back.setBounds(10, 10, 100, 25);
            back.setEnabled(false);
            back.setText("Back");

            score.setBounds(500, 10, 100, 25);

            background.setBounds(0, 0, 768, 768);
            sprite.setBounds(400, 480, 256, 256);
            giftBox.setBounds(350, 600, 64, 64);
            gift.setBounds(350, 600, 64, 64);

            health.setBounds(30, 410, 50, 50);
            fullness.setBounds(250, 410, 50, 50);
            sleep.setBounds(30, 450, 50, 50);
            happiness.setBounds(250, 450, 70, 50);

            if (pet.getStage() == 0) {
                healthBar.setValue(pet.getHealth() * 2);
                healthBar.setBounds(70, 432, 75, 10);
                fullnessBar.setValue(pet.getFullness() * 2);
                fullnessBar.setBounds(300, 432, 75, 10);
                happinessBar.setValue(pet.getHappiness() * 2);
                happinessBar.setBounds(315, 472, 75, 10);
                sleepBar.setValue(pet.getSleep() * 2);
                sleepBar.setBounds(70, 472, 75, 10);
            }
            else {
                healthBar.setValue(pet.getHealth());
                healthBar.setBounds(70, 432, 150, 10);
                fullnessBar.setValue(pet.getFullness());
                fullnessBar.setBounds(300, 432, 150, 10);
                happinessBar.setValue(pet.getHappiness());
                happinessBar.setBounds(315, 472, 150, 10);
                sleepBar.setValue(pet.getSleep());
                sleepBar.setBounds(70, 472, 150, 10);
            }

            if (healthBar.getValue() > 25)
                healthBar.setForeground(Color.green);
            else
                healthBar.setForeground(Color.red);

            if (fullnessBar.getValue() > 25)
                fullnessBar.setForeground(Color.orange);
            else
                fullnessBar.setForeground(Color.red);

            if (happinessBar.getValue() > 25)
                happinessBar.setForeground(Color.yellow);
            else
                happinessBar.setForeground(Color.red);

            if (sleepBar.getValue() > 25)
                sleepBar.setForeground(Color.blue);
            else
                sleepBar.setForeground(Color.red);

            panel.add(back);
            panel.add(giftBox);
            panel.add(gift);
            panel.add(score);
            panel.add(sprite);
            panel.add(health);
            panel.add(fullness);
            panel.add(sleep);
            panel.add(happiness);
            panel.add(healthBar);
            panel.add(fullnessBar);
            panel.add(happinessBar);
            panel.add(sleepBar);
            panel.add(background);
            panel.paintImmediately(0, 0, panel.getWidth(), panel.getHeight());

            //Pet stands in front of gift box for a second
            Thread.sleep(1000);
            while (giftBox.getY() > -360) { //Gift box flies to the top of the screen revealing the gift item
                Thread.sleep(10);
                giftBox.setBounds(giftBox.getX(), giftBox.getY() - 8, giftBox.getWidth(), giftBox.getHeight());
                counter++; //Counter that has the pet cycle between its 2 happy sprites
                if (counter == 10)
                    sprite.setIcon(icon2);
                else if (counter == 20) {
                    sprite.setIcon(icon);
                    counter = 0;
                }
                panel.paintImmediately(0, 0, panel.getWidth(), panel.getHeight());
            }
        } catch (Exception e) {}
        MainSettings.returnToGameplay();
    }

    /**
     * Animation for the pet levelling up.
     * @param pet The pet object.
     * @param playerScore The player's current score.
     * @param oldSprite The sprite that the pet had right before levelling up
     */
    public void levelup(Pet pet, int playerScore, String oldSprite) {
        try {
            //Builds main gameplay screen as normal, but with buttons removed.
            this.clearFrame();
            JButton back = new JButton();
            JLabel score = new JLabel("Score: " + playerScore);
            JLabel health = new JLabel("Health");
            JLabel fullness = new JLabel("Fullness");
            JLabel sleep = new JLabel("Sleep");
            JLabel happiness = new JLabel("Happiness");
            JProgressBar healthBar = new JProgressBar();
            JProgressBar fullnessBar = new JProgressBar();
            JProgressBar sleepBar = new JProgressBar();
            JProgressBar happinessBar = new JProgressBar();

            String spriteFile = pet.currentSprite();

            ImageIcon icon = new ImageIcon(Test.class.getResource("/Sprites/" + oldSprite + "/Happy1.png"));
            ImageIcon icon2 = new ImageIcon(Test.class.getResource("/Sprites/Indoors.png"));
            ImageIcon happy1 = new ImageIcon(Test.class.getResource("/Sprites/" + pet.getSprite() + "/Happy1.png"));
            ImageIcon happy2 = new ImageIcon(Test.class.getResource("/Sprites/" + pet.getSprite() + "/Happy2.png"));
            icon.setImage(icon.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            icon2.setImage(icon2.getImage().getScaledInstance(768, 768, Image.SCALE_SMOOTH));
            happy1.setImage(happy1.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            happy2.setImage(happy2.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            JLabel background = new JLabel(icon2);
            JLabel sprite = new JLabel(icon);

            back.setBounds(10, 10, 100, 25);
            back.setEnabled(false);
            back.setText("Back");

            score.setBounds(500, 10, 100, 25);

            background.setBounds(0, 0, 768, 768);
            sprite.setBounds(400, 125, 256, 256);

            health.setBounds(30, 410, 50, 50);
            fullness.setBounds(250, 410, 50, 50);
            sleep.setBounds(30, 450, 50, 50);
            happiness.setBounds(250, 450, 70, 50);

            healthBar.setValue(pet.getHealth());
            healthBar.setBounds(70, 432, 75, 10);
            fullnessBar.setValue(pet.getFullness());
            fullnessBar.setBounds(300, 432, 75, 10);
            happinessBar.setValue(pet.getHappiness() * 2);
            happinessBar.setBounds(315, 472, 75, 10);
            sleepBar.setValue(pet.getSleep());
            sleepBar.setBounds(70, 472, 75, 10);

            if (healthBar.getValue() > 25)
                healthBar.setForeground(Color.green);
            else
                healthBar.setForeground(Color.red);

            if (fullnessBar.getValue() > 25)
                fullnessBar.setForeground(Color.orange);
            else
                fullnessBar.setForeground(Color.red);

            if (happinessBar.getValue() > 25)
                happinessBar.setForeground(Color.yellow);
            else
                happinessBar.setForeground(Color.red);

            if (sleepBar.getValue() > 25)
                sleepBar.setForeground(Color.blue);
            else
                sleepBar.setForeground(Color.red);

            panel.add(back);
            panel.add(score);
            panel.add(sprite);
            panel.add(health);
            panel.add(fullness);
            panel.add(sleep);
            panel.add(happiness);
            panel.add(healthBar);
            panel.add(fullnessBar);
            panel.add(happinessBar);
            panel.add(sleepBar);
            panel.add(background);
            panel.paintImmediately(panel.getBounds());

            //Pet stands still for a while before evolving
            Thread.sleep(2000);
            //After evolving, all stat bars double in size.
            healthBar.setBounds(70, 432, 150, 10);
            fullnessBar.setBounds(300, 432, 150, 10);
            happinessBar.setBounds(315, 472, 150, 10);
            sleepBar.setBounds(70, 472, 150, 10);

            //Pet plays its happy animation with its new sprites
            for (int i = 0; i < 5; i++) {
                sprite.setIcon(happy1);
                panel.paintImmediately(panel.getBounds());
                Thread.sleep(200);
                sprite.setIcon(happy2);
                panel.paintImmediately(panel.getBounds());
                Thread.sleep(200);
            }
        } catch (Exception e) {}
        MainSettings.returnToGameplay();
    }

    /**
     * Main method, used for testing. Running will lead to incorrect and unpredictable behavior, as it was
     * mainly used to visualize gui screens without proper setups.
     */
    public static void main(String[] args) {
        GUI test = new GUI();
        Pet pet = new Pet("Test", 'b', new PetObserver[0]);
        String old = pet.getSprite();
        //pet.levelup();
        //test.levelup(pet, 0, old);
        pet.setState(new AngryState());
        Inventory inventory = new Inventory();
        test.toInventory(inventory);
       // test.mainGameplay(pet, 0, inventory);
        //while (true) {
         //   try {
        //         Thread.sleep(100);
        //    } catch (Exception e) {}
        //   pet.updateStats();
        //    test.mainGameplay(pet, 0, inventory);
        //}
    }
}

