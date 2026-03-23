/**
 * This interface defines the contract for all behavioural states of a virtual pet.
 * Explicitly defining what commands can and cannot be done when in a particular state.
 * Implementation defined by the different states of the Pet (AngryState, DeadState, HungryState, NormalState, SleepingState)
 * Part of the State design pattern, allowing the Pet's behaviour to change dynamically
 *
 * @author aliciaserdyuk
 * @since 03/26/25
 * @see AngryState
 * @see DeadState
 * @see HungryState
 * @see NormalState
 * @see SleepingState
 * @see Pet
 */
public interface PetState {

    /**
     * Feeds the pet with the given food item.
     *
     * @param pet the pet to feed
     * @param food the food item to give
     */
    void feed(Pet pet, Food food);

    /**
     * Plays with the pet.
     *
     * @param pet the pet to play with
     */
    void play(Pet pet);

    /**
     * Puts the pet to sleep.
     *
     * @param pet the pet to put to sleep
     */
    void sleep(Pet pet);

    /**
     * Takes the pet to the vet.
     *
     * @param pet the pet to take to the vet
     */
    void takeToVet(Pet pet);

    /**
     * Give the pet a gift.
     *
     * @param pet the pet to receive the gift
     * @param gift the gift item to give
     */
    void giveGift(Pet pet, Gift gift);

    void enabledButtons();
}
