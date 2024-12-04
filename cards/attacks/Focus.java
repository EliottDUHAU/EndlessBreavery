package cards.attacks;

import cards.Card;
import controllers.BuffController;
import game.Game;
import game.buffs.PlayCooldownBuff;
import sprites.Sprite;
import sprites.cards.FocusCardSprite;

/**
 * The Focus card, allows the player to play cards faster.
 */
public class Focus extends Card {
    /**
     * The sprite of the card.
     */
    private Sprite cardSprite = new FocusCardSprite();

    /**
     * The action of the card, gives the player a buff that reduces the cooldown of cards by 50% for 5 seconds.
     */
    @Override
    public void use() {
        PlayCooldownBuff buff = new PlayCooldownBuff(5 * Game.TPS,0.5);
        BuffController.getInstance().addBuff(buff);
    }
    /**
     * Get the sprite of the card.
     * @return the sprite of the card
     */
    @Override
    public Sprite getSprite() {
        return cardSprite;
    }
}
