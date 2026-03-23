/**
 * Represents the hungry behavioral state of a virtual pet. (Implements PetState)
 * In this state, the pet accepts all commands (feed, play, sleep, take to vet, give gifts )
 *
 * Feeding the pet will increase its fullness and transition it back to {@link NormalState}
 *
 * @author aliciaserdyuk
 * @author Mitchell Menzies
 * @since 03/26/25
 * @see PetState
 * @see Pet
 * @see PlayCommand
 * @see UseItemCommand
 * @see VisitVetCommand
 * @see GoToBed
 * @see SleepingState
 * @see NormalState
 */

public class HungryState implements PetState{
    /**
     * Feeds the pet using the given food item.
     * Feeding resolves the hunger and transitions the pet back to the Normal State.
     *
     * @param pet  the pet to feed
     * @param food the food item to give
     */
    @Override
    public void feed(Pet pet, Food food) {
        new UseItemCommand(food, pet).execute();
        pet.setState(new NormalState()); // Eating brings pet back to normal
    }


    /**
     * Plays with the pet
     *
     * @param pet the pet to play with
     */
    @Override
    public void play(Pet pet) {
        new PlayCommand(pet).execute();
    }


    /**
     * Puts the pet to sleep
     *
     * @param pet the pet to put to sleep
     */
    @Override
    public void sleep(Pet pet) {
        new GoToBed(pet).execute();
        pet.setState(new SleepingState()); // Transition to sleeping state
    }


    /**
     * Takes the pet to the vet.
     *
     * @param pet the pet to take to the vet
     */
    @Override
    public void takeToVet(Pet pet) {
        new VisitVetCommand(pet).execute();
    }


    /**
     * Gives a gift to the pet
     *
     * @param pet  the pet receiving the gift
     * @param gift the gift item being given
     */
    @Override
    public void giveGift(Pet pet, Gift gift) {
        new UseItemCommand(gift, pet).execute();
    }

    /**
     * Sets variables for which buttons are currently enabled based on the pet's current state.
     * All are enabled for hungry state.
     */
    @Override
    public void enabledButtons(){
        MainSettings.canInventory = true;
        MainSettings.canSleep = true;
        MainSettings.canPlay = true;
        MainSettings.canVet = true;
        MainSettings.canWalk = true;
        MainSettings.canFeed = true;
    }
}
