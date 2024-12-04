package entities;

import entities.hitboxes.Hitbox;
import sprites.Sprite;

/**
 * The base entity class
 */
public abstract class Entity {
    /**
     * The x position of the entity
     */
    private int xPosition;
    /**
     * The y position of the entity
     */
    private int yPosition;
    /**
     * Create a new entity
     * @param xPosition x position
     * @param yPosition y position
     */
    public Entity(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    /**
     * Get the collision box of the entity
     * @return the collision box
     */
    abstract public Hitbox getCollisionBox();
    /**
     * Set the collision box of the entity
     * @param hitbox the collision box
     */
    abstract public void setCollisionBox(Hitbox hitbox);
    /**
     * Get the y position of the entity
     * @return y position
     */
    public int getYPosition() {
        return yPosition;
    }
    /**
     * Get the x position of the entity
     * @return x position
     */
    public int getXPosition() {
        return xPosition;
    }
    /**
     * Set the x position of the entity
     * @param xPosition x position
     */
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;

        getSprite().setXPosition(xPosition);
    }
    /**
     * Set the y position of the entity
     * @param yPosition y position
     */
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
        getSprite().setYPosition(yPosition);
    }
    /**
     * Abstract method to get the sprite of the entity
     */
    public abstract Sprite getSprite();
    /**
     * Abstract method to get the hitbox of the entity
     */
    public abstract Hitbox getHitbox();
    /**
     * Update the entity
     */
    public abstract void tick();
}
