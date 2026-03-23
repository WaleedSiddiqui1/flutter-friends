/**
 * Represents the angry behavioral state of a virtual pet. (Implements PetState)
 * In this state, the pet is unhappy and only accepts the {@code giveGift()} and {@code play()} commands

 * Once the pet's happiness reaches at least half of its maximum value,
 * it automatically transitions back to {@link NormalState}.
 *
 * @author aliciaserdyuk
 * @author Mitchell Menzies
 * @since 03/26/25
 * @see PetState
 * @see NormalState
 * @see Gift
 * @see PlayCommand
 */

public class AngryState implements PetState{
    /**
     * Feeding is not allowed while in the angry state.
     *
     * @param pet  the pet the player is trying to feed
     * @param food the food item being offered
     */
    @Override
    public void feed(Pet pet, Food food) {
        // angry, can't eat
    }


    /**
     * Allows the pet to play while in an angry state.
     * If the pet’s happiness increases to 50 or more (half the total), it transitions back to the normal state.
     *
     * @param pet the pet the player is trying to play with
     */
    @Override
    public void play(Pet pet) {
        new PlayCommand(pet).execute();

        // Pet exits Angry State when happiness reaches at least 1/2 of the maximum
        if (pet.getHappiness() >= 50) {
            pet.setState(new NormalState());
        }
    }


    /**
     * Sleeping is not allowed while in the angry state.
     *
     * @param pet the pet the player is trying to put to sleep
     */
    @Override
    public void sleep(Pet pet) {
        // too angry to sleep
    }


    /**
     * Visiting the vet is not allowed while in the angry state.
     *
     * @param pet the pet the player is trying to take to the vet
     */
    @Override
    public void takeToVet(Pet pet) {
        // too angry to go to vet
    }


    /**
     * Allows the pet to receive a gift in an angry state.
     * If the gift increases happiness to 50 or more, the pet returns to normal.
     *
     * @param pet  the pet receiving the gift
     * @param gift the gift item given to the pet
     */
    @Override
    public void giveGift(Pet pet, Gift gift) {
        new UseItemCommand(gift, pet).execute();

        // Pet exits Angry State when happiness reaches at least 1/2 of the maximum
        if (pet.getHappiness() >= 50) {
            pet.setState(new NormalState());
        }
    }

    /**
     * Sets variables for which buttons are currently enabled based on the pet's current state.
     * All except inventory and play disabled for angry state.
     */
    @Override
    public void enabledButtons(){
        MainSettings.canInventory = true;
        MainSettings.canSleep = false;
        MainSettings.canPlay = true;
        MainSettings.canVet = false;
        MainSettings.canWalk = false;
        MainSettings.canFeed = false;
    }
}
