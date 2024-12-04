package entities.enemies;
import controllers.EnemyController;
import controllers.MapController;
import controllers.PlayerController;
import entities.Player;
import entities.Entity;
import entities.hitboxes.Hitbox;
import entities.hitboxes.LineHitbox;
import utils.Pair;
import utils.Vector2D;
import game.Camera;
import sprites.enemies.EnemyUI;

import java.awt.*;

/**
 * The base enemy class
 */
public abstract class  Enemy extends Entity {
    /**
     * The invulnerable ticks after getting hit
     */
    private int invulnerableTicks = 30;
    /**
     * The tick counter for iframes
     */
    private int iTickCounter = 0;
    /**
     * The knock back vector
     */
    private Vector2D knockBack;
    /**
     * The duration of the knock back
     */
    private int knockBackDuration = 15;
    /**
     * The counter for the knock back
     */
    private int knockbackCounter = 0;
    /**
     * The magnitude of the knock back
     */
    private double knockbackMagnitude = 1.5;
    /**
     * Get the player
     * @return the player
     */
    public Player getPlayer() {
        return PlayerController.getInstance().getPlayer();
    }
    /**
     * The current health of the enemy
     */
    private int health;
    /**
     * The max health of the enemy
     */
    private int maxHealth;
    /**
     * The enemy UI
     */
    private EnemyUI enemyUI;
    /**
     * Get the health of the enemy
     * @return the health
     */
    public int getHealth() {
        return health;
    }
    /**
     * Get the max health of the enemy
     * @return the max health
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    /**
     * Damage the enemy
     * @param damage the amount of damage
     * @return true if the enemy was damaged
     */
    public boolean damage(int damage) {
        if (iTickCounter > 0) {
            return false;
        }
        this.health -= damage;
        this.iTickCounter = invulnerableTicks;
        return true;
    }
    /**
     * Knock back the enemy
     */
    public void knockBack() {
        Player player = PlayerController.getInstance().getPlayer();
        // calculate knock back
        knockBack = new Vector2D(player.getSprite().getXCenter() - (getXPosition() + getSprite().getWidth() / 2), player.getYPosition() - (getYPosition() + getSprite().getHeight() / 2));
        knockBack.multiply(-knockbackMagnitude / knockBackDuration);
        knockbackCounter = knockBackDuration;
    }
    /**
     * Check if the enemy is dead
     * @return true if the enemy is dead
     */
    public boolean isDead() {
        return health <= 0;
    }
    /**
     * Create a new enemy
     * @param xPosition x position
     * @param yPosition y position
     * @param health health
     */
    public Enemy(int xPosition, int yPosition, int health, int maxHealth) {
        super(xPosition, yPosition);
        this.health = health;
        this.maxHealth = health;
        this.enemyUI = new EnemyUI(this);
    }
    /**
     * Get the hitbox of the enemy
     * @return the hitbox
     */
    abstract public Hitbox getHitbox();

    /**
     * Get the Ui of the enemy
     * @return  the enemy UI
     */
    public EnemyUI getEnemyUI(){return enemyUI;}
    /**
     * Override the position setters to update the hitbox position
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
     * Draw the enemy UI
     * @param g the graphics object
     * @param camera the camera
     */
    public void drawUI(Graphics g, Camera camera){
        final int  HEALTH_BAR_WIDTH = getSprite().getWidth()/2;
        final int HEALTH_BAR_HEIGHT = 5;
        int HEALTH_RATIO = HEALTH_BAR_WIDTH * getHealth() / getMaxHealth();
        g.setColor(Color.BLACK);
        g.fillRect(getXPosition()-camera.getX()+getSprite().getWidth()/4,getYPosition()+getSprite().getHeight()- camera.getY(),HEALTH_BAR_WIDTH,HEALTH_BAR_HEIGHT);
        g.setColor(Color.RED);
        g.fillRect(getXPosition()-camera.getX()+getSprite().getWidth()/4,getYPosition()+getSprite().getHeight()- camera.getY(),HEALTH_BAR_WIDTH-1,HEALTH_BAR_HEIGHT-1);
        g.setColor(Color.GREEN);
        g.fillRect(getXPosition()-camera.getX()+getSprite().getWidth()/4,getYPosition()+getSprite().getHeight()- camera.getY(),HEALTH_RATIO-1,HEALTH_BAR_HEIGHT-1);
    }

    /**
     * Update the enemy
     */
    @Override
    public void tick() {
        if (iTickCounter > 0) {
            iTickCounter--;
        }
        Hitbox collisionBox = getCollisionBox();
        if (knockbackCounter > 0) {
            Pair<Integer, Integer> position;
            if (collisionBox instanceof LineHitbox) {
                position = MapController.getInstance().getMap().collide((LineHitbox) collisionBox, knockBack.getX(), knockBack.getY());
            } else {
                position = new Pair<>(getXPosition() + knockBack.getX(), getYPosition() + knockBack.getY());
            }
            if (position.getFirst() != collisionBox.getXPosition() || position.getSecond() != collisionBox.getYPosition()) {
                this.setXPosition(getXPosition() + knockBack.getX());
                this.setYPosition(getYPosition() + knockBack.getY());
            }
            knockbackCounter--;
        }
    }
}
