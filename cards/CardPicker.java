package cards;

import cards.attacks.*;
import controllers.CardInputController;
import game.Camera;
import utils.Pair;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class to pick a card from a list of 3 random cards between waves
 */
public class CardPicker {
    /**
     * Random object to generate random cards
     */
    private static final Random random = new Random();
    /**
     * Enum to represent the different cards
     */
    private enum Cards {
        BANDAGE, SLASH, FOCUS, GREED, RUSH
    }

    /**
     * Factory method to create a random card
     * @return a random card
     */
    private static Card newRandomCard(){
        Cards cardEnum  = Cards.values()[random.nextInt(Cards.values().length)];
        Card card = switch (cardEnum) {
            case RUSH -> new Rush();
            case GREED -> new Greed();
            case FOCUS -> new Focus();
            case BANDAGE -> new Bandage();
            default -> new Slash();
        };
        return card;
    }

    /**
     * Get the chosen card from the player
     * @return the chosen card, null if no card has been chosen yet
     */
    public Card getChosenCard() {
        List<Pair<Integer, Integer>> clicks = CardInputController.getInstance().getClicksBuffer();
        Card card = null;
        for (Pair<Integer, Integer> click : clicks) {
            Point2D.Double clickPoint = new Point2D.Double(click.getFirst(), click.getSecond());
            if (cards.get(0).getClickBox().containsPoint(clickPoint)) {
                card = cards.get(0);
            } else if (cards.get(1).getClickBox().containsPoint(clickPoint)) {
                card = cards.get(1);
            } else if (cards.get(2).getClickBox().containsPoint(clickPoint)) {
                card = cards.get(2);
            }
        }
        return card;
    }


    /**
     * The cards to choose from
     */
    private final List<Card> cards;
    /**
     * Create a new card picker
     */
    public CardPicker() {
        cards = Arrays.asList(newRandomCard(), newRandomCard(), newRandomCard());
    }
    /**
     * Draw the card picker
     * @param g the graphics object to draw on
     * @param camera the camera to draw with
     */
    public void drawGraphics(Graphics g, Camera camera) {
        String text = "Choose a card to your deck !";
        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, camera.getWidth(), camera.getHeight());
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        int x = (camera.getWidth() - textWidth) / 2;
        int y = (camera.getHeight() - textHeight) / 3 + fm.getAscent();
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
        Card card1 = cards.get(0);
        x = (camera.getWidth() - card1.getSprite().getWidth()) / 2;
        y = camera.getHeight() / 2 ;
        card1.setXPosition(x);
        card1.setYPosition(y);
        Card card2 = cards.get(1);
        card2.setXPosition(x - card1.getSprite().getWidth() * 4 / 3);
        card2.setYPosition(y);
        Card card3 = cards.get(2);
        card3.setXPosition(x + card1.getSprite().getWidth() * 4 / 3);
        card3.setYPosition(y);
        card1.getSprite().draw(g, camera);
        card2.getSprite().draw(g, camera);
        card3.getSprite().draw(g, camera);
    }
}
