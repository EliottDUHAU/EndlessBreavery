package entities.hitboxes;

import game.Camera;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Class for a line hitbox
 */
public class LineHitbox implements Hitbox {
    /**
     * The x offset of the line
     */
    private int vectorX;
    /**
     * The y offset of the line
     */
    private int vectorY;
    /**
     * The x position of the line
     */
    private int xPosition;
    /**
     * The y position of the line
     */
    private int yPosition;
    /**
     * The shape object of the line
     */
    private Line2D line;
    /**
     * Create a new line hitbox
     * @param xPosition x position
     * @param yPosition y position
     * @param vectorX x offset from x position
     * @param vectorY y offset from y position
     */
    public LineHitbox(int xPosition, int yPosition, int vectorX, int vectorY) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.vectorX = vectorX;
        this.vectorY = vectorY;
        line = new Line2D.Double(xPosition, yPosition, xPosition + vectorX, yPosition + vectorY);
    }
    /**
     * Get the shape of the hitbox
     * @return the shape of the hitbox
     */
    @Override
    public Line2D getShape() {
        return line;
    }
    /**
     * Get the x position of the hitbox
     * @return the x position of the hitbox
     */
    @Override
    public int getXPosition() {
        return xPosition;
    }
    /**
     * Get the y position of the hitbox
     * @return the y position of the hitbox
     */
    @Override
    public int getYPosition() {
        return yPosition;
    }
    /**
     * Set the x position of the hitbox
     * @param xPosition x position
     */
    @Override
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
        line = new Line2D.Double(xPosition, yPosition, xPosition + vectorX, yPosition + vectorY);
    }
    /**
     * Set the y position of the hitbox
     * @param yPosition y position
     */
    @Override
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
        line = new Line2D.Double(xPosition, yPosition, xPosition + vectorX, yPosition + vectorY);
    }
    /**
     * Check if the hitbox intersects with another hitbox
     * @param hitbox the hitbox to check intersection with
     * @return whether the hitboxes intersect
     */
    @Override
    public boolean intersects(Hitbox hitbox) {
        if (hitbox instanceof LineHitbox lineHitbox) {
            return line.intersectsLine(lineHitbox.getShape());
        }
        return hitbox.intersects(this);
    }
    /**
     * Get the x offset of the line
     * @return
     */
    public int getVectorX() {
        return vectorX;
    }
    /**
     * Get the y offset of the line
     * @return
     */
    public int getVectorY() {
        return vectorY;
    }
    /**
     * Draw the hitbox for debugging purposes
     * @param g the graphics object to draw with
     * @param camera the camera to draw relative to
     */
    @Override
    public void debugDraw(Graphics g, Camera camera) {
        int x = xPosition + vectorX;
        int y = yPosition + vectorY;
        g.drawLine(xPosition - camera.getX(), yPosition - camera.getY(),
                x - camera.getX(), y - camera.getY());
    }
}
