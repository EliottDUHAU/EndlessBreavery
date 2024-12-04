package sprites;

import game.Camera;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The background sprite
 */
public class TileSprite implements Sprite {
    /**
     * The tile image
     */
    private BufferedImage tileImage;
    /**
     * The x position of the sprite
     */
    private int xPosition;
    /**
     * The y position of the sprite
     */
    private int yPosition;
    /**
     * The width of the sprite
     */
    private int width;
    /**
     * The height of the sprite
     */
    private int height;
    /**
     * The scale of the sprite
     */
    private float scale = 4.5f;
    /**
     * Create a new tile sprite
     * @param xPosition x position
     * @param yPosition y position
     */
    public TileSprite(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        loadTileImage();
        this.height = (int) (tileImage.getHeight() * scale);
        this.width = (int) (tileImage.getWidth() * scale);
    }
    /**
     * Get the width of the sprite
     * @return ingame width
     */
    public int getWidth() {
        return width;
    }
    /**
     * Get the height of the sprite
     * @return ingame height
     */
    public int getHeight() {
        return height;
    }
    /**
     * Load the tile image from resources.
     */
    private void loadTileImage() {
        try {
            tileImage = ImageIO.read(new File("resources/arena.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Get the x position of the sprite
     * @return x position
     */
    @Override
    public int getXPosition() {
        return xPosition;
    }
    /**
     * Get the y position of the sprite
     * @return y position
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
     * Get the image of the sprite
     * @return the image of the sprite
     */
    @Override
    public BufferedImage getImage() {
        return tileImage;
    }
    /**
     * Draw the sprite
     * @param g the graphics object
     * @param camera the camera
     */
    public void draw(java.awt.Graphics g, Camera camera) {
        if (tileImage != null) {
            int tileWidth = (int) (tileImage.getWidth() * scale); // Update tileWidth calculation
            int tileHeight = (int) (tileImage.getHeight() * scale); // Update tileHeight calculation

            for (int x = 0; x < GamePanel.GAME_WIDTH; x += tileWidth) {
                for (int y = 0; y < GamePanel.GAME_HEIGHT; y += tileHeight) {
                    g.drawImage(tileImage, x - camera.getX(), y - camera.getY(), tileWidth, tileHeight, null);
                }
            }
        }
    }
    /**
     * Is a static image, no need to update
     */
    @Override
    public void tick() {
        // Do nothing
    }
}