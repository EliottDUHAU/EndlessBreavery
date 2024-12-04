package cards.attacks;

import cards.Card;
import controllers.PlayerController;
import sprites.Sprite;
import sprites.cards.SlashCardSprite;

/**
 * The slash card
 */
public class Slash extends Card {
    /**
     * The sprite of the card
     */
    private Sprite sprite = new SlashCardSprite();
    /**
     * Use the slash attack method of the player
     */
    @Override
    public void use() {
        PlayerController.getInstance().getPlayer().slash();
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
