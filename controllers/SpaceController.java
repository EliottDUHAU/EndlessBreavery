package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Controller class for the space key
 */
public class SpaceController implements KeyListener {
    /**
     * The boolean that represents if the space key is pressed
     */
    private boolean space = false;
    /**
     * The singleton instance of the SpaceController
     */
    public boolean isSpacePressed() {
        return space;
    }
    /**
     * Handle the key typed event
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }
    /**
     * Handle the key pressed event
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = true;
        }
    }
    /**
     * Handle the key released event
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = false;
        }
    }
}
