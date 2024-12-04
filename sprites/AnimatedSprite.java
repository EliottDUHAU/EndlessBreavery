package sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Abstract class for animated sprites
 */
public abstract class AnimatedSprite implements Sprite {
    /**
     * The number of ticks passed
     */
    protected int ticks = 0;
    /**
     * The current frame
     */
    protected int frame = 0;
    /**
     * The width of the sprite
     */
    protected int width;
    /**
     * The height of the sprite
     */
    protected int height;
    /**
     * The scale of the sprite
     */
    protected double scale = 3;
    /**
     * The number of ticks per frame
     */
    protected int ticksPerFrame = 1;
    /**
     * The number of frames in the sprite sheet
     */
    protected int frameCount;
    /**
     * The x position of the sprite
     */
    private int xPosition;
    /**
     * The y position of the sprite
     */
    private int yPosition;
    /**
     * The sprite sheet
     */
    private BufferedImage spriteSheet;
    /**
     * Whether the animation is done
     */
    private boolean done = false;
    /**
     * Get the sprite sheet
     * @return the sprite sheet
     */
    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }
    /**
     * Get the scale of the sprite
     * @return scale
     */
    public double getScale() {
        return scale;
    }
    /**
     * Create a new animated sprite
     * @param spriteSheetPath path to the sprite sheet file
     * @param ticksPerFrame number of ticks per frame
     * @param width width of the sprite
     * @param height height of the sprite
     * @param xPosition x position
     * @param yPosition y position
     */
    public AnimatedSprite(String spriteSheetPath, int ticksPerFrame, int width, int height, int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.ticksPerFrame = ticksPerFrame;
        try {spriteSheet = ImageIO.read(new File(spriteSheetPath));
        } catch (Exception e) {
            System.out.println("Could not load sprite sheet: " + spriteSheetPath);
            e.printStackTrace();
        }
        frameCount = spriteSheet.getWidth() / width;
    }
    /**
     * Get the scaled width of the sprite
     * @return the width of the sprite
     */
    @Override
    public int getWidth() {
        return (int) (width * scale);
    }
    /**
     * Get the scaled height of the sprite
     * @return the height of the sprite
     */
    @Override
    public int getHeight() {
        return (int) (height * scale);
    }
    /**
     * Get the x position of the sprite
     * @return the x position of the sprite
     */
    @Override
    public int getXPosition() {
        return xPosition;
    }
    /**
     * Get the y position of the sprite
     * @return the y position of the sprite
     */
    @Override
    public int getYPosition() {
        return yPosition;
    }
    /**
     * Set the x position of the sprite
     * @param xPosition x position
     */
    @Override
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }
    /**
     * Set the y position of the sprite
     * @param yPosition y position
     */
    @Override
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }
    /**
     * Get the image of the frame
     * @return frame image
     */
    public BufferedImage getImage() {
        return spriteSheet.getSubimage(frame * width, 0, width, height);
    }
    /**
     * Get the center x position of the sprite
     * @return the center x position of the sprite
     */
    public int getXCenter() {
        return xPosition + (getWidth() / 2);
    }
    /**
     * Get the center y position of the sprite
     * @return the center y position of the sprite
     */
    public int getYCenter() {
        return yPosition + (getHeight() / 2);
    }
    /**
     * Set the done state of the sprite
     * @param done done state
     */
    public void setDone(boolean done) {
        this.done = done;
    }
    /**
     * Get the done state of the sprite
     * @return done state
     */
    public boolean isDone() {
        return done;
    }
    /**
     * Advances the animation state
     */
    @Override
    public void tick() {
        ticks++;
        if (ticks >= ticksPerFrame) {
            ticks = 0;
            frame++;
            if (frame >= frameCount) {
                done = true;
                frame = 0;
            }
        }
    }
}
