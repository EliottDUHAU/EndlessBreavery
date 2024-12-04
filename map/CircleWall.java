package map;

import entities.hitboxes.CircleHitbox;
import entities.hitboxes.LineHitbox;
import entities.hitboxes.PolygoneHitbox;
import game.Camera;
import utils.Pair;

import java.awt.*;

/**
 * Class for a circle collision wall
 */
public class CircleWall implements Wall {
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
     * The hitbox of the circle wall
     */
    private CircleHitbox hitbox;
    /**
     * Create a new circle wall
     * @param xPosition x position
     * @param yPosition y position
     * @param radius radius
     */
    public CircleWall(int xPosition, int yPosition, int radius) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.radius = radius;
        this.hitbox = new CircleHitbox(xPosition, yPosition, radius);
    }
    /**
     * Get the x position of the circle center
     * @return the x position of the circle center
     */
    public int getXPosition() {
        return xPosition;
    }
    /**
     * Get the y position of the circle center
     * @return the y position of the circle center
     */
    public int getYPosition() {
        return yPosition;
    }
    /**
     * Get the radius of the circle
     * @return the radius of the circle
     */
    public int getRadius() {
        return radius;
    }
    /**
     * Draw the circle wall collision hitbox
     * @param g the graphics object
     * @param camera the camera object
     */
    @Override
    public void debugDraw(Graphics g, Camera camera) {
        hitbox.debugDraw(g, camera);
    }
    /**
     * Check if a line intersects with the circle wall
     * @param hitbox the line hitbox
     * @return whether the line intersects with the circle wall
     */
    public Pair<Integer, Integer> collide(LineHitbox hitbox, int xVector, int yVector) {
        PolygoneHitbox movementHitbox = new PolygoneHitbox(
                new Pair<Integer, Integer>(hitbox.getXPosition(), hitbox.getYPosition()),
                new Pair<Integer, Integer>(hitbox.getXPosition() + xVector, hitbox.getYPosition() + yVector),
                new Pair<Integer, Integer>(hitbox.getXPosition() + hitbox.getVectorX() + xVector, hitbox.getYPosition() + hitbox.getVectorY() + yVector),
                new Pair<Integer, Integer>(hitbox.getXPosition() + hitbox.getVectorX(), hitbox.getYPosition() + hitbox.getVectorY())
        );
        // Check if the circle intersects the vector
        if (this.hitbox.intersects(movementHitbox)) {
            return new Pair<Integer, Integer>(hitbox.getXPosition(), hitbox.getYPosition());
        }
        return new Pair<Integer, Integer>(hitbox.getXPosition() + xVector, hitbox.getYPosition() + yVector);
    }

    public boolean intersects(LineHitbox hitbox) {
        return this.hitbox.intersects(hitbox);
    }
}
