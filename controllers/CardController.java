package controllers;

import cards.*;
import cards.status.Wound;
import game.Camera;
import game.Game;
import sprites.player.EnergyUI;
import utils.Pair;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * The CardController class is a singleton class that controls the deck, hand, and discard pile of the game.
 */
public class CardController {
    /**
     * The instance of the CardController class.
     */
    private static CardController instance;
    /**
     * Reset the instance of the CardController class.
     */
    public static void reset() {
        instance = null;
    }
    /**
     * Get the instance of the CardController class.
     * @return the instance of the CardController class
     */
    public static CardController getInstance() {
        if (instance == null) {
            instance = new CardController();
        }
        return instance;
    }
    /**
     * The deck of the game.
     */
    private Deck deck;
    /**
     * The hand of the game.
     */
    private Hand hand;
    /**
     * The discard pile of the game.
     */
    private DiscardPile discardPile;
    /**
     * The card picker of the game, will be reassigned on each new choice.
     */
    private CardPicker cardPicker;
    /**
     * Get the card picker of the game.
     * @return the card picker of the game
     */
    public CardPicker getCardPicker() {
        return cardPicker;
    }
    /**
     * Reset the card picker with new choices.
     */
    public void resetCardPicker() {
        cardPicker = new CardPicker();
    }
    /**
     * Get the deck of the game.
     * @return the deck of the game
     */
    public Deck getDeck() {
        return deck;
    }
    /**
     * Get the hand of the game.
     * @return the hand of the game
     */
    public Hand getHand() {
        return hand;
    }
    /**
     * Get the discard pile of the game.
     * @return the discard pile of the game
     */
    public DiscardPile getDiscardPile() {
        return discardPile;
    }
    /**
     * Private constructor for CardController.
     */
    private CardController() {
        deck = new Deck();
        hand = new Hand();
        discardPile = new DiscardPile();
        cardPicker = new CardPicker();

        playCooldownUI = new EnergyUI(
                0,
                0,
                playCooldown);
        drawCooldownUI = new EnergyUI(
                0,
                0,
                (int) (drawCooldown * Game.TPS), 1);
        shuffleCooldownUI = new EnergyUI(
                0,
                0,
                0, 1);
    }
    /**
     * Draw a card from the deck and add it to the hand.
     */
    public void drawCard() {
        Card card = deck.drawCard();
        if (card != null)hand.addCard(card);
    }
    /**
     * The buffer for cards to be removed on next tick.
     */
    private List<Card> removalBuffer = new ArrayList<>();
    /**
     * Schedule a card for removal.
     * @param card the card to be removed
     */
    public void scheduleRemoval(Card card) {
        removalBuffer.add(card);
    }

    /**
     * The cooldown for shuffling the discard pile into the deck.
     */
    private double shuffleCooldown = 8.0;
    /**
     * The tracker for the shuffle cooldown.
     */
    private int shuffleCooldownTimer = 0;
    /**
     * The cooldown for playing a card in ticks.
     */
    private int playCooldown = 90;

    /**
     * Get the cooldown time between card played
     * @return the cooldown in seconds
     */
    public int getPlayCooldown() {
        return playCooldown;
    }

    /**
     * Sets the cooldown between cards played
     * @param cooldown the cooldown in ticks
     */
    public void setPlayCooldown(int cooldown) {
        playCooldown = cooldown;
    }
    /**
     * The tracker for the play cooldown.
     */
    private int playCooldownTimer = 0;
    /**
     * The cooldown for drawing a card.
     */
    private double drawCooldown = 3.0;
    /**
     * The tracker for the draw cooldown.
     */
    private int drawCooldownTimer = 0;
    /**
     * The maximum hand size.
     */
    private int maxHandSize = 5;
    /**
     * The UI for the play cooldown.
     */
    private EnergyUI playCooldownUI;
    /**
     * The UI for the draw cooldown.
     */
    private EnergyUI drawCooldownUI;
    /**
     * The UI for the shuffle cooldown.
     */
    private EnergyUI shuffleCooldownUI;


    public void redistributeDeck() {
        deck.addCards(discardPile.clear());
    }

