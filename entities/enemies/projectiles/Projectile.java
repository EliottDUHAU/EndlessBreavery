package entities.enemies.projectiles;

import controllers.PlayerController;
import entities.Entity;
import entities.Player;
import utils.Vector2D;
/**
 * The Projectile class is an abstract class that represents a projectile in the game.
 */
public abstract class Projectile extends Entity {
    /**
     * The travel time of the projectile
     */
    private int travelTime;
    /**
     * Create a new entity
     *
     * @param xPosition x position
     * @param yPosition y position
     */
    public Projectile(int xPosition, int yPosition) {
        super(xPosition, yPosition);
    }

    /**
     * Get the direction of the projectile
     * @return the direction of the projectile
     */
    public abstract Vector2D getDirection();
    /**
     * Get the travel time of the projectile
     * @return the travel time
     */
    @Override
    public void setXPosition(int xPosition) {
        super.setXPosition(xPosition);
        getHitbox().setXPosition(xPosition + getSprite().getWidth() / 2);
    }
    /**
     * Override the position setters to update the hitbox position
     */
    @Override
    public void setYPosition(int yPosition) {
        super.setYPosition(yPosition);
        getHitbox().setYPosition(yPosition + getSprite().getHeight() / 2);
    }
    /**
     * Get the hitbox of the projectile
     * @return the hitbox of the projectile
     */
    public abstract boolean isAlive();
    /**
     * Get the damage of the projectile
     * @return the damage of the projectile
     */
    public void tick(){
        setXPosition(getXPosition() + (int) getDirection().getX());
        setYPosition(getYPosition() + (int) getDirection().getY());
        getSprite().tick();
    }
}
