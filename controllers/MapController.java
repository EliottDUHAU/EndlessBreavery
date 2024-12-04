package controllers;

import map.Map;

/**
 * Controller class for the map
 */
public class MapController {
    /**
     * The singleton instance of the MapController.
     */
    private static MapController instance = new MapController();
    /**
     * Returns the singleton instance of the MapController.
     * @return the singleton instance of the MapController
     */
    public static MapController getInstance() {
        return instance;
    }
    /**
     * The map instance.
     */
    private Map map;
    /**
     * Private constructor for the MapController.
     */
    private MapController() {
        this.map = new Map();
    }
    /**
     * Get the map instance.
     * @return the map instance
     */
    public Map getMap() {
        return map;
    }
}
