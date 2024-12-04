package entities.hitboxes;

import game.Camera;

import java.awt.*;

/**
 * Interface for a hitbox
 */
public interface Hitbox {
    /**
     * Get the shape of the hitbox
     * @return the shape of the hitbox
     */
    Shape getShape();
    /**
     * Get the x position of the center of the hitbox
     * @return x position
     */
    int getXPosition();
    /**
     * Get the y position of the center of the hitbox
     * @return y position
     */
    int getYPosition();
    /**
     * Set the x position of the hitbox
     * @param xPosition x position
     */
    void setXPosition(int xPosition);
    /**
     * Set the y position of the hitbox
     * @param yPosition y position
     */
    void setYPosition(int yPosition);
    /**
     * Check if the hitbox intersects with another hitbox
     * @param hitbox the hitbox to check intersection with
     * @return whether the hitboxes intersect
     */
    boolean intersects(Hitbox hitbox);
    /**
     * Draw the hitbox for debugging purposes
     * @param g the graphics object to draw with
     * @param camera the camera to draw relative to
     */
    void debugDraw(Graphics g, Camera camera);
}
