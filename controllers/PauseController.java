package controllers;

import game.Game;
import sprites.menus.PauseUI;

import java.awt.event.*;

/**
 * Controller class for pausing the game
 */
public class PauseController implements KeyListener, MouseListener {
    /**
     * The singleton instance of the PauseController
     */
    private static PauseController instance;
    /**
     * The pause screen overlay
     */
    private PauseUI pauseUI;
    /**
     * Get the singleton instance of the PauseController
     * @return the singleton instance of the PauseController
     */
    public static PauseController getInstance() {
        if (instance == null) {
            instance = new PauseController();
        }
        return instance;
    }
    /**
     * Get the pause screen overlay
     * @return the pause screen overlay
     */
    public PauseUI getPauseUI() {
        return pauseUI;
    }
    /**
     * Create a new PauseController
     */
    private PauseController() {
        pauseUI = new PauseUI();
    }
    /**
     * The boolean that represents if the escape key is pressed
     */
    private boolean escape = false;
    /**
     * Get the boolean that represents if the escape key is pressed
     * @return the boolean that represents if the escape key is pressed
     */
    public boolean getEscape() {
        return escape;
    }
    /**
     * Handle the key typed event
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
    }
    /**
     * Handle the key pressed event
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            escape = true;
        }
    }
    /**
     * Handle the key released event
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            escape = false;
        }
    }
    /**
     * Handle the mouse clicked event
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        pauseUI.handleMouseClick(e.getX(), e.getY());
        pauseUI.handleMousePressed(e.getX(), e.getY());
    }
    /**
     * Handle the mouse pressed event
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }
    /**
     * Handle the mouse released event
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    /**
     * Handle the mouse entered event
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    /**
     * Handle the mouse exited event
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
