package controllers;

import game.Game;

/**
 * Controller class for the game
 */
public class GameController {
    /**
     * The singleton instance of the GameController
     */
    private static GameController instance;
    /**
     * The game instance
     */
    private Game game;
    /**
     * Private constructor for the GameController
     */
    private GameController() {
        game = new Game();
    }
    /**
     * Get the game instance
     * @return the game instance
     */
    public Game getGame() {
        return game;
    }
    /**
     * Get the singleton instance of the GameController
     * @return the singleton instance of the GameController
     */
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }
    /**
     * Reset the singleton instance of the GameController
     */
    public static void reset() {
        instance = null;
    }
}
