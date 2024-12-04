package sprites.player;

import entities.hitboxes.CircleHitbox;
import game.Camera;
import sprites.effects.HealingVortex;
import sprites.effects.SlashSwirl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerHeal extends PlayerSpriteDecorator {
    /**
     * The swirl effect of the slash
     */
    private HealingVortex healingVortex;
    /**
     * Create a new player slash animation
     * @param playerSprite the player sprite to decorate
     */
    public PlayerHeal(PlayerSprite playerSprite) {
        super(playerSprite, "resources/effects/HealingSmall.png", 5, 62, 52);
        scale = playerSprite.getScale();
        healingVortex = new HealingVortex(getXCenter(), getYCenter());
    }
    /**
     * Image of the player slash animation
     */
    private BufferedImage spriteSheet;

    public void tick() {
        healingVortex.tick();
        super.tick();
        getPlayerSprite().tick();
    }

    /**
     * Draw the slash animation
     * @param g graphics object
     * @param camera camera object
     */
    @Override
    public void draw(Graphics g, Camera camera) {
        PlayerSprite playerSprite = getPlayerSprite();
        healingVortex.setXPosition(playerSprite.getXCenter() - healingVortex.getWidth() / 2 - camera.getX());
        healingVortex.setYPosition(playerSprite.getYCenter() - healingVortex.getHeight() / 2 - camera.getY());
        healingVortex.draw(g, camera);
        playerSprite.draw(g, camera);

    }

    /**
     * Overiding height getter to avoid camera issues
     * Gets the height of the sprite
     * @return The height of the sprite in game
     */
    @Override
    public int getHeight() {
        return getPlayerSprite().getHeight();
    }

    /**
     * Overiding width getter to avoid camera issues
     * Gets the width of the sprite
     * @return The width of the sprite in game
     */
    @Override
    public int getWidth() {
        return getPlayerSprite().getWidth();
    }
}
