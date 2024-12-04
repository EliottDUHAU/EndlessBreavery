package cards;

import controllers.CameraController;
import game.Camera;
import sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for a hand of cards
 */
public class Hand {
    /**
     * The list of cards in the hand
     */
    private List<Card> cards = new ArrayList<>();
    /**
     * Get the list of cards in the hand
     * @return the list of cards in the hand
     */
    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
    /**
     * Add a card to the hand
     * @param card the card to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }
    /**
     * Remove a card from the hand
     * @param card the card to remove
     */
    public boolean removeCard(Card card) {
        return cards.remove(card);
    }
    /**
     * Remove a card from the hand
     * @param index the index of the card to remove
     * @return the removed card
     */
    public Card removeCard(int index) {
        return cards.remove(index);
    }
    public int width() {
        int width = 0;
        for (Card card : cards) {
            width += card.getSprite().getWidth();
        }
        return width;
    }
    /**
     * Draw the hand
     * @param g the graphics object
     * @param camera the camera object
     */
    public void drawHand(Graphics g, Camera camera) {
        for (Card card : new ArrayList<>(cards)) {
            Sprite sprite = card.getSprite();
            sprite.draw(g, camera);
        }
    }
    /**
     * Game update the hand
     */
    public void tick() {
        int width = width();
        Camera camera = CameraController.getInstance().getCamera();
        int offset = (camera.getWidth() - width) / 2;
        int height = camera.getHeight() / 5;
        for (Card card : cards) {
            Sprite sprite = card.getSprite();
            card.getClickBox().setXPosition(offset);
            card.getClickBox().setYPosition(camera.getHeight() - height);
            sprite.setXPosition(offset);
            sprite.setYPosition(camera.getHeight() - height);
            offset += sprite.getWidth();
        }
    }
    /**
     * Debug draw the hand
     * @param g the graphics object
     * @param camera the camera object
     */
    public void debugDraw(Graphics g, Camera camera) {
        for (Card card : cards) {
            card.getClickBox().debugDraw(g, camera);
        }
    }
}
