package cards;

import controllers.CardController;
import entities.hitboxes.PolygoneHitbox;
import sprites.Sprite;
import utils.Pair;

/**
 * Represents a card in the game.
 */
public abstract class Card {
    /**
     * The x position of the card.
     */
    private int xPosition = 0;
    /**
     * The y position of the card.
     */
    private int yPosition = 0;
    /**
     * Set the x position of the card.
      * @param xPosition the x position
     */
    public void setXPosition(int xPosition) {
        if (this.clickBox == null) {
            initClickBox();
        }
        this.xPosition = xPosition;
        this.getSprite().setXPosition(xPosition);
        this.clickBox.setXPosition(xPosition);
    }
    /**
     * Set the y position of the card.
     * @param yPosition the y position
     */
    public void setYPosition(int yPosition) {
        if (this.clickBox == null) {
            initClickBox();
        }
        this.yPosition = yPosition;
        this.getSprite().setYPosition(yPosition);
        this.clickBox.setYPosition(yPosition);
    }
    /**
     * The click box of the card.
     */
    private PolygoneHitbox clickBox;
    /**
     * Gets the click box of the card.
     * @return the click box
     */
    public PolygoneHitbox getClickBox() {
        if (clickBox == null) initClickBox();
        return clickBox;
    }
    /**
     * Use the card and discard it.
     */
    public abstract void use();
    /**
     * Get the sprite of the card.
     * @return the sprite
     */
    public abstract Sprite getSprite();
    /**
     * Initialize the hitbox of the card.
     */
    private void initClickBox () {
        if (clickBox == null) clickBox = new PolygoneHitbox(
            new Pair<>(xPosition, yPosition),
            new Pair<>(xPosition, yPosition + getSprite().getHeight()),
            new Pair<>(xPosition + getSprite().getWidth(), yPosition + getSprite().getHeight()),
            new Pair<>(xPosition + getSprite().getWidth(), yPosition));
    }
}
