/**
 * This class implements a pet object, with various given attributes.
 * @author Mitchell Menzies
 * @author Waleed Siddiqui
 * @version 1.0
 * @see PetState
 * @see PetObserver
 * @see MainSettings
 */

import java.lang.*;

public class Pet {
    /**
     * The name of this pet.
     */
    private String name;

    /**
     * One char representing the type of the pet.
     */
    private char type;
    /**
     * Health level for pet.
     */
    private int health;
    /**
     * Fullness level for pet.
     */
    private int fullness;
    /**
     * Sleep level for pet.
     */
    private int sleep;
    /**
     * Happiness level for pet.
     */
    private int happiness;
    /**
     * The name of the folder in which this pet will pull its sprites from
     */
    private String sprite;
    /**
     * The stage or level of this pet.
     */
    private int stage;
    /**
     * The current state of the pet.
     */
    private PetState state;
    /**
     * A list of observers of this pet.
     */
    private PetObserver[] observers;
    /**
     * An integer representing the maximum value for this pet's stats
     */
    private int maxStats;

    /**
     * Basic constructor method for a new pet. Takes in name and type and fills the rest as defaults.
     * @param name A string representing this pet's name.
     * @param type A single char representing this pet's type.
     * @param observers An array of observers for this pet.
     */
    public Pet(String name, char type, PetObserver[] observers) {
        this.name = name;
        this.type = type;
        this.observers = observers;
        health = 50;
        fullness = 50;
        sleep = 50;
        happiness = 50;
        stage = 0;
        maxStats = 50;
        state = new NormalState();
        assignSprite();
    }

    /**
     * Sets the sprite for this pet based off its type.
     */
    private void assignSprite() {
        if (this.type == 'c') { //If the type is chicken
            if (this.stage == 0) //Set sprite based on the stage
                this.sprite = "Chicken0";
            else if (this.stage == 1)
                this.sprite = "Chicken1";
            else //If an invalid stage is used, send an error message
                System.out.println("Something went wrong. Stage is " + this.stage + " when it should be either 0 or 1.");
        }
        else if (this.type == 'b'){ //Repeat for bat
            if (this.stage == 0)
                this.sprite = "Bat0";
            else if (this.stage == 1)
                this.sprite = "Bat1";
            else
                System.out.println("Something went wrong. Stage is "+this.stage+" when it should be either 0 or 1.");
        }
        else if (this.type == 'p') { //Repeat for penguin
            if (this.stage == 0)
                this.sprite = "Penguin0";
            else if (this.stage == 1)
                this.sprite = "Penguin1";
            else
                System.out.println("Something went wrong. Stage is "+this.stage+" when it should be either 0 or 1.");
        }
        else //If an unsupported type is used, return an error message.
            System.out.println("Something went wrong. Type is "+this.type+" when currently only 'c', 'p' and 'b' are supported.");
    }
    /**
     * Returns the name of the pet.
     * @return A string representing the pet's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the pet.
     * @return A char representing the pet's type.
     */
    public char getType() {
        return type;
    }

    /**
     * Returns the health of the pet.
     * @return An integer representing the pet's health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the pet
     * @param health The new health value.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Returns the fullness of the pet.
     * @return An integer representing the pet's fullness.
     */
    public int getFullness() {
        return fullness;
    }

    /**
     * Sets the fullness of the pet.
     * @param fullness The new fullness value.
     */
    public void setFullness(int fullness) {
        this.fullness = fullness;
    }

    /**
     * Returns the sleep of the pet.
     * @return An integer representing the pet's sleep.
     */
    public int getSleep() {
        return sleep;
    }

    /**
     * Sets the sleep value for the pet.
     * @param sleep The new sleep value for the pet.
     */
    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    /**
     * Returns the happiness of the pet.
     * @return An integer representing the pet's happiness.
     */
    public int getHappiness() {
        return happiness;
    }

