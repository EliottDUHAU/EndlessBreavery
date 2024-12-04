package cards;

import game.Camera;
import sprites.cards.BackSideSprite;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Class for a discard pile.
 */
public class DiscardPile {
    /**
     * The sprite for the back side of the card.
     */
    private BackSideSprite backSideSprite = new BackSideSprite(0,0);
    /**
     * The list of cards in the discard pile.
     */
    private List<Card> cards = new ArrayList<>();
    /**
     * Get the list of cards in the discard pile.
     * @return the list of cards in the discard pile
     */
    private List<Card> getCards() {
        return cards;
    }
    /**
     * Add a card to the discard pile.
     * @param card the card to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }
    /**
     * Remove a card from the discard pile.
     * @param card the card to remove
     */
    private void removeCard(Card card) {
        cards.remove(card);
    }
    /**
     * Clear the discard pile.
     */
    public List<Card> clear() {
        List<Card> removedCards = new ArrayList<>(cards);
        cards.clear();
        return removedCards;
    }
    /**
     * Get the size of the discard pile.
     * @return the size of the discard pile
     */
    public int size() {
        return cards.size();
    }

    /**
     * Draw the discard pile UI.
     * @param g the graphics object
     * @param camera the camera
     */
    public void drawGraphics(Graphics g, Camera camera) {
        int x = camera.getWidth() - backSideSprite.getWidth() * 4 / 3;
        int y = camera.getHeight() - backSideSprite.getHeight() * 4 / 3;
        backSideSprite.setXPosition(x);
        backSideSprite.setYPosition(y);
        backSideSprite.draw(g, camera);
        String text = "" + size();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        x = x + (backSideSprite.getWidth() - textWidth) / 2;
        y = y + ( backSideSprite.getHeight() - textHeight) / 2 + fm.getAscent();
        // Draw black outline
        int outline = 2;
        g.setColor(Color.BLACK);
        g.drawString(text, x - outline, y - outline);
        g.drawString(text, x - outline, y + outline);
        g.drawString(text, x + outline, y - outline);
        g.drawString(text, x + outline, y + outline);

        // Draw main white text
        g.setColor(Color.WHITE);
        g.drawString(text, x, y);
    }
}
