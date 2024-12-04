package sprites.enemies;

import game.Camera;
import sprites.AnimatedSprite;

import java.awt.Graphics;

public class SkeletonSprite extends AnimatedSprite {

    private boolean faceLeft = true;

    public void setFaceLeft(boolean faceLeft) {
        this.faceLeft = faceLeft;
    }

    public boolean isFacingLeft() {
        return faceLeft;
    }
    /**
     * Create a new skeleton sprite
     * @param xPosition x position
     * @param yPosition y position
     */
    public SkeletonSprite(int xPosition, int yPosition) {
        super("resources/enemies/Skeleton/Walk2.png", 6, 25, 36, xPosition, yPosition);
        this.frameCount = 6;
        this.scale = 4;
    }

    /**
     * Draw the sprite
     * @param g the graphics object to draw the sprite onto
     */
    @Override
    public void draw(Graphics g, Camera camera) {
        if (faceLeft) {
            g.drawImage(
                    getImage(),
                    getXPosition() - camera.getX() + getWidth(),
                    getYPosition() - camera.getY(),
                    -getWidth(),
                    getHeight(),
                    null);
        } else {
            g.drawImage(
                    getImage(),
                    getXPosition() - camera.getX(),
                    getYPosition() - camera.getY(),
                    getWidth(),
                    getHeight(),
                    null);
        }
    }
}