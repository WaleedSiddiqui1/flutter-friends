/**
 * Interface for classes the observe changes to a{@link Pet}'s state
 * <p>
 * This interface is part of the Observer design pattern. Allows UI components (HungerBar, HealthIndicator, HappinessMeter)
 * to subscribe to a Pet and receive updates whenever the pet's stats change
 *
 * @author aliciaserdyuk
 * @since 03/26/25
 * @see Pet
 * @see HungerBar
 * @see HealthIndicator
 * @see HappinessMeter
 */

public interface PetObserver {
    /**
     * Called when the observed pet's attributes (hunger, health, or happiness) changes.
     *
     * @param pet the Pet object that has been updated
     */
    void update(Pet pet);
}
