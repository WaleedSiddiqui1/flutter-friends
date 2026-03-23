/**
 * A UI component that visually represents the pet's hunger level.
 * <p>
 * Updates visual when pet's hunger changes
 * If the pet's hunger falls below 25%, the bar is displayed in red, otherwise it is green
 *
 * @author aliciaserdyuk
 * @since 03/26/25
 * @see PetObserver
 * @see Pet
 */
public class HungerBar implements PetObserver {
    /**
     * Updates the visual state of the hunger bar based on the pet's current hunger level.
     *
     * @param pet the Pet whose happiness value has changed
     */
    public void update(Pet pet){
        // update hunger meter somehow?

        if (pet.getFullness() < 25){
            //turn hunger bar red
        }
        else{
            // green
        }
    }
}
