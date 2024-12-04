package entities.hitboxes;

import game.Camera;
import utils.Pair;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

/**
 * Class for a rectangular hitbox
 */
public class RectangularHitbox extends PolygoneHitbox {
    /**
     * The width of the rectangle
     */
    private int width;
    /**
     * The height of the rectangle
     */
    private int height;
    /**
     * Constructor for a rectangular hitbox
     * @param xPosition x position of the center of the rectangle
     * @param yPosition y position of the center of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public RectangularHitbox(int xPosition, int yPosition, int width, int height) {
        super(new Pair<>(xPosition - width / 2, yPosition - height / 2),
              new Pair<>(xPosition + width / 2, yPosition - height / 2),
              new Pair<>(xPosition + width / 2, yPosition + height / 2),
              new Pair<>(xPosition - width / 2, yPosition + height / 2));
        this.width = width;
        this.height = height;
    }
    /**
     * Get the x position of the center of the rectangle
     * @return the x position of the center of the rectangle
     */
    @Override
    public int getXPosition() {
        return super.getXPosition() + width / 2;
    }

    /**
     * Get the y position of the center of the rectangle
     * @return the y position of the center of the rectangle
     */
    @Override
    public int getYPosition() {
        return super.getYPosition() + height / 2;
    }

    /**
     * Overriding the position setter to center the rectangle
     * @param xPosition x position of the rectangle
     */
    @Override
    public void setXPosition(int xPosition) {
        super.setXPosition(xPosition - width / 2);
    }
    /**
     * Overriding the position setter to center the rectangle
     * @param yPosition y position of the rectangle
     */
    @Override
    public void setYPosition(int yPosition) {
        super.setYPosition(yPosition - height / 2);
    }

    /**
     * Get the width of the rectangle
     * @return the width of the rectangle
     */
    public int getWidth() {
        return width;
    }
    /**
     * Get the height of the rectangle
     * @return the height of the rectangle
     */
    public int getHeight() {
        return height;
    }
}

