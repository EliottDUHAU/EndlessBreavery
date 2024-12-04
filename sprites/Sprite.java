package sprites;

import game.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Interface for a sprite object
 */
public interface Sprite {
    /**
     * Get the width of the sprite
     * @return ingame width
     */
    int getWidth();
    /**
     * Get the height of the sprite
     * @return ingame height
     */
    int getHeight();
    /**
     * Get the x position of the sprite
     * @return x position
     */
    int getXPosition();
    /**
     * Get the y position of the sprite
     * @return y position
     */
    int getYPosition();
    /**
     * Set the x position of the sprite
     * @param xPosition x position
     */
    void setXPosition(int xPosition);
    /**
     * Set the y position of the sprite
     * @param yPosition y position
     */
    void setYPosition(int yPosition);
    /**
     * Get the image of the sprite
     * @return image
     */
    BufferedImage getImage();
    /**
     * Draw the sprite
     * @param g the graphics object to draw the sprite onto
     */
    void draw(Graphics g, Camera camera);
    /**
     * Update the sprite for animation
     */
    void tick();
}