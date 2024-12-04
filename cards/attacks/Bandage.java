package cards.attacks;

import cards.Card;
import controllers.PlayerController;
import sprites.Sprite;
import sprites.cards.BandageCardSprite;

/**
 * A card that heals the player
 */
public class Bandage extends Card {
    /**
     * The sprite of the card
     */
    private BandageCardSprite sprite = new BandageCardSprite();
    /**
     * Use the slash attack method of the player
     */
    @Override
    public void use() {
        PlayerController.getInstance().getPlayer().heal(10);
    }
    /**
     * Get the sprite of the card
     * @return the sprite
     */
    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
