package sprites.player;

import game.Camera;
import sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class LoadingBar implements Sprite {
    private BufferedImage imageFull;
    private BufferedImage imageEmpty;
    /**
     * The duration of the loading bar in ticks
     */
    private int duration;
    private int progress = 0;

    private double scale;
    private int xPosition;
    private int yPosition;

    public LoadingBar(String imageFullPath, String imageEmptyPath, int duration, int xPosition, int yPosition, double scale) {
        try {
            imageFull = ImageIO.read(new File(imageFullPath));
            imageEmpty = ImageIO.read(new File(imageEmptyPath));
        } catch (Exception e) {
            System.out.println("Could not load sprites");
            e.printStackTrace();
        }
        this.duration = duration;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.scale = scale;
    }

    /**
     * Get the width of the sprite
     *
     * @return ingame width
     */
    @Override
    public int getWidth() {
        return (int) (Math.max(imageFull.getWidth(), imageEmpty.getWidth()) * scale);
    }

    /**
     * Get the height of the sprite
     *
     * @return ingame height
     */
    @Override
    public int getHeight() {
        return (int) (Math.max(imageFull.getHeight(), imageEmpty.getHeight()) * scale);
    }

    /**
     * Get the x position of the sprite
     *
     * @return x position
     */
    @Override
    public int getXPosition() {
        return xPosition;
    }

    /**
     * Get the y position of the sprite
     *
     * @return y position
     */
    @Override
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Set the x position of the sprite
     *
     * @param xPosition x position
     */
    @Override
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Set the y position of the sprite
     *
     * @param yPosition y position
     */
    @Override
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * Get the image of the sprite
     *
     * @return image
     */
    @Override
    public BufferedImage getImage() {
        return imageFull;
    }

    /**
     * Draw the sprite
     *
     * @param g      the graphics object to draw the sprite onto
     * @param camera
     */
    @Override
    public void draw(Graphics g, Camera camera) {
        g.drawImage(imageEmpty, xPosition, yPosition, getWidth(), getHeight(), null);
        int width = (int) (imageFull.getWidth() * ((double) progress / duration));
        if (width == 0) {
            return;
        }
        BufferedImage completionLayer = imageFull.getSubimage(0, 0, width, imageFull.getHeight());
        g.drawImage(completionLayer, xPosition, yPosition, (int) (completionLayer.getWidth() * scale), (int) (completionLayer.getHeight() * scale), null);
    }

    /**
     * Update the sprite for animation
     */
    @Override
    public void tick() {
        if (progress < duration) {
            progress++;
        }
    }

    public boolean isDone() {
        return progress >= duration;
    }
}
