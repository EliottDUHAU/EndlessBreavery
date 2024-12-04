package sprites.player;

import game.Camera;
import sprites.AnimatedSprite;
import sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * The base player sprite
 */
public class PlayerSprite extends AnimatedSprite {
    /**
     * Idle sprite sheet
     */
    private BufferedImage idleSpriteSheet;
    /**
     * Run sprite sheet
     */
    private BufferedImage runSpriteSheet;
    /**
     * Get the scale of the sprite
     * @return scale
     */
    public double getScale() {
        return scale;
    }
    /**
     * Whether the player is facing left
     */
    private boolean faceLeft = false;
    /**
     * Whether the player is moving to select right animation
     */
    private boolean moving = false;

    /**
     * Set whether the player is moving
     * @param moving whether the player is moving
     */
    public void setMoving(boolean moving) {
        if (moving == this.moving) return;
        this.frame = 0;
        this.ticks = 0;
        this.moving = moving;
    }
    /**
     * Get whether the player is moving
     * @return whether the player is moving
     */
    public boolean isMoving() {
        return moving;
    }
    /**
     * Set whether the player is facing left
     * @param faceLeft whether the player is facing left
     */
    public void setFaceLeft(boolean faceLeft) {
        this.faceLeft = faceLeft;
    }
    /**
     * Get whether the player is facing left
     * @return whether the player is facing left
     */
    public boolean isFacingLeft() {
        return faceLeft;
    }
    /**
     * Create a new player sprite
     * @param xPosition x position
     * @param yPosition y position
     */
    public PlayerSprite(int xPosition, int yPosition) {
        super("resources/player/idle.png", 7, 25, 28, xPosition, yPosition);
        this.scale = 4;
        loadSpriteSheet();
    }
    /**
     * Load the spritesheet image from resources.
     */
    private void loadSpriteSheet() {
        try {
            idleSpriteSheet = ImageIO.read(new File("resources/player/idle.png"));
            runSpriteSheet = ImageIO.read(new File("resources/player/run.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Update the sprite for animation
     */
    @Override
    public void tick() {
        ticks++;
        if (ticks >= this.ticksPerFrame) {
            ticks = 0;
            frame++;
            if (!moving && frame >= 4) {
                frame = 0;
            }
            if (moving && frame >= 6) {
                frame = 0;
            }
        }
    }
    /**
     * Get the image of the sprite
     * @return image
     */
    @Override
    public BufferedImage getImage() {
        if (!moving) return idleSpriteSheet.getSubimage(frame * 25, 0, 25, 28);
        return  runSpriteSheet.getSubimage(frame * 22, 0, 22, 28);
    }
    /**
     * Draw the sprite
     * @param g the graphics object to draw the sprite onto
     */
    @Override
    public void draw(java.awt.Graphics g, Camera camera) {
        if ( faceLeft ) {
            g.drawImage(
                    getImage(),
                    getXPosition() + getWidth() - camera.getX(),
                    getYPosition() - camera.getY(),
                    -getWidth(),
                    getHeight(),
                    null);
        } else {
            g.drawImage(getImage(),
                    getXPosition() - camera.getX(),
                    getYPosition() - camera.getY(),
                    getWidth(),
                    getHeight(),
                    null);
        }
    }
}
