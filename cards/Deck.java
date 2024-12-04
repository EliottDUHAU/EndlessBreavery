package cards;

import cards.attacks.Bandage;
import cards.attacks.Rush;
import cards.attacks.Slash;
import cards.status.Wound;
import controllers.CardController;
import game.Camera;
import sprites.cards.BackSideSprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for a deck of cards
 */
public class Deck {
    /**
     * The cards in the deck
     */
    private List<Card> cards = new ArrayList<>();
    /**
     * The sprite for the back side of the card
     */
    private BackSideSprite backSideSprite = new BackSideSprite(0,0);
    /**
     * Draw the card on top of the deck
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.removeFirst();
    }
    /**
     * Draw a number of cards from the deck
     * @param amount the number of cards to draw
     */
    public List<Card> drawCards(int amount) {
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            drawnCards.add(drawCard());
        }
        return drawnCards;
    }
    /**
     * Shuffles a card to the deck
     * @param card the card to add
     */
    public void addCard(Card card) {
        cards.add(card);
        shuffle();
    }
    /**
     * Adds a card to the deck
     * @param card the card to add
     * @param shuffle whether to shuffle the deck after adding the card at the bottom
     */
    public void addCard(Card card, boolean shuffle) {
        cards.add(card);
        if (shuffle) shuffle();
    }
    /**
     * Shuffles a list of cards into the deck
     * @param cards the list of cards to add
     */
    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
        shuffle();
    }
    /**
     * Adds a list of cards to the deck
     * @param cards the list of cards to add
     * @param shuffle whether to shuffle the deck after adding the cards at the bottom
     */
    public void addCards(List<Card> cards, boolean shuffle) {
        this.cards.addAll(cards);
        if (shuffle) shuffle();
    }
    /**
     * Shuffles the deck
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }
    /**
     * Get the size of the deck
     * @return the size of the deck
     */
    public int size() {
        return cards.size();
    }

    /**
     * Constructor for the deck
     */
    public Deck() {
        for (int i = 0; i < 6; i++) {
            addCard(new Slash());
        }
        addCard(new Bandage());
        shuffle();
    }
    /**
     * Draw the deck UI
     * @param g the graphics object
     * @param camera the camera
     */
    public void drawGraphics(Graphics g, Camera camera) {
        int x = backSideSprite.getWidth() / 3;
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
