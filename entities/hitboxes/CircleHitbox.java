package entities.hitboxes;

import game.Camera;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Class for a circle hitbox
 */
public class CircleHitbox implements Hitbox {
    /**
     * The x position of the circle
     */
    private int xPosition;
    /**
     * The y position of the circle
     */
    private int yPosition;
    /**
     * The radius of the circle
     */
    private int radius;
    /**
     * The shape object of the circle
     */
    Shape circle;
    /**
     * Create a new circle hitbox
     * @param xPosition x position
     * @param yPosition y position
     * @param radius radius
     */
    public CircleHitbox(int xPosition, int yPosition, int radius) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.radius = radius;
        circle = new Ellipse2D.Double(xPosition - radius, yPosition - radius, radius * 2, radius * 2);
    }
    /**
     * Get the shape of the hitbox
     * @return the shape of the hitbox
     */
    @Override
    public Shape getShape() {
        return circle;
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
        circle = new Ellipse2D.Double(xPosition - radius, yPosition - radius, radius * 2, radius * 2);
    }
    /**
     * Set the y position of the hitbox
     * @param yPosition y position
     */
    @Override
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
        circle = new Ellipse2D.Double(xPosition - radius, yPosition - radius, radius * 2, radius * 2);
    }
    /**
     * Check if the hitbox intersects another hitbox
     * @param hitbox the hitbox to check
     * @return true if the hitboxes intersect
     */
    @Override
    public boolean intersects(Hitbox hitbox) {
        if (hitbox instanceof LineHitbox lineHitbox) {
            return lineHitbox.getShape().ptSegDist(xPosition, yPosition) <= radius;
        }
        if (hitbox instanceof CircleHitbox circleHitbox) {
            int distance = (int) Math.sqrt(Math.pow(circleHitbox.getXPosition() - xPosition, 2) + Math.pow(circleHitbox.getYPosition() - yPosition, 2));
            return distance <= radius + circleHitbox.getRadius();
        }
        return hitbox.intersects(this);
    }
    /**
     * Get the radius of the hitbox
     * @return the radius of the hitbox
     */
    public int getRadius() {
        return radius;
    }
    /**
     * Set the radius of the hitbox
     * @param radius the radius of the hitbox
     */
    public void setRadius(int radius) {
        circle = new Ellipse2D.Double(xPosition - radius, yPosition - radius, radius * 2, radius * 2);
        this.radius = radius;
    }
    /**
     * Draw the hitbox
     * @param g the graphics object
     * @param camera the camera object
     */
    @Override
    public void debugDraw(Graphics g, Camera camera) {
        int screenX = xPosition - camera.getX();
        int screenY = yPosition - camera.getY();
        int screenRadius = radius;
        g.drawOval(screenX - screenRadius, screenY - screenRadius, screenRadius * 2, screenRadius * 2);
    }
}
