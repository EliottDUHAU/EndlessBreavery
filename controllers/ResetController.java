package controllers;

import game.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Controller class for the resetting the game
 */
public class ResetController implements MouseListener {
    /**
     * The singleton instance of the ResetController
     */
    private static ResetController instance;
    /**
     * Get the singleton instance of the ResetController
     * @return the singleton instance of the ResetController
     */
    public static ResetController getInstance() {
        if (instance == null) {
            instance = new ResetController();
        }
        return instance;
    }
    /**
     * Private constructor for the ResetController
     */
    private ResetController() {
    }
    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (PlayerController.getInstance().getPlayer().isAlive()) return;
        CardController.reset();
        EnemyController.reset();
        GameController.reset();
        PlayerController.reset();
        BuffController.reset();
        PlayerController.reset();
        Game game = GameController.getInstance().getGame();
        game.addObserver(ViewController.getInstance().getGamePanel());
        new Thread(new Runnable() {
            public void run() {
                game.run();
            }
        }).start();
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
