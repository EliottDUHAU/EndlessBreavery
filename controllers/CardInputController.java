package controllers;

import cards.Card;
import utils.Pair;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for card click inputs
 */
public class CardInputController implements MouseListener {
    /**
     * The singleton instance of the controller
     */
    private static CardInputController instance;
    /**
     * Get the singleton instance of the controller
     * @return the singleton instance of the controller
     */
    public static CardInputController getInstance() {
        if (instance == null) {
            instance = new CardInputController();
        }
        return instance;
    }
    /**
     * Private constructor for the controller
     */
    private CardInputController() {
    }
    /**
     * The buffer for clicks
     */
    private List<Pair<Integer, Integer>> clicksBuffer = new ArrayList<>();
    /**
     * Inspects the clicks buffer and flushes it
     * @return the clicks buffer
     */
    public List<Pair<Integer, Integer>> getClicksBuffer() {
        List<Pair<Integer, Integer>> values = clicksBuffer;
        clicksBuffer = new ArrayList<>();
        return values;
    }
    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        clicksBuffer.add(new Pair<>(e.getX(), e.getY()));
    }
    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // Not used
    }
    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // Not used
    }
    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // Not used
    }
    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // Not used
    }
}
