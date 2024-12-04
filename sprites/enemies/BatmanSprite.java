package sprites.enemies;

import game.Camera;
import sprites.AnimatedSprite;

public class BatmanSprite extends AnimatedSprite {

    private boolean faceLeft = true;

    public void setFaceLeft(boolean faceLeft) {
        this.faceLeft = faceLeft;
    }

    public boolean isFacingLeft() {
        return faceLeft;
    }

    /**
     * Create a new slime sprite
     * @param xPosition x position
     * @param yPosition y position
     */
    public BatmanSprite(int xPosition, int yPosition) {
        super("resources/enemies/Batman/Flight.png", 6, 42, 34, xPosition, yPosition);
        this.frameCount = 8;
        this.scale = 3;
    }
    /**
     * Draw the sprite
     * @param g the graphics object to draw the sprite onto
     */
    @Override
    public void draw(java.awt.Graphics g, Camera camera) {
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