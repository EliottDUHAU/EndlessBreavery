package map;

import entities.hitboxes.LineHitbox;
import entities.hitboxes.PolygoneHitbox;
import game.Camera;
import utils.Pair;

import java.awt.*;

/**
 * Class for a line collision wall
 */
public class LineWall implements Wall {
    /**
     * The hitbox of the line wall
     */
    LineHitbox hitbox;
    /**
     * Create a new line wall
     * @param xPosition x position
     * @param yPosition y position
     * @param xVector x offset from x position
     * @param yVector y offset from y position
     */
    public LineWall(int xPosition, int yPosition, int xVector, int yVector) {
        this.hitbox = new LineHitbox(xPosition, yPosition, xVector, yVector);
    }
    /**
     * Draw the collision hitbox of the wall
     * @param g graphics object
     * @param camera camera object
     */
    @Override
    public void debugDraw(Graphics g, Camera camera) {
        this.hitbox.debugDraw(g, camera);
    }
    /**
     * Collide a line hitbox with the wall
     * @param hitbox the line hitbox
     * @param xVector x offset from x position
     * @param yVector y offset from y position
     * @return the new position of the line hitbox
     */
    @Override
    public Pair<Integer, Integer> collide(LineHitbox hitbox, int xVector, int yVector) {
        PolygoneHitbox movementHitbox = new PolygoneHitbox(
                new Pair<Integer, Integer>(hitbox.getXPosition(), hitbox.getYPosition()),
                new Pair<Integer, Integer>(hitbox.getXPosition() + xVector, hitbox.getYPosition() + yVector),
                new Pair<Integer, Integer>(hitbox.getXPosition() + hitbox.getVectorX() + xVector, hitbox.getYPosition() + hitbox.getVectorY() + yVector),
                new Pair<Integer, Integer>(hitbox.getXPosition() + hitbox.getVectorX(), hitbox.getYPosition() + hitbox.getVectorY())
        );
        // Check if the line intersects the vector
        if (this.hitbox.intersects(movementHitbox)) {
            return new Pair<Integer, Integer>(hitbox.getXPosition(), hitbox.getYPosition());
        }
        return new Pair<Integer, Integer>(hitbox.getXPosition() + xVector, hitbox.getYPosition() + yVector);
    }

    @Override
    public boolean intersects(LineHitbox hitbox) {
        return this.hitbox.intersects(hitbox);
    }
}
