/**
 * Represents the dead behavioral state of a virtual pet. (Implements PetState)
 * In this state, the pet can no longer interact with the player. All commands are disabled
 *
 * @author aliciaserdyuk
 * @author Mitchell Menzies
 * @since 03/26/25
 * @see PetState
 * @see Pet
 */

public class DeadState implements PetState {

    /**
     * Feeding is not possible while in the dead state.
     *
     * @param pet  the pet the player is trying to feed
     * @param food the food item being offered
     */
    @Override
    public void feed(Pet pet, Food food) {
        // dead cant eat
    }

    /**
     * Playing is not possible while in the dead state.
     *
     * @param pet the pet the player is trying to play with
     */
    @Override
    public void play(Pet pet) {
        // dead cant play
    }

    /**
     * Sleeping is not possible while in the dead state.
     *
     * @param pet the pet the player is trying to put to sleep
     */
    @Override
    public void sleep(Pet pet) {
        // dead cant sleep
    }

    /**
     * Visiting the vet is not possible while in the dead state.
     *
     * @param pet the pet the player is trying to take to the vet
     */
    @Override
    public void takeToVet(Pet pet) {
        // dead cant take to vet
    }

    /**
     * Giving gifts is not possible while in the dead state.
     *
     * @param pet  the pet the player is trying to give a gift to
     * @param gift the gift item being offered
     */
    @Override
    public void giveGift(Pet pet, Gift gift) {
        // dead cant give gift
    }

    /**
     * Sets variables for which buttons are currently enabled based on the pet's current state. All disabled for dead state.
     */
    @Override
    public void enabledButtons(){
        MainSettings.canInventory = false;
        MainSettings.canSleep = false;
        MainSettings.canPlay = false;
        MainSettings.canVet = false;
        MainSettings.canWalk = false;
        MainSettings.canFeed = false;
    }
}
