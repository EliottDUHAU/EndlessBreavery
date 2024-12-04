package sprites.effects;

import game.Camera;
import sprites.AnimatedSprite;

import java.awt.*;

/**
 * Class for a slash swirl effect
 */
public class SlashSwirl extends AnimatedSprite {
    /**
     * Whether the swirl is facing left
     */
    private boolean faceLeft = false;
    /**
     * Create a new slash swirl
     * @param xPosition x position
     * @param yPosition y position
     * @param faceLeft whether the swirl is facing left
     */
    public SlashSwirl(int xPosition, int yPosition, boolean faceLeft) {
        super("resources/effects/SlashSwirlSheet.png", 2, 40, 39,
                xPosition - 40 * 3,
                yPosition - 39 * 3);
        this.scale = 6;
        this.faceLeft = faceLeft;
    }
    /**
     * Draw the swirl
     * @param g graphics object
     * @param camera camera object
     */
    @Override
    public void draw(java.awt.Graphics g, Camera camera) {
        Graphics2D g2d = (Graphics2D) g;
        // Save the original composite
        Composite originalComposite = g2d.getComposite();

        // Set the composite to the desired opacity
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));

        // draw image
        if ( faceLeft ) {
            g2d.drawImage(
                    getImage(),
                    getXPosition() + getWidth() - camera.getX(),
                    getYPosition()- camera.getY(),
                    -getWidth(),
                    getHeight(),
                    null);
        } else {
            g2d.drawImage(getImage(),
                    getXPosition() - camera.getX(),
                    getYPosition() - camera.getY(),
                    getWidth(),
                    getHeight(),
                    null);
        }
        // reset the composite
        g2d.setComposite(originalComposite);
    }
}
