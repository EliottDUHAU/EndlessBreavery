package sprites.effects;

import game.Camera;
import sprites.AnimatedSprite;

import java.awt.*;

public class HealingVortex extends AnimatedSprite {
    public HealingVortex(int x, int y) {
        super("resources/effects/HealingSmall.png", 4, 62, 52, x, y);
        this.scale = 3;
    }

    @Override
    public void draw(Graphics g, Camera camera) {
        Graphics2D g2d = (Graphics2D) g;
        // Save the original composite
        Composite originalComposite = g2d.getComposite();

        // Set the composite to the desired opacity
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        g2d.drawImage(getImage(), getXPosition(), getYPosition(), getWidth(), getHeight(), null);

        // Restore the original composite
        g2d.setComposite(originalComposite);

    }
}
