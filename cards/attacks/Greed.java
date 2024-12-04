package cards.attacks;

import cards.Card;
import controllers.CardController;
import sprites.Sprite;
import sprites.cards.BackSideSprite;
import sprites.cards.GreedCardSprite;

/**
 * The Greed card class, draws 3 cards and shuffles the deck if it is empty
 */
public class Greed extends Card {
    /**
     * Create a new Greed card
     */
    private Sprite sprite = new GreedCardSprite();

    /**
     * The action of the Greed card, draws 3 cards and shuffles the deck if it is empty
     */
    @Override
    public void use() {
        CardController cardController = CardController.getInstance();
        for (int i = 0; i < 3; i++) {
            if  (cardController.getDeck().size() == 0) {
                cardController.redistributeDeck();
                if (cardController.getDeck().size() == 0) {
                    return;
                }
            }
            cardController.drawCard();
        }
    }
    /**
     * Get the sprite of the Greed card
     * @return the sprite of the Greed card
     */
    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
