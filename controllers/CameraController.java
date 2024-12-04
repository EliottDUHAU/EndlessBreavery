package controllers;

import game.Camera;

/**
 * Controller class for the camera
 */
public class CameraController {
    /**
     * The singleton instance of the CameraController
     */
    private static CameraController instance;
    /**
     * The camera instance for the window
     */
    private Camera camera = new Camera(0, 0);
    /**
     * Get the camera instance
     * @return the camera instance
     */
    public Camera getCamera() {
        return camera;
    }
    /**
     * Private constructor for the CameraController
     */
    private CameraController() {
    }
    /**
     * Get the singleton instance of the CameraController
     * @return the singleton instance of the CameraController
     */
    public static CameraController getInstance() {
        if (instance == null) {
            instance = new CameraController();
        }
        return instance;
    }
}
