/**
 * A UI component that visually represents the pet's happiness level.
 * <p>
 * Updates visual when pet's happiness changes
 * If the pet's happiness falls below 25%, the meter is displayed in red, otherwise it is green
 *
 * @author aliciaserdyuk
 * @since 03/26/25
 * @see PetObserver
 * @see Pet
 */
public class HappinessMeter implements PetObserver {
    /**
     * Updates the visual state of the happiness meter based on the pet's current happiness level.
     *
     * @param pet the Pet whose happiness value has changed
     */
    public void update(Pet pet){
        // update happiness meter somehow?

        if (pet.getHappiness() < 25){
            //turn hunger bar red
        }
        else{
            // green
        }

    }
}
