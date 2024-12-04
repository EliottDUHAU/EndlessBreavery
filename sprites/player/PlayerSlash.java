package sprites.player;

import entities.hitboxes.CircleHitbox;
import game.Camera;
import sprites.effects.SlashSwirl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Player sprite decorator to add a slash animation
 */
public class PlayerSlash extends PlayerSpriteDecorator {
    private CircleHitbox hitbox;

    /**
     * The swirl effect of the slash
     */
    private SlashSwirl swirl;
    /**
     * Create a new player slash animation
     * @param playerSprite the player sprite to decorate
     */
    public PlayerSlash(PlayerSprite playerSprite) {
        super(playerSprite, "resources/player/slash.png", 5, 47, 29);
        loadSpriteSheet();
        scale = playerSprite.getScale();
        swirl = new SlashSwirl(getXCenter(), getYCenter(), isFacingLeft());
        hitbox = new CircleHitbox(getXCenter(), getYCenter(), 20);
    }
    /**
     * Image of the player slash animation
     */
    private BufferedImage spriteSheet;
    /**
     * Load the spritesheet image from resources.
     */
    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(new File("resources/player/slash.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Advance the state of the slash animation
     */
    @Override
    public void tick() {
        hitbox.setRadius(hitbox.getRadius() + 5);
        if (frame > 2) swirl.tick();
        super.tick();
    }
    /**
     * Draw the slash animation
     * @param g graphics object
     * @param camera camera object
     */
    @Override
    public void draw(Graphics g, Camera camera) {
        if (this.isFacingLeft()) {
            g.drawImage(getImage(),
                    getXPosition() - camera.getX() + getWidth() + getOffsetX(),
                    getYPosition() - camera.getY(),
                    -getWidth(), getHeight(), null);
        }
        else {
            g.drawImage(getImage(),
                    getXPosition() - camera.getX() + getOffsetX(),
                    getYPosition() - camera.getY(),
                    getWidth(), getHeight(),
                    null);
        }

        if (!swirl.isDone() && frame > 3) swirl.draw(g, camera);
    }

    public void debugDraw(Graphics g, Camera camera) {
        hitbox.debugDraw(g, camera);
    }

    public CircleHitbox getHitbox() {
        return hitbox;
    }
}
