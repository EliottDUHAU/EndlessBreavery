package entities.hitboxes;
import game.Camera;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * A void hitbox that does not collide with anything
 */
public class VoidHitbox implements Hitbox {
    /**
     * Get the shape of the hitbox
     * @return the shape of the hitbox
     */
    @Override
    public Shape getShape() {
        return new Line2D.Double(0, 0, 0, 0);
    };
    /**
     * Get the x position of the hitbox
     * @return the x position of the hitbox
     */
    @Override
    public int getXPosition() {
        return 0;
    }
    /**
     * Get the y position of the hitbox
     * @return the y position of the hitbox
     */
    @Override
    public int getYPosition() {
        return 0;
    }
    /**
     * Set the x position of the hitbox
     * @param xPosition the x position of the hitbox
     */
    @Override
    public void setXPosition(int xPosition) {
        // Do nothing
    }
    /**
     * Set the y position of the hitbox
     * @param yPosition the y position of the hitbox
     */
    @Override
    public void setYPosition(int yPosition) {
        // Do nothing
    }
    /**
     * Check if the hitbox intersects with another hitbox
     * @param hitbox the hitbox to check
     * @return always returns false
     */
    @Override
    public boolean intersects(Hitbox hitbox) {
        return false;
    }

    /**
     * Check if the hitbox intersects with a circle hitbox
     * @param g the graphics object to draw with
     * @param camera the camera to draw relative to
     */
    @Override
    public void debugDraw(Graphics g, Camera camera) {
        // Do nothing
    }

}
