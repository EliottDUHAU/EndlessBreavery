package sprites.player;

import game.Camera;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Abstract player sprite decorator to augment the sprite and animation
 */
public abstract class PlayerSpriteDecorator extends PlayerSprite {
    /**
     * The player sprite to decorate
     */
    private PlayerSprite playerSprite;
    /**
     * Get the player sprite
     * @return the player sprite
     */
    public PlayerSprite getPlayerSprite() {
        return playerSprite;
    }
    /**
     * Sprite sheet image
     */
    private BufferedImage spriteSheet;
    /**
     * Create a new player sprite decorator
     *
     * @param sprite the player sprite to decorate
     */
    public PlayerSpriteDecorator(PlayerSprite sprite, String spriteSheetPath, int ticksPerFrame, int width, int height) {
        super(sprite.getXPosition(), sprite.getYPosition());
        this.playerSprite = sprite;
        this.width = width;
        this.height = height;
        this.ticksPerFrame = ticksPerFrame;
        try {
            spriteSheet = ImageIO.read(new File(spriteSheetPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frameCount = spriteSheet.getWidth() / width;
    }
    /**
     * Get the center x position of the sprite
     * @return the center x position of the sprite
     */
    @Override
    public int getXCenter() {
        return playerSprite.getXCenter();
    }
    /**
     * Get the center y position of the sprite
     * @return the center y position of the sprite
     */
    @Override
    public int getYCenter() {
        return playerSprite.getYCenter();
    }
    /**
     * Forward the draw to the decorated sprite
     */
    @Override
    public void draw(java.awt.Graphics g, Camera camera) {
        playerSprite.draw(g, camera);
    }
    /**
     * Set the moving state of the decorated sprite
     *
     * @param moving moving state
     */
    @Override
    public void setMoving(boolean moving) {
        playerSprite.setMoving(moving);
    }
    /**
     * Set the facing state of the decorated sprite
     *
     * @param faceLeft true if facing left, false if facing right
     */
    @Override
    public void setFaceLeft(boolean faceLeft) {
        playerSprite.setFaceLeft(faceLeft);
    }
    /**
     * Get the facing state of the decorated sprite
     *
     * @return true if facing left, false if facing right
     */
    @Override
    public boolean isFacingLeft() {
        return playerSprite.isFacingLeft();
    }
    /**
     * Get the image of the current frame
     * @return the image of the current frame
     */
    @Override
    public BufferedImage getImage() {
        return spriteSheet.getSubimage(frame * width, 0, width, height);
    }
    /**
     * Get the x offset of the sprite
     * @return the x offset of the sprite
     */
    public int getOffsetX() {
        return (playerSprite.getWidth() - getWidth()) / 2;
    }
    /**
     * Advance the animation state
     */
    public void tick() {
        ticks++;
        if (ticks >= ticksPerFrame) {
            ticks = 0;
            frame++;
            if (frame >= frameCount) {
                setDone(true);
                frame = 0;
            }
        }
    }

    @Override
    public void setXPosition(int xPosition) {
        getPlayerSprite().setXPosition(xPosition);
    }

    @Override
    public void setYPosition(int yPosition) {
        getPlayerSprite().setYPosition(yPosition);
    }
}
