package controllers;

import entities.Player;

/**
 * Controller class for the player
 */
public class PlayerController {
    /**
     * The singleton instance of the PlayerController
     */
    private static PlayerController instance;


    /**
     * The player instance
     */
    private Player player;
    /**
     * Private constructor for the PlayerController
     */
    private PlayerController() {
        player = new Player(0, 0);
    }
    /**
     * Get the player instance
     * @return the player instance
     */
    public Player getPlayer() {
        return player;
    }
    /**
     * Get the singleton instance of the PlayerController
     * @return the singleton instance of the PlayerController
     */
    public static PlayerController getInstance() {
        if (instance == null) {
            instance = new PlayerController();
        }
        return instance;
    }
    /**
     * Reset the singleton instance of the PlayerController
     */
    public static void reset() {
        instance = null;
    }
}
