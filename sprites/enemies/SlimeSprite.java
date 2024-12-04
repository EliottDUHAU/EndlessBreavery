package sprites.enemies;

import entities.enemies.Slime;
import game.Camera;
import sprites.AnimatedSprite;
import sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * The sprite for a slime enemy
 */
public class SlimeSprite extends AnimatedSprite {

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
    public SlimeSprite(int xPosition, int yPosition) {
        super("resources/enemies/slime/move.png", 4, 32, 25, xPosition, yPosition);
        this.frameCount = 4;
        this.scale = 4;
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
