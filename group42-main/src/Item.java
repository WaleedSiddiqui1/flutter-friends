/**
 * This interface represents a general item that can be used on a pet.
 * Implemented by Food and Gift classes.
 * @author Waleed Siddiqui
 * @see Food
 * @see Gift
 */
public interface Item {
    /**
     * @return the name of the item
     */
    String getName();

    /**
     * @return the sprite used by this item
     */
    String getSprite();

    /**
     * Applies this item’s effect to the pet.
     *
     * @param pet the pet to apply the effect to
     */
    void applyEffect(Pet pet);
}