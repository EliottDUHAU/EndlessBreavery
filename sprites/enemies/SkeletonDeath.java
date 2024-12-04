package sprites.enemies;

import game.Camera;
import sprites.AnimatedSprite;

public class SkeletonDeath extends AnimatedSprite {

    private boolean faceLeft = true;

    public void setFaceLeft(boolean faceLeft) {
        this.faceLeft = faceLeft;
    }

    public boolean isFacingLeft() {
        return faceLeft;
    }

    public SkeletonDeath(int xPosition, int yPosition) {
        super("resources/enemies/Skeleton/Death.png", 6, 18, 36, xPosition, yPosition);
        this.frameCount = 12;
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