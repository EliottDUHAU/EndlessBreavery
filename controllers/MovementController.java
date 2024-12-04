package controllers;

import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Controller for the movement of the player.
 */
public class MovementController implements KeyListener {
    /**
     * The singleton instance of the MovementController
     */
    private static MovementController instance;
    /**
     * Get the instance of the MovementController class.
     * @return the instance of the MovementController class
     */
    public static MovementController getInstance() {
        if (instance == null) {
            instance = new MovementController();
        }
        return instance;
    }
    /**
     * Private constructor for the MovementController.
     */
    private MovementController() {
    }

    /**
     * The list of directions the player is moving in.
     */
    private final List<Directions> directions = new ArrayList<>();
    /**
     * The direction the player is facing, either LEFT or RIGHT.
     */
    private Directions facing = Directions.RIGHT;
    /**
     * Whether the player is using QWERTY or AZERTY keybinds.
     */
    private boolean isQwerty = false;
    /**
     * Get the list of directions the player is moving in.
     * @return the list of directions the player is moving in.
     */
    public List<Directions> getDirections() {
        return directions;
    }
    /**
     * Get the direction the player is facing.
     * @return Direction.LEFT or Direction.RIGHT.
     */
    public Directions getFacing() {
        return facing;
    }

    /**
     * Toggle between QWERTY and AZERTY keybindings.
     */
    public void toggleKeybinding() {
        isQwerty = !isQwerty;
    }
    /**
     * Dummy method to implement KeyListener
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }
    /**
     * Method to handle key pressed events
     * @param e the key event
     */
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (!directions.contains(Directions.UP)) directions.add(Directions.UP);
                break;
            case KeyEvent.VK_DOWN:
                if (!directions.contains(Directions.DOWN)) directions.add(Directions.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                if (!directions.contains(Directions.LEFT)) directions.add(Directions.LEFT);
                facing = Directions.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                if (!directions.contains(Directions.RIGHT)) directions.add(Directions.RIGHT);
                facing = Directions.RIGHT;
                break;
            case KeyEvent.VK_W:
                if (isQwerty && !directions.contains(Directions.UP)) directions.add(Directions.UP);
                break;
            case KeyEvent.VK_A:
                if (isQwerty && !directions.contains(Directions.LEFT)) directions.add(Directions.LEFT);
                facing = Directions.LEFT;
                break;
            case KeyEvent.VK_D:
                if (!directions.contains(Directions.RIGHT)) directions.add(Directions.RIGHT);
                facing = Directions.RIGHT;
                break;
            case KeyEvent.VK_S:
                if (!directions.contains(Directions.DOWN)) directions.add(Directions.DOWN);
                break;
            case KeyEvent.VK_Z:
                if (!isQwerty && !directions.contains(Directions.UP)) directions.add(Directions.UP);
                break;
            case KeyEvent.VK_Q:
                if (!isQwerty && !directions.contains(Directions.LEFT)) directions.add(Directions.LEFT);
                facing = Directions.LEFT;
                break;
        }
    }
    /**
     * Method to handle key released events
     * @param e the key event
     */
    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                directions.remove(Directions.UP);
                break;
            case KeyEvent.VK_DOWN:
                directions.remove(Directions.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                directions.remove(Directions.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                directions.remove(Directions.RIGHT);
                break;
            case KeyEvent.VK_W:
                if (isQwerty) directions.remove(Directions.UP);
                break;
            case KeyEvent.VK_A:
                if (isQwerty) directions.remove(Directions.LEFT);
                break;
            case KeyEvent.VK_D:
                directions.remove(Directions.RIGHT);
                break;
            case KeyEvent.VK_S:
                directions.remove(Directions.DOWN);
                break;
            case KeyEvent.VK_Z:
                if (!isQwerty) directions.remove(Directions.UP);
                break;
            case KeyEvent.VK_Q:
                if (!isQwerty) directions.remove(Directions.LEFT);
                break;
        }
    }
}