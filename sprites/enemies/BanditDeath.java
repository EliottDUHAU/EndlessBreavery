package sprites.enemies;

import game.Camera;
import sprites.AnimatedSprite;

public class BanditDeath extends AnimatedSprite {

    private boolean faceLeft = true;

    public void setFaceLeft(boolean faceLeft) {
        this.faceLeft = faceLeft;
    }

    public boolean isFacingLeft() {
        return faceLeft;
    }

    public BanditDeath(int xPosition, int yPosition) {
        super("resources/enemies/Bandit/Death.png", 6, 51, 37, xPosition, yPosition);
        this.frameCount = 4;
        this.scale = 4;
    }

    @Override
    public void draw(java.awt.Graphics g, Camera camera) {
        if (faceLeft) {
            g.drawImage(
                    getImage(),
                    getXPosition() - camera.getX() + getWidth(),
                    getYPosition() - camera.getY(),
                    -getWidth(),
                    getHeight(),
                    null);
        } else {
            g.drawImage(
                    getImage(),
                    getXPosition() - camera.getX(),
                    getYPosition() - camera.getY(),
                    getWidth(),
                    getHeight(),
                    null);
        }
    }
}
