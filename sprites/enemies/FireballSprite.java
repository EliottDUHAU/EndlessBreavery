package sprites.enemies;

import game.Camera;
import sprites.AnimatedSprite;

import java.awt.*;

public class FireballSprite extends AnimatedSprite {

    private boolean faceLeft = true;

    public void setFaceLeft(boolean faceLeft) {
        this.faceLeft = faceLeft;
    }

    public boolean isFacingLeft() {
        return faceLeft;
    }

    public FireballSprite(int XPosition, int YPosition){
        super("resources/projectile/Fireball.png",3,25,25,XPosition,YPosition);
        this.scale = 1.5;
    }

    @Override
    public void draw(Graphics g, Camera camera) {
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