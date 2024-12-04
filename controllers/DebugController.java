package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Controller class for debugging
 */
public class DebugController implements KeyListener {
    /**
     * The boolean that represents if the H key is pressed
     */
    private boolean hPressed = false;
    /**
     * The boolean that represents if the C key is pressed
     */
    private boolean cPressed = false;
    /**
     * The singleton instance of the DebugController
     */
    private static DebugController instance;

    /**
     * Private constructor for the DebugController
     */
    private DebugController() {
    }
    /**
     * Get the singleton instance of the DebugController
     *
     * @return the singleton instance of the DebugController
     */
    public static DebugController getInstance() {
        if (instance == null) {
            instance = new DebugController();
        }
        return instance;
    }
    /**
     * Get the boolean that represents if the H key is pressed
     *
     * @return the boolean that represents if the H key is pressed
     */
    public boolean getHPressed() {
        return hPressed;
    }
    /**
     * Get the boolean that represents if the C key is pressed
     *
     * @return the boolean that represents if the C key is pressed
     */
    public boolean getCPressed() {
        return cPressed;
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
     *
     * @param e the key event
     */
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_H:
                hPressed = true;
                break;
            case KeyEvent.VK_C:
                cPressed = true;
                break;
        }
    }
    /**
     * Handle the key released event
     *
     * @param e the key event
     */
    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_H:
                hPressed = false;
                break;
            case KeyEvent.VK_C:
                cPressed = false;
                break;
        }
    }
}
