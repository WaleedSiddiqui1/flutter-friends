/**
 * A UI component that visually represents the pet's health level.
 * <p>
 * Updates visual when pet's health changes
 * If the pet's health falls below 25%, the indicator is displayed in red, otherwise it is green
 *
 * @author aliciaserdyuk
 * @since 03/26/25
 * @see PetObserver
 * @see Pet
 */
public class HealthIndicator implements PetObserver {
    /**
     * Updates the visual state of the health indicator based on the pet's current health level.
     *
     * @param pet the Pet whose happiness value has changed
     */
    public void update(Pet pet){
        // update health indicator meter somehow?

        if (pet.getHealth() < 25){
            //turn hunger bar red
        }
        else{
            // green
        }
    }
}
