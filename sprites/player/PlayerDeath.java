package sprites.player;

import game.Camera;

import java.awt.*;

public class PlayerDeath extends PlayerSpriteDecorator {
    public PlayerDeath(PlayerSprite sprite) {
        super(sprite, "resources/player/death.png", 10, 22, 24);
    }

    @Override
    public void draw(Graphics g, Camera camera) {
        g.drawImage(getImage(), getXPosition() - camera.getX(), getYPosition() - camera.getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        ticks++;
        if (ticks >= ticksPerFrame) {
            ticks = 0;
            if (frame < frameCount - 1) frame++;
            if (frame == frameCount - 1) {
                setDone(true);
            }
        }
    }
}