    /**
     * Tick the card controller.
     */
    public void tick() {
        List<Pair<Integer, Integer>> clicks = CardInputController.getInstance().getClicksBuffer();
        hand.tick();
        playCooldownTimer++;
        if (playCooldownUI.getXPosition() != CameraController.getInstance().getCamera().getWidth() / 2 - playCooldownUI.getWidth() / 2
            || playCooldownUI.getYPosition() != CameraController.getInstance().getCamera().getHeight() * 4 / 5 - (int) (playCooldownUI.getHeight() * 1.3)) {
            playCooldownUI.setXPosition(CameraController.getInstance().getCamera().getWidth() / 2 - playCooldownUI.getWidth() / 2);
            playCooldownUI.setYPosition(CameraController.getInstance().getCamera().getHeight() * 4 / 5 - (int) (playCooldownUI.getHeight() * 1.3));
        }
        int xDrawUI = 20;
        int yDrawUI = CameraController.getInstance().getCamera().getHeight() - 50;
        int xShuffleUI = CameraController.getInstance().getCamera().getWidth() - 20 - shuffleCooldownUI.getWidth();
        int yShuffleUI = CameraController.getInstance().getCamera().getHeight() - 50;
        if (drawCooldownUI.getXPosition() != xDrawUI || drawCooldownUI.getYPosition() != xDrawUI) {
            drawCooldownUI.setXPosition(xDrawUI);
            drawCooldownUI.setYPosition(yDrawUI);
        }
        if (shuffleCooldownUI.getXPosition() != xShuffleUI || shuffleCooldownUI.getYPosition() != yShuffleUI) {
            shuffleCooldownUI.setXPosition(xShuffleUI);
            shuffleCooldownUI.setYPosition(yShuffleUI);
        }
        playCooldownUI.tick();
        if (drawCooldownTimer < drawCooldown * Game.TPS && hand.getCards().size() < maxHandSize && deck.size() > 0) {

            if (drawCooldownTimer == 0) drawCooldownUI = new EnergyUI(
                    xDrawUI, yDrawUI,
                    (int) (drawCooldown * Game.TPS), 1);

            drawCooldownTimer++;
            drawCooldownUI.tick();
        }
        else {
            if (deck.size() == 0) {

                shuffleCooldownUI.tick();
                if (hand.getCards().size() < maxHandSize) {
                    shuffleCooldownTimer++;
                }
                if (shuffleCooldownTimer > shuffleCooldown * Game.TPS) {
                    System.out.println("Shuffling discard pile into deck");
                    redistributeDeck();
                    shuffleCooldownTimer = 0;
                }
            }
            if (deck.size() > 0 && hand.getCards().size() < maxHandSize) {
                 drawCard();
                drawCooldownTimer = 0;
            }
        }
        if (shuffleCooldownTimer == 1) {
            shuffleCooldownUI = new EnergyUI(
                    xShuffleUI, yShuffleUI,
                    (int) (shuffleCooldown * Game.TPS), 1);
        }
        for (Pair<Integer, Integer> click : clicks) {
            int x = click.getFirst();
            int y = click.getSecond();
            for (Card card : hand.getCards()) {
                if (playCooldownTimer >= playCooldown && card.getClickBox().containsPoint(new Point2D.Double(x, y))) {
                    if (card instanceof Wound) break;
                    playCooldownTimer = 0;
                    playCooldownUI = new EnergyUI(
                            CameraController.getInstance().getCamera().getWidth() / 2 - playCooldownUI.getWidth() / 2,
                            CameraController.getInstance().getCamera().getHeight() * 4 / 5 - (int) (playCooldownUI.getHeight() * 1.3),
                            playCooldown);
                    System.out.println("Card clicked");
                    card.use();
                    scheduleRemoval(card);
                }
            }
        }
        for (Card card : removalBuffer) {

            boolean removed = hand.removeCard(card);
            if (removed) discardPile.addCard(card);
        }
        removalBuffer.clear();
    }

    /**
     * Draw the cards interface.
     * @param g the graphics
     * @param camera the camera
     */
    public void drawGraphics(Graphics g, Camera camera) {
        hand.drawHand(g, camera);
        deck.drawGraphics(g, camera);
        discardPile.drawGraphics(g, camera);
        playCooldownUI.draw(g, camera);
        drawCooldownUI.draw(g, camera);
        shuffleCooldownUI.draw(g, camera);
    }
}
