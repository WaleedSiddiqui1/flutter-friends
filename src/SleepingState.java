/**
 * Represents the sleeping behavioral state of a virtual pet. (Implements PetState)
 * In this state, the pet is asleep and will not respond to any commands. All commands are disabled
 * Once the pet's sleep level reaches it's maximum, the game transitions the pet out of the SleepingState
 *
 * @author aliciaserdyuk
 * @author Mitchell Menzies
 * @since 03/26/25
 * @see PetState
 * @see Pet
 */

public class SleepingState implements PetState{
    /**
     * The pet is asleep and cannot eat.
     *
     * @param pet  the pet the player is trying to feed
     * @param food the food item being offered
     */
    @Override
    public void feed(Pet pet, Food food) {
        // sleeping can't eat
    }


    /**
     * The pet is asleep and cannot play.
     *
     * @param pet the pet the player is trying to play with
     */
    @Override
    public void play(Pet pet) {
        // asleep, can't play
    }


    /**
     * The pet is already asleep, so the sleep command is denied.
     *
     * @param pet the pet the player is trying to put to sleep
     */
    @Override
    public void sleep(Pet pet) {
        // already sleeping cant do anything
    }


    /**
     * Visiting the vet is not allowed while in the sleeping state.
     *
     * @param pet the pet the player is trying to take to the vet
     */
    @Override
    public void takeToVet(Pet pet) {
        // asleep, cant take to vet
    }


    /**
     * Giving gifts is not allowed in the sleeping state.
     *
     * @param pet  the pet the player is trying to give a gift to
     * @param gift the gift item being offered
     */
    @Override
    public void giveGift(Pet pet, Gift gift) {
        // pet is asleep cannot give gift
    }

    /**
     * Sets variables for which buttons are currently enabled based on the pet's current state.
     * All are disabled for sleeping state.
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
