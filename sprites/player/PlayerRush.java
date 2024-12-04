package sprites.player;

import entities.hitboxes.CircleHitbox;
import game.Camera;

import java.awt.*;

public class PlayerRush extends PlayerSpriteDecorator{
    public PlayerRush(PlayerSprite playerSprite) {
        super(playerSprite, "resources/player/Rush.png", 3,34, 17);
    }

    private CircleHitbox hitbox = new CircleHitbox(getXCenter(), getYCenter(), 100);

    public CircleHitbox getHitbox() {
        return hitbox;
    }

    @Override
    public void tick() {
        super.tick();
        hitbox.setXPosition(getXCenter());
        hitbox.setYPosition(getYCenter());
    }

    @Override
    public void draw(Graphics g, Camera camera) {
        if (!isFacingLeft())
            g.drawImage(getImage(),
                getXCenter() - camera.getX() - getWidth() / 2,
                getYCenter() - camera.getY() - getHeight() / 2,
                getWidth(),
                getHeight(),
                null);
        else
            g.drawImage(getImage(),
                getXCenter() - camera.getX() + getWidth() / 2,
                getYCenter() - camera.getY() - getHeight() / 2,
                -getWidth(),
                getHeight(),
                null);
    }

    public void debugDraw(Graphics g, Camera camera) {
        hitbox.debugDraw(g, camera);
    }

}
