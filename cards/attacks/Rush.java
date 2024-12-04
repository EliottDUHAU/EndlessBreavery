package cards.attacks;

import cards.Card;
import controllers.PlayerController;
import sprites.Sprite;
import sprites.cards.RushCardSprite;
/**
 * The Rush card, allows the player to dash in a direction dealing damage while immune to damage
 */
public class Rush extends Card {
    /**
     * The sprite for the Rush card
     */
    private final Sprite sprite = new RushCardSprite();
    /**
     * Use the Rush card
     */
    @Override
    public void use() {
        PlayerController.getInstance().getPlayer().rush();
    }
    /**
     * Get the sprite for the Rush card
     * @return the sprite for the Rush card
     */
    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
