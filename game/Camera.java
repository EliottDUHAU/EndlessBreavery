package game;

import controllers.ViewController;
import view.GamePanel;

/**
 * The camera class
 */
public class Camera {
    /**
     * The x position of the camera
     */
    private int x;
    /**
     * The y position of the camera
     */
    private int y;
    /**
     * The width of the camera
     * initialized to the game default width
     */
    private int width = GamePanel.GAME_WIDTH;
    /**
     * The height of the camera
     * initialized to the game default height
     */
    private int height = GamePanel.GAME_HEIGHT;
    /**
     * Get the height of the camera
     * @return the height of the camera
     */
    public int getHeight() {
        height = ViewController.getInstance().getGamePanel().getHeight();
        return height;
    }
    /**
     * Get the width of the camera
     * @return the width of the camera
     */
    public int getWidth() {
        width = ViewController.getInstance().getGamePanel().getWidth();
        return width;
    }
    /**
     * Create a new camera
     * @param x x position
     * @param y y position
     */
    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Get the x position of the camera
     * @return the x position of the camera
     */
    public int getX() {
        return x;
    }
    /**
     * Get the y position of the camera
     * @return the y position of the camera
     */
    public int getY() {
        return y;
    }
    /**
     * Set the x position of the camera
     * @param x x position
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Set the y position of the camera
     * @param y y position
     */
    public void setY(int y) {
        this.y = y;
    }
}
