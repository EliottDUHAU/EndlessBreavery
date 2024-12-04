package sprites.player;

import entities.Player;
import game.Camera;
import sprites.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class for the player health overlay
 */
public class PlayerUI implements Sprite {
    /**
     * The width of the health bar
     */
    static final int HEALTH_BAR_WIDTH = 250;
    /**
     * The height of the health bar
     */
    static final int HEALTH_BAR_HEIGHT = 20;
    /**
     * Get the height of the sprite
     * Unimplemented
     * @return the height of the sprite
     */
    @Override
    public int getHeight() {
        return 0;
    }
    /**
     * Get the width of the sprite
     * Unimplemented
     * @return the width of the sprite
     */
    public int getWidth() {
        return 0;
    }
    /**
     * Player to display health for
     */
    private Player player;
    /**
     * Create a new player UI
     * @param player the player to display health for
     */
    public PlayerUI(Player player) {
        this.player = player;
    }
    /**
     * Get the x position of the sprite
     * Unimplemented
     * @return the x position of the sprite
     */
    @Override
    public int getXPosition() {
        return 0;
    }
    /**
     * Get the y position of the sprite
     * Unimplemented
     * @return the y position of the sprite
     */
    @Override
    public int getYPosition() {
        return 0;
    }
    /**
     * Set the x position of the sprite
     * Unimplemented
     * @param xPosition x position
     */
    @Override
    public void setXPosition(int xPosition) {
    }
    /**
     * Set the y position of the sprite
     * Unimplemented
     * @param yPosition y position
     */
    @Override
    public void setYPosition(int yPosition) {
    }
    /**
     * Advance the sprite's state
     */
    @Override
    public void tick() {
    }
    /**
     * Get the image of the sprite
     * Unimplemented
     * @return the image of the sprite
     */
    @Override
    public BufferedImage getImage() {
        return null;
    }
    /**
     * Draw the UI
     * @param g the graphics object to draw to
     * @param camera the camera to draw relative to
     */
    @Override
    public void draw(Graphics g, Camera camera) {
        int healthRatio = player.getHealth() * HEALTH_BAR_WIDTH / player.getMaxHealth();
        // Draw the player's health
        g.setColor(Color.BLACK);
        g.drawRect(9,9,HEALTH_BAR_WIDTH+2,HEALTH_BAR_HEIGHT+2);
        g.setColor(Color.RED);
        g.fillRect(10, 10, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
        g.setColor(Color.GREEN);
        g.fillRect(10, 10, healthRatio, HEALTH_BAR_HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Health: " + player.getHealth() + "/" + player.getMaxHealth(), 20, 25);
    }
}
