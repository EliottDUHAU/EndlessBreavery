package map;

import entities.hitboxes.LineHitbox;
import game.Camera;
import utils.Pair;

import java.awt.*;

/**
 * Interface for a collision wall
 */
public interface Wall {
    /**
     * Draw the collision hitbox of the wall
     * @param g graphics object
     * @param camera camera object
     */
    void debugDraw(Graphics g, Camera camera);
    /**
     * Collide a line hitbox with the wall
     * @param hitbox the line hitbox
     * @param xVector x offset from x position
     * @param yVector y offset from y position
     * @return the new position of the line hitbox, unchanged if collision occurred
     */
    Pair<Integer, Integer> collide(LineHitbox hitbox,
                                   int xVector,
                                   int yVector);

    boolean intersects(LineHitbox hitbox);
}
