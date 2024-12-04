package sprites.menus;

import controllers.AudioController;
import controllers.CameraController;
import controllers.EnemyController;
import game.Camera;
import sprites.Sprite;
import view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class for the pause screen overlay
 */
public class GameOver implements Sprite {

    public GameOver() {
        int buttonWidth = 200;
        int buttonHeight = 100;
        int centerX = (GamePanel.GAME_WIDTH - buttonWidth) / 2;
        int centerY = (GamePanel.GAME_HEIGHT - buttonHeight) / 2;
    }
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
    @Override
    public int getWidth() {
        return 0;
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
     * Get the image of the sprite
     * Unimplemented
     * @return the image of the sprite
     */
    @Override
    public BufferedImage getImage() {
        return null;
    }
    /**
     * Update the sprite
     * Unimplemented
     */
    @Override
    public void tick() {
    }
    /**
     * Draw the pause screen overlay
     * @param g graphics object
     * @param camera camera object
     */
    @Override
    public void draw(Graphics g, Camera camera) {
        int killCount = EnemyController.getInstance().getKillCount();
        int waveCount = EnemyController.getInstance().getWaveCount();
        // transparent black background
        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, camera.getWidth(), camera.getHeight());
        // Draw "Game Over" text
        drawText(g, 75, "Game Over", camera.getHeight() / 3);
        drawText(g, 25, "Killed " + killCount + " enemies.", camera.getHeight() * 2 / 3);
        drawText(g, 25, "Survived " + waveCount + " waves.", (camera.getHeight() * 2 / 3) + g.getFontMetrics().getAscent() * 2);
        drawText(g, 25, "Click to restart.", (camera.getHeight() * 2 / 3) - g.getFontMetrics().getAscent() * 2);

    }

    private void drawText(Graphics g, int fontSize, String text, int height) {
        g.setFont(new Font("Arial", Font.BOLD, fontSize));
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        int x = (CameraController.getInstance().getCamera().getWidth() - textWidth) / 2;
        int y = height + textHeight;
        // Draw black outline
        int outline = 2;
        g.setColor(Color.BLACK);
        g.drawString(text, x - outline, y - outline);
        g.drawString(text, x - outline, y + outline);
        g.drawString(text, x + outline, y - outline);
        g.drawString(text, x + outline, y + outline);
        // Draw main white text
        g.setColor(Color.WHITE);
        g.drawString(text, x, y);
    }
}

