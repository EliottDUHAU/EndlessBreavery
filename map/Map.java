package map;

import java.awt.*;
import java.util.*;
import java.util.List;

import entities.hitboxes.LineHitbox;
import game.Camera;
import sprites.TileSprite;
import utils.Pair;

/**
 * Class for a map
 */
public class Map {
    /**
     * The list of walls in the map
     */
    private List<Wall> walls = new ArrayList<Wall>();
    /**
     * The background sprite of the map
     */
    private TileSprite background = new TileSprite(0, 0);
    /**
     * Create a new map
     */
    public Map() {

        // Add outer arena hitboxes to the map in clockwise order
        walls.add(new LineWall(1171, 1130, 1250, 0));
        walls.add(new LineWall(2340, 1075, 575,500));
        walls.add(new LineWall(2755, 1300, 0, 1100));
        walls.add(new LineWall(2800, 2175, -625, 500));
        walls.add(new LineWall(2500, 2535, -1500, 0));
        walls.add(new LineWall(1210, 2540, -700, -580));
        walls.add(new LineWall(821, 2400, 0, -1100));
        walls.add(new LineWall(800, 1450, 400, -330));
        // Adding rock obstacles
        walls.add(new CircleWall(1345, 2205, 60));
        walls.add(new CircleWall(2230, 2175, 65));
        walls.add(new CircleWall(2225, 1468, 67));
        walls.add(new CircleWall(1324, 1478, 65));
        // Filling corners to avoid enemies spawning there
        for (int i = 1050; i > 750; i -= 25) {
            walls.add(new LineWall(2340, i, 570, 500));
        }
        for (int i = 2200; i < 2600; i += 25) {
            walls.add(new LineWall(2800, i, -625, 500));
        }
        for (int i = 2565; i < 2965; i += 25) {
            walls.add(new LineWall(1210, i, -700, -580));
        }
        for (int i = 1425; i > 1025; i -= 25) {
            walls.add(new LineWall(800, i, 400, -330));
        }
    }
    /**
     * Get the background sprite of the map
     * @return the background sprite of the map
     */
    public TileSprite getBackground() {
        return background;
    }
    /**
     * Collide a line hitbox with the map
     * @param collisionbox the line hitbox
     * @param xVector x offset from x position
     * @param yVector y offset from y position
     * @return the new position of the line hitbox, unchanged if collision occurred
     */
    public Pair<Integer, Integer> collide(LineHitbox collisionbox, int xVector, int yVector) {
        Pair<Integer, Integer> newPosition = new Pair<>(collisionbox.getXPosition(), collisionbox.getYPosition());
        for (Wall wall : walls) {
            newPosition= wall.collide(collisionbox, xVector, yVector);
            if (newPosition.getFirst() == collisionbox.getXPosition() && newPosition.getSecond() == collisionbox.getYPosition()) {
                return newPosition;
            }
        }
        return newPosition;
    }
    public boolean intersects(LineHitbox collisionbox) {
        for (Wall wall : walls) {
            if (wall.intersects(collisionbox)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Draw the map
     * @param g graphics object
     * @param camera camera object
     */
    public void debugDraw(Graphics g, Camera camera) {
        for (Wall wall : walls) {
            wall.debugDraw(g, camera);
        }
    }
}
