package cards.status;

import cards.Card;
import entities.hitboxes.Hitbox;
import sprites.Sprite;
import sprites.cards.WoundCardSprite;

public class Wound extends Card {
    private Sprite sprite = new WoundCardSprite();

    @Override
    public void use() {
        // Do nothing
    }

    /**
     * Get the sprite of the card.
     *
     * @return the sprite
     */
    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