    /**
     * Sets the happiness of the pet.
     * @param happiness The new happiness value for the pet.
     */
    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }


    /**
     * Increases the pet’s fullness by a specified amount depending on the food fed
     * Caps at 100
     * @param amount the amount to increase fullness by
     */
    public void increaseFullness(int amount) {
        this.fullness = Math.min(this.fullness + amount, 100);
    }

    /**
     * Increases the pet’s happiness by a specified amount depending on the gift used
     * Caps at 100.
     * @param amount the amount to increase happiness by
     */
    public void increaseHappiness(int amount) {
        this.happiness = Math.min(this.happiness + amount, 100);
    }
    /**
     * Returns the name of the folder this pet's sprites are stored in.
     * @return A string representing the folder's name.
     */
    public String getSprite(){
        return sprite;
    }

    /**
     * Returns the stage of the pet.
     * @return An integer representing the pet's stage.
     */
    public int getStage() {
        return stage;
    }

    /**
     * Sets the stage of the pet.
     * @param stage The new stage for the pet.
     */
    public void setStage(int stage) {
        this.stage = stage;
        this.assignSprite();
        if (stage == 1)
            this.maxStats = 100;
    }

    /**
     * Returns the current state of the pet.
     * @return A state object representing this pet's state.
     */
    public PetState getState() {
        return state;
    }

    public char getStateChar() {
        if (this.state.getClass() == NormalState.class)
            return 'n';
        else if (this.state.getClass() == AngryState.class)
            return 'a';
        else if (this.state.getClass() == SleepingState.class)
            return 's';
        else if (this.state.getClass() == HungryState.class)
            return 'h';
        else
            return 'd';
    }

    /**
     * Returns the array of observers for this pet.
     * @return An array of this pet's observers.
     */
    public PetObserver[] getObservers() {
        return observers;
    }

    public void eat(Food food) {
        System.out.println("*Eat animation*");
        fullness += food.getNutritionValue();
    }

    /**
     * The sleeping command. Sets the pet's state to sleeping.
     */
    public void sleep() {
        state = new SleepingState();
    }

    /**
     * The play command. Increases happiness.
     */
    public void play() {
        this.happiness += 50;
    }

    /**
     * The exercise command. Increases health but decreases sleep and fullness.
     */
    public void exercise() {
        this.health += 25;
        this.sleep -= 25;
        this.fullness -= 25;
    }

    /**
     * The receive gift command. Increments happiness by a given amount.
     * @param gift The gift item being given
     */
    public void receiveGift(Gift gift) {
        happiness += gift.getHappinessBoost();
    }

    /**
     * The visit vet command. Increases pet's health.
     */
    public void visitVet() {
        this.health += 50;
    }

    /**
     * Sets the state of the pet.
     * @param state The state object being set to.
     */
    public void setState(PetState state) {
        this.state = state;
    }

    /**
     * Sets the pet's state based on a character rather than a state object.
     * @param state A character representing the first letter of the state.
     */
    public void setStateChar(char state) {
        if (state == 'n')
            this.state = new NormalState();
        else if (state == 'a')
            this.state = new AngryState();
        else if (state == 'h')
            this.state = new HungryState();
        else if (state == 's')
            this.state = new SleepingState();
        else //There is no other state it can be, so it must be dead state.
            this.state = new DeadState();
    }

    /**
     * Adds an observer object to this pet's observers.
     * @param observer The observer object being added.
     */
    public void addObserver(PetObserver observer){
        PetObserver[] newObservers = new PetObserver[observers.length + 1];
        for (int i = 0; i < observers.length; i++) {
            newObservers[i] = observers[i];
        }
        newObservers[observers.length] = observer;
        observers = newObservers;
    }

    /**
     * Removes an observer from this pet's observers.
     * @param observer The observer being removed.
     */
    public void removeObserver(PetObserver observer) {
        try {
            PetObserver[] newObservers = new PetObserver[observers.length - 1];
            for (int i = 0; i < observers.length; i++) {
                if (observers[i] == observer) {
                    for (int j = i + 1; j < observers.length; j++)
                        newObservers[j - 1] = observers[j];
                }
            }
            observers = newObservers;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Observer does not exist.");
        }
    }

    /**
     * Notifies all observers.
     */
    public void notifyObservers() {
        for (int i = 0; i < observers.length; i++) {
            observers[i].update(this);
        }
    }

    /**
     * Advances this pet to its second (and final) stage.
     */
    public void levelup() {
        //Update stage to 1
        stage = 1;
        this.maxStats = 100;

        //Double each attribute to correspond with the new maximum values for each
        health = health * 2;
        fullness = fullness * 2;
        sleep = sleep * 2;
        happiness = happiness * 2;

        //Update the sprite
        assignSprite();
    }

    /**
     * Returns the pet's current sprite directory given its type, stage, and current state.
     * @return The directory to this pet's sprite file.
     */
    public String currentSprite() {
        if (this.state.getClass() == NormalState.class)
            return ("/Sprites/"+this.sprite+"/Normal.png");
        else if (this.state.getClass() == AngryState.class)
            return ("/Sprites/"+this.sprite+"/Angry.png");
        else if (this.state.getClass() == SleepingState.class)
            return ("/Sprites/"+this.sprite+"/Sleep.png");
        else if (this.state.getClass() == HungryState.class)
            return ("/Sprites/"+this.sprite+"/Hungry.png");
        else if (this.state.getClass() == DeadState.class)
            return ("/Sprites/"+this.sprite+"/Dead.png");
        else
            return "Error"; //Occurs if the pet does not have a valid state
    }

    /**
     * Updates the pet's stats, used every tick of main gameplay (every second).
     */
    public void updateStats() {

        //Enables buttons based on the pet's current state.
        this.state.enabledButtons();

        //If any stat passes its maximum value, set it back to maximum.
        if (health > maxStats)
            health = maxStats;
        if (fullness > maxStats)
            fullness = maxStats;
        if (happiness > maxStats)
            happiness = maxStats;

        //If the pet is in normal, hungry or angry state, do normal stat changes
        if (this.state.getClass() == NormalState.class || this.state.getClass() == HungryState.class || this.state.getClass() == AngryState.class) {
            if (this.type == 'b') //Sleep goes down faster for bat
                this.sleep -= 2;
            else
                this.sleep -= 1;

            if (this.type == 'c') //Fullness goes down faster for chicken
                this.fullness -= 2;
            else
                this.fullness -= 1;

            if (this.type == 'p') //Happiness goes down faster for penguin
                this.happiness -= 2;
            else
                this.happiness -= 1;
        }
        if (this.state.getClass() == SleepingState.class) { //If the pet is sleeping
            if (this.type == 'b') //Sleep goes up slower for bat
                this.sleep += 3;
            else
                this.sleep += 6;

            //Other stats go down as normal
            if (this.type == 'c')
                this.fullness -= 2;
            else
                this.fullness -= 1;

            if (this.type == 'p')
                this.happiness -= 2;
            else
                this.happiness -= 1;

            //If the pet is currently sleeping and sleep stat is at full, return to normal state.
            if ((this.stage == 0 && this.sleep >= 50) || (this.stage == 1 && this.sleep >= 100))
                this.state = new NormalState();
        }

        //Unless the pet is sleeping, set it to normal
        if (this.state.getClass() != SleepingState.class)
            this.state = new NormalState();

        //If fullness is 0, decrease health
        if (fullness <= 0) {
            this.health -= 1;
        }

        // If fullness is below 0, set it back to 0
        if (this.fullness < 0 && this.health > 0) {
            this.fullness = 0;
            if (this.state.getClass() != SleepingState.class)
                if (this.state.getClass() != HungryState.class) { //If the pet is not sleeping or already in hungry state, enter hungry state
                    MainSettings.pointPenalty(5);
                    this.state = new HungryState();
                }
        }

        //If happiness drops below 0, set it back to 0
        if (this.happiness < 0 && this.health > 0) {
            this.happiness = 0;
            if (this.state.getClass() != SleepingState.class)
                if (this.state.getClass() != AngryState.class) { //If the pet is not sleeping or in angry state, set to angry state
                    MainSettings.pointPenalty(5);
                    this.state = new AngryState();
                }
        }

        //If the pet is not dead but its sleep stat has reached 0, decrement health and forcibly enter sleeping state
        if (this.sleep <= 0 && this.health > 0 && this.state.getClass() != SleepingState.class) {
            MainSettings.pointPenalty(100);
            health -= 25;
            state = new SleepingState();
        }
        //If health is less than zero, set all stats to 0 and kill the pet.
        if (this.health <= 0) {
            state = new DeadState();
            fullness = 0;
            happiness = 0;
            sleep = 0;
        }
    }

    /**
     * Unused testing class, prints out pet's stats.
     */
    public void showInfo() {
        System.out.println(name + type + health + fullness + sprite + happiness + sleep);
    }
}
