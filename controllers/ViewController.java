package controllers;

import game.Game;
import view.GamePanel;
import view.Window;

/**
 * Controller class for the view
 */
public class ViewController {
    /**
     * The singleton instance of the ViewController
     */
    private static ViewController instance;
    /**
     * The window instance
     */
    private Window window;
    /**
     * The game panel instance
     */
    private GamePanel gamePanel;
    /**
     * Private constructor for the ViewController
     */
    private ViewController() {
        gamePanel = new GamePanel();
        window = new Window();
    }
    /**
     * Get the GamePanel instance
     * @return the GamePanel instance
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    /**
     * Get the singleton instance of the ViewController
     * @return the singleton instance of the ViewController
     */
    public static ViewController getInstance() {
        if (instance == null) {
            instance = new ViewController();
        }
        return instance;
    }
}